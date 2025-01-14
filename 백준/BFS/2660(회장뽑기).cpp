BFS사용해서 모두 방문할 수 있는 최단거리 반환

#include<iostream>
#include<queue>
#include<vector>

using namespace std;

int N;
bool graph[55][55];
int result = 987654321;

void input() {
	cin >> N;
	while (1) {
		int a, b;
		cin >> a >> b;
		graph[a][b] = true;
		graph[b][a] = true;
		if (a == -1 && b == -1)
			break;
	}
}

bool connect(bool visited[]) {
	for (int i = 1; i < N; i++) {
		if (visited[i] == false)
			return false;
	}
	return true;
}

int BFS(int start) {
	queue<pair<int, int>> q;
	bool visited[55] = { 0, };
	q.push({ start,0 });
	visited[start] = true;
	int cnt = 0;
	while (q.size() != 0) {
		int cur = q.front().first;
		cnt = q.front().second;
		q.pop();
		for (int i = 1; i <= N; i++) {
			if (graph[cur][i] == true && visited[i] == false) {
				q.push({ i,cnt + 1 });
				visited[i] = true;
			}
		}
	}
	result = min(result, cnt);
	return cnt;
}

int main(void) {
	input();
	vector<int> vec;
	for (int i = 1; i <= N; i++)
		vec.push_back(BFS(i));
	int cnt = 0;
	for (int i = 0; i < vec.size(); i++) {
		if (vec[i] == result) {
			cnt++;
		}
	}
	cout << result << " " << cnt << endl;
	for (int i = 0; i < vec.size(); i++) {
		if (vec[i] == result) {
			cout << i + 1 << " ";
		}
	}
}
