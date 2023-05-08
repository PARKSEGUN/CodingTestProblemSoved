주어진 이동경로와 시간을 매핑하고
어느지점에서 다른 지점까지의 최단 거리를 BFS로구해주는 move 함수 작성

#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;

int N, M, X;
vector<pair<int,int>> vec[1111];
int visited[1111];
int result;

void input() {
	cin>> N >> M >> X;
	for (int i = 0; i < M; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		vec[a].push_back({ b,c });
	}
}

void reset() {
	for (int i = 1; i <= N; i++)
		visited[i] = 987654321;
}

int move(int start,int end) {
	reset();
	queue<pair<int,int>> q;
	q.push({ start,0 });
	visited[start] = true;
	int answer = 987654321;
	while (q.size() != 0) {
		int cur = q.front().first;
		int sum = q.front().second;
		q.pop();
		if (cur == end)
			answer = min(answer, sum);
		for (int i = 0; i < vec[cur].size(); i++) {
			if (visited[vec[cur][i].first]> sum + vec[cur][i].second) {
				visited[vec[cur][i].first] = sum + vec[cur][i].second;
				q.push({ vec[cur][i].first,sum + vec[cur][i].second });
			}
		}
	}
	//cout << start << " " << end << " " << answer << endl;
	return answer;
}

int main(void) {
	input();
	for (int i = 1; i <= N; i++) {
		result = max(move(i, X) + move(X , i),result);
	}
	cout << result;
}
