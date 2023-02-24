dfs를 사용해서 현재 탐색하고있는 부분의 밑의 값을 현재값으로 받고 또 그부분의 밑의 값을 탐색함
ex) 1 2 3    1의 밑에값인 2를 현재값으로 받고 2는 다시 3을 현재값으로 가르킨다
    2 3 1
모든 부분을 탐색해서 가장 많은 조합을 이루는 경우를 찾아낸다
  
#include<iostream>
#include<vector>
#include<string>
#include<algorithm>

using namespace std;

int N;
int arr[111];

void input() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
}

bool visited1[111];
bool visited2[111];
vector<int> vec;

bool makeSet(int cur) {
	if (visited2[arr[cur]] == true) {
		return false;
	}
	vec.push_back(cur);
	if (visited1[arr[cur]] == true) {
		return true;
	}
	if (cur == arr[cur]) {
		return true;
	}

	visited1[cur] = true;
	visited2[arr[cur]] = true;
	return makeSet(arr[cur]);
}

int main(void) {
	input();
	bool visited[111] = { 0, };
	vector<int> result;

	for (int i = 1; i <= N; i++) {
		if (visited[i]==false&&makeSet(i) == true) {
			for (auto x : vec) {
				result.push_back(x);
				visited[x] = true;
			}
		}
		for (int i = 1; i <= N; i++) {
			visited1[i] = false;
			visited2[i] = false;
		}
		vec.clear();
	}

	cout << result.size()<<endl;
	sort(result.begin(), result.end());
	for (auto x : result) {
		cout << x << endl;
	}
}
