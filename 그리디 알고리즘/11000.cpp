#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
#include<queue>

using namespace std;


int main(void) {
	int N;
	cin >> N;
	vector<pair<int, int>> vec;
	int result = 1;
	priority_queue<int, vector<int>, greater<int>> pq;

	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		vec.push_back({ x,y });
	}
	sort(vec.begin(), vec.end());
	pq.push(vec[0].second);
	for (int i = 1; i < vec.size();i++) {
		if (pq.top() <= vec[i].first) {
			pq.pop();
			pq.push(vec[i].second);
		}
		else
			pq.push(vec[i].second);
	}
	cout << pq.size();
}
