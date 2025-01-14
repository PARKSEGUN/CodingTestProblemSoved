삭제된 여부를 bool 변수로 표현해서 접근했지만 시간초과 발생
erase(), insert 함수를 사용해서 해결하려 했지만 시간초과 발생
linked list로 접근, 이전의 좌표와 이후의 좌표를 기억하는 vector생성, 삭제한것을 stack 으로 관리
U 또는 D 가 나왔을때 이동가능 거리수에 대해 한자리수로 착각해서 오류 발생

#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

string solution(int n, int k, vector<string> cmd) {
    string answer = "";
    for (int i = 0; i < n; i++)
        answer.push_back('O');
    vector<pair<int, int>> side(n);  //{왼쪽,오른쪽}
    vector<int>remove;

    side[0].first = -1; side[0].second = 1;
    side[n - 1].first = n - 2; side[n - 1].second = -1;
    for (int i = 1; i < n - 1; i++) {
        side[i].first = i - 1;
        side[i].second = i + 1;
    }
    int cur = k;
    for (int i = 0; i < cmd.size(); i++) {
        string str = cmd[i];
        if (str[0] == 'U') {
            for (int i = 0; i < stoi(str.substr(2))/*이부분*/; i++) {
                cur = side[cur].first;
            }
        }
        else if (str[0] == 'D') {
            for (int i = 0; i < stoi(str.substr(2)); i++) {
                cur = side[cur].second;
            }
        }
        else if (str[0] == 'C') {
            remove.push_back(cur);
            if (side[cur].second != -1) {
                if (side[cur].first != -1)
                    side[side[cur].first].second = side[cur].second;
                side[side[cur].second].first = side[cur].first;
                cur = side[cur].second;
            }
            else {
                cur = side[cur].first;
                side[cur].second = -1;
            }
        }
        else if (str[0] == 'Z') {
            int tmp = remove.back();
            remove.pop_back();
            if (side[tmp].first != -1)
                side[side[tmp].first].second = tmp;
            if (side[tmp].second != -1)
                side[side[tmp].second].first = tmp;
        }
    }
    for (int i = 0; i < remove.size(); i++)
        answer[remove[i]] = 'X';
    return answer;
}
