#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <iostream>

using namespace std;

unordered_map<string, int> um;   //<만들어진 경우,횟수>
int mx[11]; //map에 저장되어있는 string의 길이당 최대의 횟수를 기억하기위한 변수
char arr[33];

void combination(int cnt, int start, string& str, int& course) {
    if (cnt == course) {
        string tmp = "";
        for (int i = 0; i < cnt; i++)
            tmp += arr[i];
        um[tmp]++;
        //map에 저장되어있는 string의 길이당 최대의 횟수를 기억
        mx[course] = max(mx[course], um[tmp]);
    }
    else {
        for (int i = start; i < str.size(); i++) {
            arr[cnt] = str[i];
            combination(cnt + 1, i + 1, str, course);
        }
    }
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    //알파벳 오름차순으로 정렬된값을 return 하기위한 정렬
    for (int i = 0; i < orders.size(); i++)
        sort(orders[i].begin(), orders[i].end());
    //조합할 수 있는 모든 경우와 그 경우의 횟수 구하기
    for (auto x : orders)
        for (auto y : course)
            combination(0, 0, x, y);
    //두번이상 조합되었고 가장 많이 사용된값 구하기
    for (auto z : um)
        if (mx[z.first.size()] == z.second && z.second >= 2)
            answer.push_back(z.first);
    //문자열 형식을 오름차순으로 정렬해서 return 하기위한 정렬
    sort(answer.begin(), answer.end());
    return answer;
}
