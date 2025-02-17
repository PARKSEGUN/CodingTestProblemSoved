Union-Find 알고리즘을 사용해서 각각 정점의 최대 부모노드가 같은지를 확인한다
같다면 서로 연결되어있음을 의미한다

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int N, M;
int connect[222][222];
int plan[1111];
int parent[222];

void input() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> connect[i][j];
		}
	}
	for (int i = 0; i < M; i++) {
		cin >> plan[i];
		plan[i]--;
	}
}

int findParent(int x) {
	if (parent[x] == x)
		return x;
	return findParent(parent[x]);
}


void unionFind(int a, int b) {
	a = findParent(a);
	b = findParent(b);
	if (a < b)
		parent[b] = a;
	else
		parent[a] = b;
}

int main(void) {
	input();
	for (int i = 0; i < N; i++)
		parent[i] = i;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (connect[i][j] == 1) {
				unionFind(i, j);
			}
		}
	}
	for (int i = 0; i < M - 1; i++) {
		if (findParent(plan[i]) != findParent(plan[i + 1])) {
			cout << "NO";
			return 0;
		}
	}
	cout << "YES";
}
