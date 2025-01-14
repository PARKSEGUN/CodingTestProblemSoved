문제를 보고 마지막 순서부터 시작해 앞으로 오면서 자신보다 큰 값을 찾으면 n^2 으로 시간초과 발생
우선순위큐를 사용해서 top의 값과 지금 현재 탐색하려고하는 값을 비교해서 현재값이 더 크다면 작아질 때까지 pop, 작다면 push 를 해줘서 해결
스택을 사용하면 좀더 간단하게 구현

#include<iostream>
#include<vector>
#include<queue>

using namespace std;

int N;
vector<pair<int, int>> vec;

int main(void) {
	cin >> N;
	vector<int> result(N);
	for (int i = 0; i < N; i++) {
		int x;
		cin >> x;
		while (vec.size() != 0 && vec[vec.size() - 1].first < x) 
			vec.pop_back();

		if (vec.size() != 0) 
			result[i] = vec[vec.size() - 1].second + 1;

		vec.push_back({ x,i });
	}

	for (auto x : result)
		cout << x << " ";
}
