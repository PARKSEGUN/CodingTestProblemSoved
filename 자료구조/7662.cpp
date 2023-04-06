maxheap과 minheap을 사용하는 두개의 우선순위큐를 사용
최댓값을 제거하면 maxheap에서 최솟값을 제거하면 minheap에서 제거
명령어가 끝난후에 남아있는 두개의 우선순위큐를 비교해서 같은 값이 존재하면 그중에 max값 min 값 추출
같은 값을 찾아내는 과정에서 시간초과 발생
visited 변수 사용해서 해결

#include<iostream>
#include<queue>
#include<vector>
#include<string>
#include<algorithm>

using namespace std;

//제거되었는지를 확인하기위한 변수
bool visited[1111111];

void solve() {
	priority_queue<pair<int, int>> pqMax;
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pqMin;
	int N;
	cin >> N;
	int countI = 0;
	int countD = 0;
	for (int i = 0; i < N; i++) {
		char tmp;
		cin >> tmp;
		if (tmp == 'I') {
			countI++;
			int x;
			cin >> x;
			pqMax.push({ x,i });
			pqMin.push({ x, i });
			visited[i] = true;
		}
		else {
			int x;
			cin >> x;
			if (x == 1) {
				//D -1 에 의해 제거된 값들을 제거해주기위해
				while (pqMax.size() != 0 && visited[pqMax.top().second] == false)
					pqMax.pop();
				//D 1 입력에 의한 제거
				if (pqMax.size() != 0) {
					visited[pqMax.top().second] = false;
					pqMax.pop();
				}
			}
			else {
				//D 1 에 의해 제거된 값들을 제거해주기위해
				while (pqMin.size() != 0 && visited[pqMin.top().second] == false)
					pqMin.pop();
				//D -1 입력에 의한 제거
				if (pqMin.size() != 0) {
					visited[pqMin.top().second] = false;
					pqMin.pop();
				}
			}
		}
		//모든 명령어를 실행하고 나머지 제거된 값들 정리
		while (pqMax.size() != 0 && visited[pqMax.top().second] == false)
			pqMax.pop();
		while (pqMin.size() != 0 && visited[pqMin.top().second] == false)
			pqMin.pop();
	}
	if (pqMax.size() == 0 && pqMin.size() == 0)
		cout << "EMPTY" << endl;
	else
		cout << pqMax.top().first << " " << pqMin.top().first << endl;
}

int main(void) {
	int T;
	cin >> T;
	for (int i = 0; i < T; i++) {
		solve();
	}
}
