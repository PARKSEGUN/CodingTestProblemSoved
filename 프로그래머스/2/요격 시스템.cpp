처음에 문제를 보고 바로 정렬해야겠다는 생각이 들어서 정렬을 한 후에 생각해봤다.
정렬한 후에 요격한발로 처리될 수 있는 그룹을 조합으로 묶어주고 
파괴되지 않은 미사일이 속해있는 그룹중에 가장 미사일이 많은 그룹부터 제거한다면 해결할 수 있다고 생각
일단 그룹으로 묶어주는 코드만 돌려봤을때 시간초과 발생 -> 그룹으로 묶어줄때에 이중반복문을 사용해서 인것
선형 탐색으로 해결해야되거나 O(NlogN) 으로 해결해야한다.
주어진 값은 미사일의 시작 좌표와 끝 좌표 이 두개로 문제를 해결해야한다.
해결방법
미사일을 시작시간에 따라 오름차순으로 정렬
탐색하고있는 미사일을 파괴할 수 있을때에 같이 파괴할 수 있는 미사일 파악
탐색하고있는 미사일의 끝 좌표보다
작다면
현재 탐색하고있는 미사일을 파괴할때 같이 파괴 시킬 수 있다. 
이때 중요한점은 끝좌표를 기억해야하고 끝좌표를 계획 최소의 수로 초기화 시켜주어야한다. ( end = min(end, targets[i][1]);)
작지 않다면
시작 좌표와 끝 좌표를 다시 설정해주고 answer ++

#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> targets) {
    int answer = 1;
    sort(targets.begin(), targets.end());
    int start = targets[0][0];
    int end = targets[0][1];
    for (int i = 1; i < targets.size(); i++) {
        if (targets[i][0] < end) {
            end = min(end, targets[i][1]);
        }
        else {
            start = targets[i][0];
            end = targets[i][1];
            i--;
            answer++;
        }
    }
    return answer;
}
