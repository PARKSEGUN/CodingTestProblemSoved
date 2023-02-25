가장큰 값을 미리 탐색해서 그 값까지는 계속 주식을 구매하다가 그 값에 판매하는것이 최적해
값이 계속 올라가는 경우와 주식을 구매하지 않는 경우의 반례를 제거해주면서 해결하려했지만
가장 큰 값을 미리 구하는 과정 O(n) 의 과정의 추가로 시간초과 발생
가장 큰 값을 미리 구하지 않고 최적해를 구하기 위해서 뒤에서부터 탐색하면 효율적

#include<iostream>
#include<vector>

using namespace std;

int main(void) {
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		int n,x;
		cin >> n;
		vector<int> vec;
		for (int i = 0; i < n; i++) {
			cin >> x;
			vec.push_back(x);
		}
		long long result = 0;
		int mx = vec[n-1];
		for (int i = n - 2; i >= 0; i--) {
			if (mx > vec[i])
				result += mx - vec[i];
			else
				mx = vec[i];
		}
		cout << result<<endl;
	}
}
