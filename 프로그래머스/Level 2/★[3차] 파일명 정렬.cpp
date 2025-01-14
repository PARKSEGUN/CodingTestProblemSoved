head와 number, tail을 구해주는 함수를 만들어주었고
sort의 비교조건을 설정해주기위해 cmp 함수를 만들어 주었다 head는 대소문자를 구별하지 않는다고 했기때문에
소문자로 일정하게 맞춰주는 조건을 추가했지만 계속 오답처리되었고 tail의 조건이 문제였다
head와 number의 값이 같다면 tail의 값에 상관없이 입력한 순서대로 나오게 해야된다
즉 안전한 정렬을 하라는 뜻이였다
sort() 함수를 써서 안전한 정렬을 만들 수 있는 방법이 생각나지않아서 블로그를 참조했고
stable_sort() 함수를 써주면 간단하게 해결되었다
입력 순서를 유지한다 -> 안전한 정렬을 하라 
는 것을 

#include <string>
#include <vector>
#include<iostream>
#include<algorithm>

using namespace std;

string findHead(string str) {
	for (int i = 0; i < str.size(); i++) {
		if (str[i] >= 'A' && str[i] <= 'Z')
			str[i] += 32;
	}
	string head = "";
	for (int i = 0; i < str.size(); i++)
		if (str[i] >= '0' && str[i] <= '9') {
			head = str.substr(0, i);
			return head;
		}
}

pair<int, string> findNumberTail(string str) {
	int number = 0;
	string tail = "";

	int x = 0, y = 0;
	for (int i = 0; i < str.size(); i++)
		if (str[i] >= '0' && str[i] <= '9') {
			x = i;
			break;
		}
	for (int i = x; i < str.size(); i++) {
		if (str[i] < '0' || str[i]>'9') {
			number = stoi(str.substr(x, i - x + 1));
			y = i;
			break;
		}
		if (i == str.size() - 1) {
			number = stoi(str.substr(x, i - x + 1));
			break;
		}
	}
	tail = str.substr(y);
	if (y == 0)
		tail = "";
	return { number,tail };
}

bool cmp(string str1, string str2) {
	string head1 = findHead(str1);
	int number1 = findNumberTail(str1).first;
	string tail1 = findNumberTail(str1).second;

	string head2 = findHead(str2);
	int number2 = findNumberTail(str2).first;
	string tail2 = findNumberTail(str2).second;

	if (head1 == head2) {
		return number1 < number2;
	}
	return head1 < head2;
}

vector<string> solution(vector<string> files) {
	stable_sort(files.begin(), files.end(), cmp);
	return files;
}
