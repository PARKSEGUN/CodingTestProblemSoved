#include<iostream>
#include<queue>

using namespace std;

int N, M;
int map[1111][1111];
bool visited[1111][1111];
bool breakVisited[1111][1111];
int dix[4] = { 0,0,-1,1 };
int diy[4] = { 1,-1,0,0 };

void input() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < M; j++) {
			map[i][j] = str[j] - '0';
		}
	}
}

int BFS() {
	queue<pair<pair<int, int>,pair<int,bool>>> q;
	q.push({ { 0,0 },{0,false} });
	visited[0][0] = true;
	while (q.size() != 0) {
		int x = q.front().first.first;
		int y = q.front().first.second;
		int cnt = q.front().second.first;
		bool brk = q.front().second.second;
		q.pop();
		if (x == N - 1 && y == M - 1)
			return cnt;
		for (int i = 0; i < 4; i++) {
			int dx = x + dix[i];
			int dy = y + diy[i];
			if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
				if (brk == false) {
					if (map[dx][dy] == 0&&visited[dx][dy]==false) {
						visited[dx][dy] = true;
						q.push({ { dx,dy },{cnt + 1,brk} });
					}
					else  if (map[dx][dy] == 1&&visited[dx][dy]==false&&breakVisited[dx][dy]==false) {
						visited[dx][dy] = true;
						breakVisited[dx][dy] = true;
						q.push({ { dx,dy },{cnt + 1,true} });
					}
				}
				else if (brk == true) {
					if (map[dx][dy]==0&& breakVisited[dx][dy] == false) {
						breakVisited[dx][dy] = true;
						q.push({ { dx,dy },{cnt+1,brk} });
					}
				}
			}
		}
	}
	return -2;
}

int main(void) {
	input();
	cout << BFS()+1<<endl;
}
