UNION-FIND 알고리즘과 비슷한 형태로 해결

#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

bool visited[111];
vector<int> vec;

void unionFind(int start, vector<int>& cards, int cnt) {
    if (visited[start] == true) {
        vec.push_back(cnt);
        return;
    }
    visited[start] = true;
    return unionFind(cards[start] - 1, cards, cnt + 1);
}

int solution(vector<int> cards) {
    int max1 = 0, max2 = 0;  //첫번째로 가장큰수, 두번째로 가장큰수
    for (int i = 0; i < cards.size(); i++) {
        if (visited[i] == false) {
            unionFind(i, cards, 0);
        }
    }
    for (int i = 0; i < vec.size(); i++) {
        if (max1 < vec[i]) {
            max2 = max(max2, max1);
            max1 = vec[i];
        }
        else {
            max2 = max(max2, vec[i]);
        }
    }
    return max1 * max2;
}
