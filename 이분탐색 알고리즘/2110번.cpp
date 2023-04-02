맨앞의 공유기와 맨 뒤에 공유기를 설치해주고 그 두 공유기의 거리를 균등하게 나누는 방법을 생각했지만 최적해를 구하지 못했고
이분탐색을 사용해서 공유기 사이의 거리를 먼저 정해놓고 공유기를 설치했을때에 개수가 C 와 맞는지 확인해서 맞다면
두 공유기 사이의 거리중에서 가장 큰값을 유지한다.

#include <iostream>
#include <vector>
#include<algorithm>

using namespace std;

int N, C;
vector<int> vec;
int answer;

bool chk(int range) {
	int cnt = 1;
	int start = vec[0];
	for (int i = 1; i < N; i++) {
		if (start + range <= vec[i]) {
			cnt++;
			start = vec[i];
		}
	}
	if (cnt < C) {
		return false;
	}
	return true;
}

int main(void) {
	cin >> N >> C;
	for (int i = 0; i < N; i++) {
		int x;
		cin >> x;
		vec.push_back(x);
	}
	sort(vec.begin(), vec.end());
	int start = 1;
	int end = vec[N-1]-vec[0];
	while (start <= end) {
		int mid = (end+start) / 2;
		if (chk(mid) == false) {
			end = mid - 1;
		}
		else {
			start = mid + 1;
			answer = max(answer, mid);
		}
	}
	cout << answer;
}
