문제를 보고 DFS 아니면 BFS로 모든 경우를 확인해봐야한다고 생각 DFS 보다는 BFS가 효율적이고 이해하기 편할거 같아서 선택
queue를 사용하고 queue에는 현재 내가 탐색하고있는 점수구간, 남은 화살, 현재 내 점수표
이렇게 들어있고
내가 지금 탐색하고있는 점수구간을 이겨서 다음 점수구간으로 넘어가는 경우와
아무것도 하지않고 넘어가는 경우
이렇게 두가지로 나눌 수 있다.
가장 차이가 많이나야하기때문에 차이(diff)를 계속 초기화 해주면서 진행
같은 차이라면 낮은 점수를 많이 맞춘것을 리턴하라는 조건에서
차이가 가장 큰 무리중에서 조건에 맞게 compare를 작성해주고 첫번째것을 뽑아줘서 해결
해결하는 방법이 어려운게 아닌 구현하는 부분에서 힘들었던 문제

#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<vector<int>> answers;
int diff = 1;

void winner(vector<int> info, vector<int>& score, int arrow) {
    //남은 화살이 있다면
    if (arrow > 0)
        score[10] += arrow;
    int lion = 0;
    int apeach = 0;
    for (int i = 0; i < score.size(); i++) {
        if (score[i] != 0 || info[i] != 0) {
            if (score[i] > info[i])
                lion += 10 - i;
            else
                apeach += 10 - i;
        }
    }
    if (diff <= lion - apeach) {
        if (diff < lion - apeach) {
            diff = lion - apeach;
            answers.clear();
        }
        answers.push_back(score);
    }
}

void BFS(int& n, vector<int>& info) {
    queue<pair<pair<int, int>, vector<int>>> lion; //<현재 탐색해야하는 점수구간,남은 화살>/이긴 점수구간
    vector<int> vec(11, 0);
    lion.push({ {0,n},vec });
    while (lion.size() != 0) {
        int cur = lion.front().first.first;
        int arrow = lion.front().first.second;
        vector<int> score = lion.front().second;
        lion.pop();
        if (cur > 10) {
            winner(info, score, arrow);
            continue;
        }
        int apeachNum = info[cur];    //어피치가 해당점수 맞춘 개수

        //그냥 안쏘고 넘어가는 경우
        lion.push({ {cur + 1,arrow},score });
        if (arrow > apeachNum) {    //이기고 넘어가는 경우
            arrow -= apeachNum + 1;
            score[cur] = apeachNum + 1;
            lion.push({ {cur + 1,arrow},score });
        }
    }
}

bool compare(vector<int> vec1, vector<int> vec2) {
    int k = 10;
    while (1) {
        if (vec1[k] == vec2[k])
            k--;
        else
            return vec1[k] > vec2[k];

    }
}
vector<int> solution(int n, vector<int> info) {
    vector<int> answer(1, -1);
    BFS(n, info);
    sort(answers.begin(), answers.end(), compare);
    if (answers.size() == 0)
        return answer;
    return answers.front();
}
