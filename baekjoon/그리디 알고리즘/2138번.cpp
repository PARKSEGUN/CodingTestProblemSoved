DFS로 모든 경우를 확인한다면 2^100000 로 시간초과가 발생
그래서 if문을 걸어주어서 시간을 줄여보았다
경우의수는 현재스위치를 누르냐 안누르냐 이기때문에
이미 탐색이 끝난부분은 더이상 바뀔 수가 없기때문에 탐색이 끝난 부분이 만들고자하는 배열과 같지 않다면 return 을 해주었지만 시간초과와 메모리 초과
확인해보니 0번째 전구는 0번째와 1번째만 접근할 수 있었다
따라서 0번째 전구를 누르는지 안누르는지만 알 수 있다면 단일 for문으로 해결가능
ex) 000 -> 010 
  0번째 전구를 눌렀다고 생각했을때 110 이 되고 탐색을 시작한다 맨앞에 0번째 전구가 다르기 때문에 변경할 수 있는건 오직 1번째 스위치
  001 1번째 전구도 다르고 역시나 1번째 전구를 변경할 수 있는건 2번째 스위치 010
  만들고자하는 배열과 비교하면서 해결
  
  
  
#include <string>
#include <vector>
#include<unordered_map>
#include<iostream>

using namespace std;

int N;
vector<bool> startBulb;
vector<bool> endBulb;
int result=987654321;

void input() {
	cin >> N;
	string str1;
	string str2;
	cin >> str1 >> str2;
	for (int i = 0; i < N; i++) { startBulb.push_back(str1[i]-'0'); }
	startBulb.push_back(0);
	for (int i = 0; i < N; i++) { endBulb.push_back(str2[i] - '0'); }
	endBulb.push_back(0);
}

bool same(vector<bool>& cur) {
	for (int i = 0; i < N; i++) {
		if (cur[i] != endBulb[i])
			return false;
	}
	return true;
}

void solve(int sum,int cnt, vector<bool> bulb) {
	while (1) {
		if (cnt >= N - 1) { break; }

		if (bulb[cnt] != endBulb[cnt]) {
			bulb[cnt] = !bulb[cnt];
			bulb[cnt + 1] = !bulb[cnt + 1];
			bulb[cnt + 2] = !bulb[cnt + 2];
			sum++;
		}
		cnt++;
	}
	if (same(bulb) == true) { result = min(result, sum); return; }
}

int main(void) {
	input();
	//첫번째 전구를 누르지 않았을때
	solve(0,0, startBulb);

	//첫번째 전구를 눌렀을때
	startBulb[0] = !startBulb[0];
	startBulb[1] = !startBulb[1];
	solve(1,0, startBulb);
	if (result == 987654321)result = -1;
	cout << result;
}
