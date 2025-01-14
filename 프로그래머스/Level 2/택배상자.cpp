#include <string>
#include <vector>

using namespace std;

int solution(vector<int> order) {
	int answer = 0;
	vector<int> stack;
	int cnt = 0;
	for (int i = 1; i <= order.size(); i++) {
		if (order[cnt] == i) {
			answer++;
			cnt++;
		}
		else {
			while (stack.size() != 0 && stack.back() == order[cnt]) {
				cnt++;
				answer++;
				stack.pop_back();
			}
			stack.push_back(i);
		}
	}
	while (stack.size() != 0 && stack.back() == order[cnt]) {
		cnt++;
		answer++;
		stack.pop_back();
	}
	return answer;
}
