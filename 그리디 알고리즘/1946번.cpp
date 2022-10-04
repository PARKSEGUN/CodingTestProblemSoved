#include<iostream>
#include <string>
#include <vector>
#include<memory>
#include<algorithm>
#include<vector>

using namespace std;

int N;
vector<pair<int, int>> ranking;
void Input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;
		ranking.push_back({ a,b });
	}
}


void solution() {
	int result = 1;
	sort(ranking.begin(), ranking.end());
	int mn = ranking[0].second;
	for (int i = 1; i < ranking.size(); i++) {
		if (ranking[i].second < mn) { result++; mn = ranking[i].second; }
	}
	cout << result<<endl;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	int T;
	cin >> T;
	for (int i = 0; i < T; i++) {
		Input();
		solution();
		ranking.clear();
	}
}
