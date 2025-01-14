주어진 맵을 탐색하면서 X 가 아닌부분이라면 방문표시를 해주고 그곳을 기점으로 BFS 사용
모든 연결된 부분을 합해주고 이어서 방문하지 않은 지점을 탐색

#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

bool visited[111][111];
int map[111][111];
int dix[4] = { 0,0,1,-1 };
int diy[4] = { 1,-1,0,0 };
int N, M;
vector<int> answer;

int BFS(int startX, int startY) {
    queue<pair<int, int>> q;
    int sum = map[startX][startY];
    q.push({ startX,startY });
    visited[startX][startY] = true;
    while (q.size() != 0) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++) {
            int dx = x + dix[i];
            int dy = y + diy[i];
            if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
                if (visited[dx][dy] == false && map[dx][dy] != 0) {
                    visited[dx][dy] = true;
                    q.push({ dx,dy });
                    sum += map[dx][dy];
                }
            }
        }
    }
    return sum;
}

void findStart() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (map[i][j] != 0 && visited[i][j] == false) {
                answer.push_back(BFS(i, j));
            }
        }
    }
}

vector<int> solution(vector<string> maps) {
    N = maps.size();
    M = maps[0].size();
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (maps[i][j] == 'X')
                map[i][j] = 0;
            else
                map[i][j] = maps[i][j] - '0';
        }
    }
    findStart();
    if (answer.size() == 0)
        answer.push_back(-1);
    sort(answer.begin(), answer.end());
    return answer;
}
