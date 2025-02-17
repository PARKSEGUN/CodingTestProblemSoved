sort 조건을 성립해주는 방법을 알면 쉽게 해결

#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int standard;

bool cmp(vector<int> vec1, vector<int> vec2) {
    if (vec1[standard] == vec2[standard]) {
        return vec1[0] > vec2[0];
    }
    return vec1[standard] < vec2[standard];
}

int modSum(vector<int> vec, int col) {
    int sum = 0;
    for (int i = 0; i < vec.size(); i++) {
        sum += vec[i] % col;
    }
    return sum;
}

int solution(vector<vector<int>> data, int col, int row_begin, int row_end) {
    int answer = 0;
    standard = col - 1;
    sort(data.begin(), data.end(), cmp);
    for (int i = row_begin - 1; i <= row_end - 1; i++) {
        answer ^= modSum(data[i], i + 1);
    }
    return answer;
}
