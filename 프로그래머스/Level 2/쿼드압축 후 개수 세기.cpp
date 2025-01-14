범위 설정해주는 함수를 작성하고  DFS 방식으로 점점 범위를 4등분으로 쪼개 가면서 
나눠진 구역이 모두 1이거나 0일 경우를 찾아서 확인하고 다음 단계를 반복하는 식으로 해결한다
(문제를 쪼개고 시작해서 처음 arr 배열이 모두 같을때에 반례가 발생)
  


#include <string>
#include <vector>
#include <iostream>

using namespace std;

int cnt1, cnt0;

bool compare(int sx, int sy, int ex, int ey, vector<vector<int>>& arr) {
	for (int i = sx; i < ex; i++)
		for (int j = sy; j < ey; j++)
			if (arr[i][j] != arr[sx][sy])
				return false;
	(arr[sx][sy] == 1) ? cnt1++ : cnt0++;
	return true;
}

void quadTree(int sx, int sy, int ex, int ey, vector<vector<int>>& arr) {
	if (ex - sx == 1) {
		compare(sx, sy, ex, ey, arr);
		return;
	}
	//1분면
	if (compare(sx, sy, (sx + ex) / 2, (sy + ey) / 2, arr) == false)
		quadTree(sx, sy, (sx + ex) / 2, (sy + ey) / 2, arr);
	//2분면
	if (compare(sx, (sy + ey) / 2, (sx + ex) / 2, ey, arr) == false)
		quadTree(sx, (sy + ey) / 2, (sx + ex) / 2, ey, arr);
	//3분면
	if (compare((sx + ex) / 2, sy, ex, (sy + ey) / 2, arr) == false)
		quadTree((sx + ex) / 2, sy, ex, (sy + ey) / 2, arr);
	//4분면
	if (compare((sx + ex) / 2, (sy + ey) / 2, ex, ey, arr) == false)
		quadTree((sx + ex) / 2, (sy + ey) / 2, ex, ey, arr);
}

vector<int> solution(vector<vector<int>> arr) {
	vector<int> answer;
	if (compare(0, 0, arr.size(), arr.size(), arr) != true)
		quadTree(0, 0, arr.size(), arr.size(), arr);
	answer.push_back(cnt0);
	answer.push_back(cnt1);
	return answer;
}
