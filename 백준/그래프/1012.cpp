BFS를 사용해서 map이 몇구역으로 나누어지는지를 판별함

#include<iostream>
#include<queue>

using namespace std;

int T, N, M, K;

int dix[4] = { 0,0,1,-1 };
int diy[4] = { 1,-1,0,0 };

void solution() {
	int map[55][55] = { 0, };
	bool visited[55][55] = { 0, };
	int answer = 0;
	cin >> M >> N >> K;
	for (int i = 0; i < K; i++) {
		int x, y;
		cin >> x >> y;
		map[y][x] = 1;
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] == 1 && visited[i][j] == false) {
				answer++;
				queue<pair<int, int>> q;
				q.push({ i,j });
				visited[i][j] = true;
				while (q.size() != 0) {
					int x = q.front().first;
					int y = q.front().second;
					q.pop();
					for (int i = 0; i < 4; i++) {
						int dx = x + dix[i];
						int dy = y + diy[i];
						if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
							if (map[dx][dy] == 1 && visited[dx][dy] == false) {
								visited[dx][dy] = true;
								q.push({ dx,dy });
							}
						}
					}
				}
			}
		}
	}
	cout << answer << endl;
}

int main(void) {
	cin >> T;
	for (int i = 0; i < T; i++) {
		solution();
	}
}
