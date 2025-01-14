모든 경우를 탐색해야한다고 생각해서 dfs 적용
시간을 줄이기위해서 순열로 해결하려했지만 예제 3번 반례 -> 조합으로 진행
조합으로 모든 경우를 확인하고 확인한 배열을 정렬을 시켜주고 하나의 문자열로 합쳐주면 중복되는 값 제거가능

#include <string>
#include <vector>
#include<iostream>
#include<algorithm>
#include<unordered_set>

using namespace std;

bool visited[11];
string arr[11];
unordered_set<string> us;
//가려진 문자열과 알맞은지 확인
bool chkSame(string str1, string str2) {
	if (str1.size() != str2.size())
		return false;
	for (int i = 0; i < str1.size(); i++) {
		if (str2[i] != '*' && str1[i] != str2[i])
			return false;
	}
	return true;
}
//dfs로 조합경우 모두 확인
void dfs(int cnt, vector<string>& user_id, vector<string>& banned_id) {
	if (cnt == banned_id.size()) {
		vector<string> vec;
		for (auto x : arr)
			vec.push_back(x);
		sort(vec.begin(), vec.end());
		string str = "";
		for (auto x : vec)
			str += x;
		us.insert(str);
		return;
	}
	for (int i = 0; i < user_id.size(); i++) {
		if (visited[i] == false && chkSame(user_id[i], banned_id[cnt]) == true) {
			visited[i] = true;
			arr[cnt] = user_id[i];
			dfs(cnt + 1, user_id, banned_id);
			visited[i] = false;
		}
	}
}

int solution(vector<string> user_id, vector<string> banned_id) {
	dfs(0, user_id, banned_id);
	return us.size();
}
