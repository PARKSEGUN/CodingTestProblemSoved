어떤 연산자가 사용되었는지 확인
사용된 연산자를 dfs를 사용해서 조합으로 우선순위로 가능한 모든 경우의수를 확인
우선순위에 따른 계산(erase 함수를 사용)

#include <string>
#include <vector>
#include<iostream>
#include<algorithm>
#include<cmath>

using namespace std;

vector<char> oper; //+,-,* 의 유뮤확인
string str;
bool visited[3];
char arr[3];
long long result;
//어떤 연산자가 주어졌는지 확인
void chkOper() {
	bool chk[3] = { 0, };
	for (auto x : str) {
		if (x == '+')
			chk[0] = true;
		else if (x == '-')
			chk[1] = true;
		else if (x == '*')
			chk[2] = true;
	}
	if (chk[0] == true)
		oper.push_back('+');
	if (chk[1] == true)
		oper.push_back('-');
	if (chk[2] == true)
		oper.push_back('*');
}
//계산하기위한 함수
void calculate() {
	vector<long long> num;    //값 집합
	vector<char> cal;   //연산자 집합
	//값과 연산자를 분리해서 저장
	int idx = 0;
	for (int j = 0; j < str.size(); j++) {
		if (str[j] < '0' || str[j]>'9') {
			num.push_back(stoi(str.substr(idx, j - idx)));
			idx = j + 1;
			cal.push_back(str[j]);
		}
	}
	num.push_back(stoi(str.substr(idx)));
	//계산(erase 함수 사용)
	for (int i = 0; i < oper.size(); i++) {
		for (int j = 0; j < cal.size(); j++) {
			if (arr[i] == cal[j]) {
				if (arr[i] == '-') {
					num[j + 1] = num[j] - num[j + 1];
					num.erase(num.begin() + j);
				}
				else if (arr[i] == '+') {
					num[j + 1] = num[j] + num[j + 1];
					num.erase(num.begin() + j);
				}
				else if (arr[i] == '*') {
					num[j + 1] = num[j] * num[j + 1];
					num.erase(num.begin() + j);
				}
				cal.erase(cal.begin() + j);
				j--;
			}
		}
	}
	for (auto x : num)
		result = max(abs(x), result);
}
//dfs 사용해서 연산자 우선순위를 설정할 수 있는 모든경우 확인
void dfs(int cnt) {
	if (cnt == oper.size()) {
		calculate();
		return;
	}
	for (int i = 0; i < oper.size(); i++) {
		if (visited[i] == false) {
			visited[i] = true;
			arr[cnt] = oper[i];
			dfs(cnt + 1);
			visited[i] = false;
		}
	}
}

long long solution(string expression) {
	str = expression;
	chkOper();
	dfs(0);
	return result;
}
