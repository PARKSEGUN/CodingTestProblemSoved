모든 경우의수를 확인해서 목적지를 도달할수있는지를 확인하면 된다고 생각
DFS를 사용하면 시간초과 발생
BFS로 접근해서 현재 위치를 계속 갱신시켜주면서 도달할 수 있는 편의점이나 목적지를 탐색

#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#include<cmath>

using namespace std;

int N;	//편의점 개수
int hx, hy;		//집 좌표
vector<pair<int, int>> con;	//편의점 좌표
bool visited[111];
bool chk;	//목적지를 도달했는 알려주는 변수

void input() {
	cin >> N;
	cin >> hx >> hy;
	con.clear();
	for (int i = 0; i <= N; i++) {
		int x, y;
		cin >> x >> y;
		con.push_back({ x,y });	//con에 목적지 좌표도 마지막으로 넣어줌
	}
	
	chk = false;
}

void BFS(int xx, int yy) {
	queue<pair<int, int>> q;
	q.push({ hx,hy });
	while (q.size() != 0) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		for (int i = 0; i <= N; i++) {
			if (visited[i] == false && abs(con[i].first - x) + abs(con[i].second - y) <= 1000) {
				
				if (i == N) { chk = true; return; }
				
				q.push({ con[i].first,con[i].second });
				visited[i] = true;
			}
		}
	}
}

bool solution() {
	BFS(hx, hy);
	return chk;
}

int main(void) {
	int T;
	cin >> T;
	for (int i = 0; i < T; i++) {
		input();
		if (solution()) { cout << "happy" << endl; }
		else cout << "sad" << endl;
		for (int i = 0; i <= N; i++) {
			visited[i] = false;
		}
	}
}
