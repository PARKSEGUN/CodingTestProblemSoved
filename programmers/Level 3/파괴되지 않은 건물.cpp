처음에는 모든 배열을 탐색해주면서 해결하는 간단한 문제인지 알았지만 주어지는 skill 의 수가 많아서 시간초과 발생
다른 방법을 생각하다가 해결할 방법을 찾지 못해서 블로그 참조
누적합 알고리즘을 사용해서 어떠한 구역의 증가 또는 감소를 모서리에 누적합으로 표시해서 해결
ex)  4,4크기를 1,1 부터 2,2 까지 5증가 ->
  0,0,0,0
  0,5,0,-5
  0,0,0,0
  0,-5,0,5
  로 마킹을 해두고 가로, 세로 누적합을 구해주면 주어진 skill의 증가 또는 감소 해결
  

#include <string>
#include <vector>
#include <iostream>

using namespace std;

int map[1111][1111];

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
    for (int i = 0; i < skill.size(); i++) {
        int sign = 0;
        if (skill[i][0] == 1)
            sign = -1;
        else
            sign = 1;

        map[skill[i][1]][skill[i][2]] += skill[i][5] * sign;
        map[skill[i][1]][skill[i][4] + 1] += skill[i][5] * sign * -1;
        map[skill[i][3] + 1][skill[i][2]] += skill[i][5] * sign * -1;
        map[skill[i][3] + 1][skill[i][4] + 1] += skill[i][5] * sign;
    }

    //왼쪽에서 오른쪽으로 누적합 계산
    for (int i = 0; i < board.size(); i++) {
        for (int j = 1; j < board[i].size(); j++) {
            map[i][j] = map[i][j - 1] + map[i][j];

        }

    }
    //위에서 아래로 누적합 계산
    for (int i = 0; i < board[0].size(); i++) {
        for (int j = 1; j < board.size(); j++) {
            map[j][i] = map[j - 1][i] + map[j][i];
        }
    }
    //원래의 배열과 합침
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board[i].size(); j++) {
            board[i][j] += map[i][j];
        }
    }
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board[i].size(); j++) {
            if (board[i][j] > 0)
                answer++;
        }
    }
    return answer;
}
