문제에 따라서 구름을 이동시키고 비를 내린후에 구름이 있는 좌표의 4가지 대각선 좌표를 확인해서 또다시 바구니를 증가시키고
저장된 물이 2이상인 지점에 구름을 표시한다 이때 전에 구름이였던 곳을 구름이 될 수 없다
문제의 순서대로 작성하면 된다고 생각함
map의 범위를 벗어나는 것, 구름의 이동시켰을때의 좌표에 구름이 있을때, 전에 구름이였던 곳은 구름이 될 수 없다는 점에 대해 조심해서 해결

#include<iostream>
#include<vector>
#include<string>

using namespace std;

int N, M;
int map[55][55];
vector<pair<int, int>> cloudMove;
int cloud[55][55];
int dir[8][2] = {
	{0,-1},
	{-1,-1},
	{-1,0},
	{-1,1},
	{0,1},
	{1,1},
	{1,0},
	{1,-1}
};

void input() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
		}
	}
	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		cloudMove.push_back({ a - 1,b });
	}
	cloud[N - 1][0] = 1;
	cloud[N - 1][1] = 1;
	cloud[N - 2][0] = 1;
	cloud[N - 2][1] = 1;
}
//x번째 이동을 수행하고 비를 내림
void moveAndRain(int idx) {
	vector<pair<int, int >> vec;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (cloud[i][j] == 1) {
				cloud[i][j] = 0;
				int x = i;
				int y = j;
				x += (dir[cloudMove[idx].first][0] * cloudMove[idx].second);
				(x % N < 0) ? x = x % N + N : x %= N;
				y += (dir[cloudMove[idx].first][1] * cloudMove[idx].second);
				(y % N < 0) ? y = y % N + N : y %= N;
				vec.push_back({ x,y });
			}
		}
	}
	//각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
	for (auto x : vec) {
		cloud[x.first][x.second] = 1;
		map[x.first][x.second] += 1;
	}
	//2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면,
	//대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (cloud[i][j] == 1) {
				int dix[4] = { 1,1,-1,-1 };
				int diy[4] = { -1,1,-1,1 };
				int sum = 0;
				for (int k = 0; k < 4; k++) {
					int x = i + dix[k];
					int y = j + diy[k];
					if (x >= 0 && x < N && y >= 0 && y < N && map[x][y]>0) {
						sum++;
					}
				}
				map[i][j] += sum;
			}
		}
	}
	//바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (cloud[i][j] == 1)
				cloud[i][j] = 0;
			else if (cloud[i][j] != 1 && map[i][j] >= 2) {
				cloud[i][j] = 1;
				map[i][j] -= 2;
			}
		}
	}
}

int main(void) {
	input();
	for (int i = 0; i < M; i++) {
		moveAndRain(i);
	}
	int result = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			result += map[i][j];
		}
	}
	cout << result;
}
