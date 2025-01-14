숫자가 채워지는 규칙
한가지 동작이 실행될때마다 n은 -1 씩 감소 
1. 1씩 증가하면서 배열을 내려가면서 저장
2. n만큼 내려갔다면 1씩 증가시키면서 한 배열에 저장
3. 1씩 증가하면서 배열을 올라가면서 저장

#include <string>
#include <vector>
#include<iostream>
#include<algorithm>

using namespace std;

vector<int> solution(int n) {
    vector<int> answer;
    vector<vector<int>> vec;
    //뒤에서부터 채워지는 경우를 해결하기위해 따로 채워넣은뒤 앞뒤를 뒤집는다
    vector<vector<int>> vecBack;

    for (int i = 0; i < n; i++) {
        vector<int> v;
        v.push_back(i + 1);
        vec.push_back(v);
        vecBack.push_back(v);
        vecBack[i].pop_back();
    }
    int idx = n - 1;
    int val = n + 1;
    n--;
    while (n > 0) {
        for (int i = 0; i < n; i++) {
            vec[idx].push_back(val);
            val++;
        }
        n--;
        for (int i = 0; i < n; i++) {
            idx--;
            vecBack[idx].push_back(val);
            val++;
        }
        n--;
        for (int i = 0; i < n; i++) {
            idx++;
            vec[idx].push_back(val);
            val++;
        }
        n--;
    }
    for (int i = 0; i < vecBack.size(); i++) {
        for (int j = vecBack[i].size() - 1; j >= 0; j--) {
            vec[i].push_back(vecBack[i][j]);
        }
    }

    for (auto x : vec) {
        for (auto y : x)
            answer.push_back(y);
    }
    return answer;
}
