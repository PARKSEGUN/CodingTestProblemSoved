첫번째 접근 방법은 dist라는 친구들의 이동거리가 담긴배열에서 모든 weak 정점에서 출발했을때에 나올 수 있는 모든 경우를 저장
즉 1이라는 지점에서 [1,2,3,4] 라는 dist친구들이 출발했을때에 어디까지 취약점을 발견할 수 있는지를 저장하는 것이다.
1에서 출발했고 4에 또다른 취약지점이 있을경우에는 
dist[0]은 1 
dist[1]은 1 
dist[2]은 1, 4 
dist[3]은 1, 4
의 취약점을 발견할 수 있는 것이다.
이렇게 모든 dist에서 모든 취약지점에서 출발했을때에 나올 수 있는 모든 경우를 구하고 조합으로 맞춰보면 모든 경우의수를 확인해서 답을 구할 수 있다고 생각했다
결과는 시간초과였다.
모든 경우의수를 조합으로 확인하는데 이때 DFS를 사용했고 그래서 시간초과가 발생한 것 같다.

원형이라는 모형을 보고 길이를 더 늘려주어서 선형으로 만들어서 해결하는 방법은 없을까 생각했고, 선형으로 만들어줘도 모든 경우를 확인하려면
시간초과가 나올 것이라고 생각했고 그리디(탐욕법)으로 해결해야겠다고 생각했지만 결국 생각해내지 못해서 해답을 보고 해결했다.

해답을 보니 일단 완전탐색으로 해결해야되는 문제였다. 주어지는 범위가 작기때문에 나 또한 완전탐색으로 모든 경우의 수를 확인해서 해결할 수 있다고 생각했었지만 어떤 방법을 사용했는지 확인해보자

1. 계산하기쉽게 먼저 주어진 weak배열을 두배로 늘려주어서 선형으로 변형해준다. 두배 늘려주었을때에 인덱스 값도 증가시켜준다.
2. 효율적인 계산을 위해서 정렬을 진행한다(어차피 순열을 사용해서 진행할 것인데 굳이 정렬이 필요하지 않을것 같다서 적용하지 않았다.)
3. dist배열로 만들 수 있는 모든 순열의 경우를 만들어준다.
4. 만들어진 순열로 취약점의 탐색시작점을 정해주고 시작점에따른 끝점도 정해준다음 만들어진 순열로 탐색을 진행한다.

즉, weak배열이 [1, 5, 6, 10] 이렇게 있고 두배 늘려주어서 [1, 5, 6, 10, 13, 17, 22, 25]  만들어진 순열이 [4, 3, 2, 1] 이라면 
출발지점을 정해준다 1,5,6,10 차례대로 첫번째 순열인 4을 대입해준다. 
1에서 출발하게 되면 5까지 가능 하기때문에 다음순열인 3은 5가 아닌 6에서 출발하게된다.
순열 3은 6에서 출발해서 9까지 밖에 탐색을 못하기때문에 10은 그다음 순열인 2가 맡게된다.
순열 2를 진행시켰을때에 10을 탐색하고 이때, 끝점은 10으로 10이 탐색이 되었다면 종료한다.
만약 weak 5에서 출발하게되었다면 끝점은 13이되고 6에서 출발했다면 끝점은 17이 된다.
이렇게 설계하게되면 모든 경우를 확인하고 최소값을 찾아낼 수 있다.


#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

bool visited[11];
int arr[11];
int answer = 11;

//취약지점을 탐색 - 취약점의 개수는 weak.size()/2개라는 것을 이용해서 해결
void findWeak(vector<int>& weak, vector<int>& dist) {
    for (int i = 0; i < weak.size() / 2; i++) {
        int cur = weak[i] - 1;
        int end = weak[i + weak.size() / 2 - 1];
        int cnt = 0;
        for (int j = i; j <= i + weak.size() / 2 - 1; j++) {
            if (cur < weak[j]&&cnt<dist.size()) {
                cur = weak[j] + arr[cnt];
                cnt++;
            }
            if (cur >= end) {
                answer = min(answer, cnt);
                break;
            }
        }
    }
}
//순열을 찾기위함 DFS함수
void DFS(int cnt, vector<int>& weak, vector<int>& dist) {
    if (cnt == dist.size()) {
        findWeak(weak, dist);
    }
    else {
        for (int i = 0; i < dist.size(); i++) {
            if (visited[i] == false) {
                arr[cnt] = dist[i];
                visited[i] = true;
                DFS(cnt + 1, weak, dist);
                visited[i] = false;
            }
        }
    }
}

int solution(int n, vector<int> weak, vector<int> dist) {
    //원형의 문제를 선형으로 해결하기위해 길이를 두배 늘려주기
    weak.resize(weak.size() * 2);
    for (int i = 0; i < weak.size() / 2; i++) {
        weak[weak.size() / 2 + i] = weak[i] + n;
    }
    DFS(0, weak, dist);
    return (answer==11)?-1:answer;
}
