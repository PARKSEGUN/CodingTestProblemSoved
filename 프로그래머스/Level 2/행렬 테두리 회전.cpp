입력받은 queries를 매개변수로 받는 함수를 만들어주고
이 함수는 deque를 이용해서 맵을 조건에 맞게 회전시킨다 이때 최소값을 구한다
주어진 테두리를 deque에 넣고 마지막 값을 맨 앞으로 오게해주면 조건이 성립

#include <string>
#include <vector>
#include<iostream>
#include<queue>
#include<algorithm>

using namespace std;

int map[111][111];

void mapSetting(int rows, int columns) {
	int k = 1;
	for (int i = 1; i <= rows; i++) {
		for (int j = 1; j <= columns; j++) {
			map[i][j] = k;
			k++;
		}
	}
}

int rotation(vector<int>queries) {
	int small = 987654321;
	deque<int> q;
	for (int i = queries[1]; i <= queries[3]; i++) //위
		q.push_back(map[queries[0]][i]);
	for (int i = queries[0] + 1; i <= queries[2]; i++) //오른쪽
		q.push_back(map[i][queries[3]]);
	for (int i = queries[3] - 1; i >= queries[1]; i--) //아래
		q.push_back(map[queries[2]][i]);
	for (int i = queries[2] - 1; i >= queries[0] + 1; i--) //왼쪽
		q.push_back(map[i][queries[1]]);

	int x = q.back();
	q.pop_back();
	q.push_front(x);
    
	for (int i = queries[1]; i <= queries[3]; i++) { //위
		map[queries[0]][i] = q.front();
		small = min(q.front(), small);
		q.pop_front();
	}
	for (int i = queries[0] + 1; i <= queries[2]; i++) { //오른쪽
		map[i][queries[3]] = q.front();
		small = min(q.front(), small);
		q.pop_front();
	}
	for (int i = queries[3] - 1; i >= queries[1]; i--) { //아래
		map[queries[2]][i] = q.front();
		small = min(q.front(), small);
		q.pop_front();
	}
	for (int i = queries[2] - 1; i >= queries[0] + 1; i--) { //왼쪽
		map[i][queries[1]] = q.front();
		small = min(q.front(), small);
		q.pop_front();
	}
	return small;
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
	vector<int> answer;
	mapSetting(rows, columns);
	for (auto x : queries) 
		answer.push_back(rotation(x));
    return answer;
}
