문제에서 주어진 규칙을 어기는 실수의 종류가 있다.

"O"를 표시할 차례인데 "X"를 표시하거나 반대로 "X"를 표시할 차례인데 "O"를 표시한다.
  - o의 개수는 x의 개수와 같거나 x의 개수 +1 이여야한다.
  - x의 개수는 o의 개수 -1 이거나 o의 개수와 같아야 한다.
  
선공이나 후공이 승리해서 게임이 종료되었음에도 그 게임을 진행한다.
- 완성이 된 종류가 있는지 확인한다 -> 완성될 수 있는 8가지의 방법을 확인한 후에 o,x 의 완성된 개수를 파악한다.
  ㅇ가 완성이되었다면 o의 개수는 x의 개수보다 1개 많아야한다.
  x 가 완성이되었다면 x의 개수는 o의 개수와 같아야한다.
  둘다 완성이 되었다면 false를 리턴한다.
  
라는 조건에 맞춰서 조건식을 완성해주면 해결된다.

#include <string>
#include <vector>
#include <iostream>

using namespace std;

int map[3][3];
bool endMap[8][3][3] = {
    {
        {0,0,1},
        {0,0,1},
        {0,0,1}
    },
    {
        {0,1,0},
        {0,1,0},
        {0,1,0}
    },
    {
        {1,0,0},
        {1,0,0},
        {1,0,0}
    },
    {
        {1,1,1},
        {0,0,0},
        {0,0,0}
    },
    {
        {0,0,0},
        {1,1,1},
        {0,0,0}
    },
    {
        {0,0,0},
        {0,0,0},
        {1,1,1}
    },
    {
        {1,0,0},
        {0,1,0},
        {0,0,1}
    },
    {
        {0,0,1},
        {0,1,0},
        {1,0,0}
    },
};

pair<int, int> countMap() {
    int countX = 0, countO = 0;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (map[i][j] == 1) {
                countO++;
            }
            else if (map[i][j] == -1) {
                countX++;
            }
        }
    }
    return { countO,countX };
}

bool notEnd() {
    pair<int, int> count = countMap();
    int countO = count.first, countX = count.second;

    //개수로 비교
    if (countO != countX && countO != countX + 1) {
        return false;
    }

    //게임이 끝났는지 확인
    int completeO = 0, completeX = 0;   //완성된 O or X 의 개수
    for (int k = 0; k < 8; k++) {
        int endCountO = 0, endCountX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (endMap[k][i][j] == 1 && map[i][j] == 1) {
                    endCountO++;
                }
                else if (endMap[k][i][j] == 1 && map[i][j] == -1) {
                    endCountX++;
                }
            }
        }
        if (endCountO == 3) {
            completeO++;
        }
        else if (endCountX == 3) {
            completeX++;
        }
    }
    cout << completeO << " " << completeX << endl;
    if (completeO != 0 && completeX == 0) {
        if (countO != countX + 1) {
            return false;
        }
    }
    else if (completeX != 0 && completeO == 0) {
        if (countO != countX) {
            return false;
        }
    }
    else if (completeX != 0 && completeO != 0) {
        return false;
    }
    return true;
}

int solution(vector<string> board) {
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board[i].size(); j++) {
            if (board[i][j] == 'O') {
                map[i][j] = 1;
            }
            else if (board[i][j] == 'X') {
                map[i][j] = -1;
            }
            else {
                map[i][j] = 0;
            }
        }
    }
    return notEnd();
}
