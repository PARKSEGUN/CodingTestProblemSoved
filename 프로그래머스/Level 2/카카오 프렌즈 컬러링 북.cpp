BFS 알고리즘의 간단한 문제였다.

#include <vector>
#include <iostream>
#include <queue>

using namespace std;

bool visited[111][111];

int BFS(int x, int y, int shape, vector<vector<int>>& picture) {
    int m = picture.size();
    int n = picture[0].size();
    int dix[4] = { 0,0,1,-1 };
    int diy[4] = { 1,-1,0,0 };
    queue<pair<int, int>> q;
    q.push({ x,y });
    visited[x][y] = true;
    int sum = 1;
    while (q.size() != 0) {
        pair<int, int> cur = q.front();
        q.pop();
        for (int i = 0; i < 4; i++) {
            int dx = cur.first + dix[i];
            int dy = cur.second + diy[i];
            if (dx >= 0 && dx < m && dy >= 0 && dy < n && visited[dx][dy] == false && picture[dx][dy] == shape) {
                visited[dx][dy] = true;
                q.push({ dx,dy });
                sum++;
            }
        }
    }
    return sum;
}

vector<int> solution(int m, int n, vector<vector<int>> picture) {
    // 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            visited[i][j] = false;
        }
    }

    int number_of_area = 0;
    int max_size_of_one_area = 0;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (visited[i][j] == false && picture[i][j] != 0) {
                max_size_of_one_area = max(max_size_of_one_area, BFS(i, j, picture[i][j], picture));
                number_of_area++;

            }
        }
    }
    vector<int> answer(2);
    answer[0] = number_of_area;
    answer[1] = max_size_of_one_area;
    return answer;
}
