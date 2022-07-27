#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <vector>
#include<windows.h>

#define endl "\n"

using namespace std;

int dice[10];
//0은 시작, 99는 도착을 의미
int road1[26] = { 0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,-1,-1,-1,-1,-1 };
int road2[18] = { 0,2,4,6,8,10,13,16,19,25,30,35,40 ,-1,-1,-1,-1,-1 };
int road3[22] = { 0,2,4,6,8,10,12,14,16,18,20,22,24,25,30,35,40 ,-1,-1,-1,-1,-1 };
int road4[28] = { 0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,28,27,26,25,30,35,40 ,-1,-1,-1,-1,-1 };

int horse[4][2];    //각각의 말들은 몇번째 길에 있는지 그 길의 몇번째 칸에 있는지를 알아야함
vector<vector<int>> vec;
void Input() {
	for (int i = 0; i < 10; i++) {
		cin >> dice[i];
	}
	for (int i = 0; i < 4; i++) {
		horse[i][0] = 0;    horse[i][1] = 0;       //1번째 길에 0번째 칸으로 초기화
	}
	vector<int> vv = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
	vector<int> vv1 = { 0,1,2,3,4,5,21,22,23,24,30,31,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
	vector<int> vv2 = { 0,1,2,3,4,5,6,7,8,9,10,28,29,24,30,31,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
	vector<int> vv3 = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,27,26,25,24,30,31,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
	vec.push_back(vv); vec.push_back(vv1); vec.push_back(vv2); vec.push_back(vv3);
}
int result;

int arr[10];
//도착한 칸에 다른 말이있는지 확인
bool test(int n) {
	for (int i = 0; i < 4; i++) {
		if (i != n) {
			if (vec[horse[n][0]][horse[n][1]] == vec[horse[i][0]][horse[i][1]]) {
				if (vec[horse[n][0]][horse[n][1]] != -1&& vec[horse[i][0]][horse[i][1]] != -1) {
					return true;
				}
			}
		}
	}
	return false;
}

void DFS(int cnt, int sum) {
	if (cnt == 10) {
		if (sum > result) {
			for (int i = 0; i < 10; i++) { cout << arr[i] << " "; }cout << "sum : " << sum << endl;
		}
		result = max(result, sum);

	}
	else {
		for (int i = 0; i < 4; i++) {
			//게임판의 도착칸에 도착했다면 이동을 마침
			if (vec[horse[i][0]][horse[i][1]] == -1) { continue; }

			horse[i][1] += dice[cnt];

			//맵변경해주기
			if (vec[horse[i][0]][horse[i][1]] == 5) {
				horse[i][0] = 1;
			}
			else if (vec[horse[i][0]][horse[i][1]] == 10) {
				horse[i][0] = 2;
			}
			else if (vec[horse[i][0]][horse[i][1]] == 15) {
				horse[i][0] = 3;
			}

			//말의 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다.
			if (test(i) == true) {
				
				if (vec[horse[i][0]][horse[i][1]] == 5) {
					horse[i][0] = 0;
				}
				else if (vec[horse[i][0]][horse[i][1]] == 10) {
					horse[i][0] = 0;
				}
				else if (vec[horse[i][0]][horse[i][1]] == 15) {
					horse[i][0] = 0;
				}
				horse[i][1] -= dice[cnt];
				continue;
			}
			int val = 0;

			if (horse[i][0] == 0) { val = road1[horse[i][1]]; }
			else if (horse[i][0] == 1) { val = road2[horse[i][1]]; }
			else if (horse[i][0] == 2) { val = road3[horse[i][1]]; }
			else if (horse[i][0] == 3) { val = road4[horse[i][1]]; }
			if (val == -1) { val = 0; }

			//	cout << val <<" "<< horse[i][0] << " " << horse[i][1] << endl; system("pause");
			arr[cnt] = i;
				DFS(cnt + 1, sum + val);
			if (vec[horse[i][0]][horse[i][1]] == 5) {
				horse[i][0] = 0;
			}
			else if (vec[horse[i][0]][horse[i][1]] == 10) {
				horse[i][0] = 0;
			}
			else if (vec[horse[i][0]][horse[i][1]] == 15) {
				horse[i][0] = 0;
			}
			horse[i][1] -= dice[cnt];
		}
	}
}

void Solve() {
	DFS(0, 0);
	cout << result;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	Input();
	Solve();
}
