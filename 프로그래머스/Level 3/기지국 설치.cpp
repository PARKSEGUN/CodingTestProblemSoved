station을 탐색하면서 start, end(전파가 전달되지 않은 곳의 길이를 확인하기위해) 설정함
전파가 전달되지 않은 곳에 총 길이에 몇개에 기지국이 들어갈 수 있는지 확인

#include <iostream>
#include <vector>

using namespace std;

int solution(int n, vector<int> stations, int w)
{
	int answer = 0, start = 1, end = 0, range = w * 2 + 1;
	for (auto x : stations) {
		end = x - w - 1;
		if (end - start + 1 > 0)
			answer += ((end - start + 1) % range == 0) ? (end - start + 1) / range : (end - start + 1) / range + 1;
		start = x + w + 1;
		if (x == stations[stations.size() - 1] && x != n && n - start + 1 > 0)
			answer += ((n - start + 1) % range == 0) ? (n - start + 1) / range : (n - start + 1) / range + 1;
	}
	return answer;
}
