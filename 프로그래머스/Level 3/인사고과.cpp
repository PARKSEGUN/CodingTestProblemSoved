처음에 문제를보고 100000이라는 범위에 n^2 알고리즘을 사용하면 시간초과가 날것이라고 생각했다.
  바로 직관적으로 브루트포스 알고리즘을 사용해서 시간초과 여부를 확인했고 정답은 맞았지만 역시나 시간초과가 발생했다.
  이 문제를 해결하기위해서는 '다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면' 이 경우를 어떤 알고리즘으로 작성할 것이냐 였다.
  두가지의 변수를 비교하기때문에 하나의 점수를 정렬해 놓는다면 접근할 수 있다고 생각했다.
  첫번째 자리에 대해서 내림차순, 두번째 자리에 대해서 내림차순으로 정렬한후에
  탐색하면서 앞자리가 달라질때마다(앞자리가 달라지는 순간은 앞자리가 같은 점수들중에 가장 큰수) 정해진 vector에 넣어주고 모든수를 vector안에 내용과 비교하도록했다.
  vector안에 내용과 비교해서 작은것이 존재한다면 -1을 넣어주어서 제거됨을 표시했다.
  #include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<pair<int, int>> compareVal;

bool mapping(vector<vector<int>>& scores, pair<int, int>& target) {
    int before = -1;
    for (int i = 0; i < scores.size(); i++) {
        for (int j = 0; j < compareVal.size(); j++) {
            if (scores[i][0] < compareVal[j].first && scores[i][1] < compareVal[j].second) {
                if (scores[i][0] == target.first && scores[i][1] == target.second) {
                    return false;
                }
                else {
                    //삭제된것은 -1처리
                    scores[i][0] = -1;
                }
            }
        }
        if (before != scores[i][0]) {
            compareVal.push_back({ scores[i][0],scores[i][1] });
            before = scores[i][0];
        }
    }
    return true;
}

bool compare(vector<int> a, vector<int> b) {
    if (a[0] == b[0]) {
        return a[1] > b[1];
    }
    return a[0] > b[0];
}

int solution(vector<vector<int>> scores) {
    pair<int, int> target = { scores[0][0],scores[0][1] };
    sort(scores.begin(), scores.end(), compare);
    if (mapping(scores, target) == false) {
        return -1;
    }

    int sum = target.first + target.second;
    int rank = 1;
    for (int i = 0; i < scores.size(); i++) {
        if (scores[i][0] != -1 && scores[i][0] + scores[i][1] > sum) {
            rank++;
        }
    }
    return rank;
}

통과를 받았지만 결과적으로 나온 경과 시간이 너무 많이 나왔고 맘에 들지않았다.
또한 이 알고리즘은 모든 앞자리의 수가 다 다른 입력이 주어진다면 역시나 n^2 시간복잡도가 발생하게되고 올바르지 못한것같다.
따로 알아보니
  첫번째를 내림차순 두번째를 오른차순으로 정렬 한다면 탐색하면서 첫째가 같은 숫자라면 두번째가 가장 작은수가 제일위에 가장 높은 수가 그룹의 제일 밑에 위치하게된다.
  이 가장 높은 수를 비교대상으로 저장해놓는다
  이 그룹의 밑에있는 그룹의 첫번째 점수는 당연히 위에 그룹보다 첫번째 수는 작을 것이기때문에 두번째 수만 비교하면 되게된다.
  예를 들어서
  3 2
  3 4
  2 1
  2 9
  이렇게 그룹이 나눠지는 것이다.
  이렇게되면 3 4와 2 1 을 비교하게되고 작기때문에 제거되고 2 9 는 9가 더 크기때문에 2 9 를 비교대상으로 저장해주고 탐색을 해주는 방식으로 진행하면된다.
  시간복잡도는 n으로 선형으로 해결할 수 있어서 시간이 많이 단축되는 방법이였다.

시간이 걸리더라도 문제를 풀때에 항상 효율적인 방법을 찾는 습관을 기르자.
