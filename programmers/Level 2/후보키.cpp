unordered_map을 사용해서 유일성을 찾아내었고
서로가 포함하는지를 파악해서 최소성을 찾아내었

#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>
#include <algorithm>

using namespace std;

bool visited[11];
int arr[11];
vector<vector<int>> answerValue;

void DFS(int cnt, int start, int M, vector<vector<string>>& vec) {
    if (cnt > 0) {
        //unordered_map을 사용해서 유일성을 만족하는지확인
        unordered_map<string, bool> um;
        bool chk = false;
        for (int i = 0; i < vec.size(); i++) {
            string str = "";
            for (int j = 0; j < cnt; j++) {
                str += vec[i][arr[j]];
            }
            if (um[str] != true) {
                um[str] = true;
            }
            else {
                chk = true;
                break;
            }
        }
        if (chk == false) {
            vector<int> v;
            for (int j = 0; j < cnt; j++) {
                v.push_back(arr[j]);
            }
            answerValue.push_back(v);
        }
    }
    for (int i = start; i < M; i++) {
        if (visited[i] == false) {
            visited[i] = true;
            arr[cnt] = i;
            DFS(cnt + 1, i + 1, M, vec);
            visited[i] = false;
        }
    }
}

int solution(vector<vector<string>> relation) {
    int answer = 0;
    unordered_map<string, bool> um;
    DFS(0, 0, relation[0].size(), relation);
    for (int i = 0; i < answerValue.size(); i++) {
        bool visited[11] = { 0, };
        bool chk = false;
        for (int j = 0; j < answerValue[i].size(); j++) {
            visited[answerValue[i][j]] = true;
        }
        for (int j = 0; j < answerValue.size(); j++) {
            if (i != j) {
                int cnt = 0;
                for (int k = 0; k < answerValue[j].size(); k++) {
                    if (visited[answerValue[j][k]] == true) {
                        cnt++;
                    }
                }
                if (cnt == answerValue[j].size()) {
                    chk = true;
                    break;
                }
            }
        }
        if (chk == false) {
            for (int k = 0; k < answerValue[i].size(); k++) {
                cout << answerValue[i][k] << " ";
            }cout << endl;
            answer++;
        }
    }
    return answer;
}
