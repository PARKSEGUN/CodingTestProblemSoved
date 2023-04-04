사람의 이동은 BFS를 사용해서 확인하고 불은 리스트를 사용해서 불의 좌표를 기억해서 그 좌표에서번진 불의 좌표를 다시 저장하는 방식으로 구현 -> 시간초과 발생
불의 퍼짐을 매 단계 확인하는것이 아닌 미리 BFS를 사용해서 저장하고 BFS 로 사람의 이동을 하는 중에 불의 count 값과 비교해서 사람의 count값이더 적다면 가능
불의 count 값이 더 크다면 이미 번진 후 (count는 단계를 의미)

#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
#include<queue>

using namespace std;

int N, M;
char map[1111][1111];	//0 길 1 벽
bool visited[1111][1111];
int chkFire[1111][1111];
int sx, sy;
int dix[4] = { 0,0,1,-1 };
int diy[4] = { 1,-1,0,0 };

queue<pair<int,int>> fireQ;
queue<int> fireCount;

void fireMove() {
	while (fireQ.size() != 0) {
		int x = fireQ.front().first;
		int y = fireQ.front().second;
		int cnt = fireCount.front();
		fireQ.pop();
		fireCount.pop();
		for (int i = 0; i < 4; i++) {
			int dx = x + dix[i];
			int dy = y + diy[i];
			if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
				if (map[dx][dy] == '.' && chkFire[dx][dy] == 987654321) {
					fireQ.push({ dx,dy });
					fireCount.push(cnt + 1);
					chkFire[dx][dy] = cnt + 1;
				}
			}
		}
	}
}

void input() {
	cin >> N >> M;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			chkFire[i][j] = 987654321;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
			if (map[i][j] == 'F') {
				fireQ.push({ i,j });
				fireCount.push(0);
				chkFire[i][j] = 0;
			}
			else if (map[i][j] == 'J') {
				sx = i; sy = j;
			}
		}
	}

}

int main(void) {
	input();
	fireMove();
	queue<pair<int, int>> move;
	queue<int> count;
	move.push({ sx,sy });
	count.push(0);
	while (move.size() != 0) {
		int x = move.front().first;
		int y = move.front().second;
		int cnt = count.front();
		move.pop();
		count.pop();
		//종료조건
		if ((x == 0 || x == N - 1) || (y == 0 || y == M - 1)) {
			cout << cnt + 1;
			return 0;
		}
		for (int i = 0; i < 4; i++) {
			int dx = x + dix[i];
			int dy = y + diy[i];
			if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
				if (map[dx][dy] == '.' && chkFire[dx][dy] >cnt+1 && visited[dx][dy] == false) {
					move.push({ dx,dy });
					visited[dx][dy] = true;
					count.push(cnt + 1);
				}
			}
		}
	}
	cout << "IMPOSSIBLE";
}
