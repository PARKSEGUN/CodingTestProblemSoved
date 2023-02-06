문제를 보고 주사위에 있는 값들을 나타내는 dice 배열과 움직일때마다 알맞은 dice 인덱스에 접근 할 수 있는 diceDir 배열을 
만들어주어서 방향이 바뀔때마다 올바른 값을 꺼내올수 있도록 하려고 했지만 반례발생
다시 주사위의 전개도와 연결해주고 dice값이 저장되어있는 diceVal 배열을 만들어주고 움직일때마다 
바닥을 기준으로 전개도가 어떻게 바뀌는 지를 경우에 따라 설정

전개도 기준
   3
 2 0 1
   4
   5

#include<iostream>
#include<string>
#include<algorithm>
#include<vector>

using namespace std;

int N, M;
int sx, sy;
int K;
int map[22][22];
vector<int> order;
int diceVal[6];	//주사위 값
int dix[4] = { 0,0,-1,1 };
int diy[4] = { 1,-1,0,0 };

void input() {
	cin >> N >> M >> sx >> sy >> K;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
		}
	}
	for (int i = 0; i < K; i++) {
		int x;
		cin >> x;
		order.push_back(x-1);
	}
}

void print() {
	for (int i = 0; i < 6; i++) {
		cout << diceVal[i] << " ";
	}cout << endl;
}

int main(void) {
	input();
	int bottom = 0;	//시작 바닥면을 0으로 초기화
	for (int i = 0; i < K; i++) {
		int x = sx + dix[order[i]];
		int y = sy + diy[order[i]];
		if (x >= 0 && x < N && y >= 0 && y < M) {
			sx = x;
			sy = y;
			int chk0 = false;
			if (map[sx][sy] == 0)
				chk0 = true;

			if (order[i] == 0) {
				int tmp = diceVal[0];
				diceVal[0] = diceVal[1];
				diceVal[1] = diceVal[5];
				diceVal[5] = diceVal[2];
				diceVal[2] = tmp;
				if (chk0 == true) {
					map[sx][sy] = diceVal[0];
				}
				else {
					diceVal[0] = map[sx][sy];
					map[sx][sy] = 0;
				}
			}
			else if (order[i] == 1) {
				int tmp = diceVal[0];
				diceVal[0] = diceVal[2];
				diceVal[2] = diceVal[5];
				diceVal[5] = diceVal[1];
				diceVal[1] = tmp;
				if (chk0 == true) {
					map[sx][sy] = diceVal[0];
				}
				else {
					diceVal[0] = map[sx][sy];
					map[sx][sy] = 0;
				}
			}
			else if (order[i] == 2) {
				int tmp = diceVal[0];
				diceVal[0] = diceVal[3];
				diceVal[3] = diceVal[5];
				diceVal[5] = diceVal[4];
				diceVal[4] = tmp;
				if (chk0 == true) {
					map[sx][sy] = diceVal[0];
				}
				else {
					diceVal[0] = map[sx][sy];
					map[sx][sy] = 0;
				}
			}
			else if (order[i] == 3) {
				int tmp=diceVal[0];
				diceVal[0] = diceVal[4];
				diceVal[4] = diceVal[5];
				diceVal[5] = diceVal[3];
				diceVal[3] = tmp;
				if (chk0 == true) {
					map[sx][sy] = diceVal[0];
				}
				else {
					diceVal[0] = map[sx][sy];
					map[sx][sy] = 0;
				}
			}
			cout << diceVal[5] << endl;
		}
		else continue;

	}
}
