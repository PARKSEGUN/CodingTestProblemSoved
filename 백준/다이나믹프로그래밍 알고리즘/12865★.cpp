DFS에다가 메모이제이션을 추가해서 해결해보려고 했지만 사용한 물건을 다르지만 무게와 가치가 같아서 생기는 반례발생
dp로 해결하려 했지만 해결하지 못해서 블로그에 배낭문제알고리즘 점화식을 통해 해결

#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
#include<memory>
#include<ctime>
#include<cstring>

using namespace std;

int N, K;
int W[111];
int V[111];
int dp[111][111111]; //[x][y] -> x 번째 물건, y무게일때 최대 가치값

void input() {
	cin >> N >> K;
	for (int i = 1; i <= N; i++) 
		cin >> W[i] >> V[i];
}

void solve() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= K; j++) {
			if (j - W[i] >= 0)
				dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
			else
				dp[i][j] =  dp[i - 1][j];
				
		}
	}
}

int main(void) {
	input();
	solve();
	cout << dp[N][K];
}
