#include <string>
#include <vector>
#include<algorithm>
#include<iostream>

using namespace std;

int mx = 0;
int mn = 100;
int n;
int arr[11];
int resultA;
int resultB;

void solve(vector<vector<int>>& users, vector<int>& emoticons) {
	int A = 0, B = 0;
	for (int i = 0; i < users.size(); i++) {
		int sum = 0;
		for (int j = 0; j < emoticons.size(); j++) {
			if (users[i][0] <= arr[j])
				sum += emoticons[j] / 100 * (100 - arr[j]);
		}
		if (sum >= users[i][1])
			A++;
		else
			B += sum;
	}
	if (resultA < A) {
		resultA = A;
		resultB = B;
	}
	else if (resultA == A) 
		resultB=max(resultB,B);
}

void DFS(int cnt, vector<vector<int>>& users, vector<int>& sales, vector<int>& emoticons) {
	if (cnt == n) {
		solve(users, emoticons);
		return;
	}
	for (int i = 0; i < sales.size(); i++) {
		arr[cnt] = sales[i];
		DFS(cnt + 1, users, sales, emoticons);
	}
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
	vector<int> answer;
	vector<int> sales{10,20,30,40};
	n = emoticons.size();
	DFS(0, users, sales, emoticons);
    answer.push_back(resultA);
    answer.push_back(resultB);
	return answer;
}
