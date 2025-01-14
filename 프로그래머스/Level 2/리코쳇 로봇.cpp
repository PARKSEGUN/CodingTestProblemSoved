BFS를 사용해서 visited는 좌표만 설정하는 것이아니고 방향까지 넣어줘서 [4] 를 추가해준다


#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

int sx, sy, ex, ey;

bool visited[4][111][111];

pair<int, int> up(int x, int y, vector<string>& board) {
    while (x - 1 >= 0 && board[x - 1][y] != 'D') {
        x--;
    }
    return { x,y };
}
pair<int, int> down(int x, int y, vector<string>& board) {
    while (x + 1 < board.size() && board[x + 1][y] != 'D') {
        x++;
    }
    return { x,y };
}
pair<int, int> right(int x, int y, vector<string>& board) {
    while (y + 1 < board[0].size() && board[x][y + 1] != 'D') {
        y++;
    }
    return { x,y };
}
pair<int, int> left(int x, int y, vector<string>& board) {
    while (y - 1 >= 0 && board[x][y - 1] != 'D') {
        y--;
    }
    return { x,y };
}

int solution(vector<string> board) {
    int answer = 0;

    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board[i].size(); j++) {
            if (board[i][j] == 'R') {
                sx = i;
                sy = j;
            }
            else if (board[i][j] == 'G') {
                ex = i;
                ey = j;
            }
        }
    }
    queue<pair<int, int>> q;
    queue<int> move;
    q.push({ sx,sy });
    move.push(0);
    while (q.size() != 0) {
        int x = q.front().first;
        int y = q.front().second;
        int cnt = move.front();
        if(board[x][y]=='G'){
            return cnt;
        }
        q.pop();
        move.pop();
        pair<int, int> dir;
        if (visited[0][x][y] == false) {
            visited[0][x][y] = true;
            dir = right(x, y, board);
            q.push({ dir.first,dir.second });
            move.push(cnt + 1);
        }
        if (visited[1][x][y] == false) {
            visited[1][x][y] = true;
            dir = left(x, y, board);
            q.push({ dir.first,dir.second });
            move.push(cnt + 1);
        }
        if (visited[2][x][y] == false) {
            visited[2][x][y] = true;
            dir = up(x, y, board);
            q.push({ dir.first,dir.second });
            move.push(cnt + 1);
        }
        if (visited[3][x][y] == false) {
            visited[3][x][y] = true;
            dir = down(x, y, board);
            q.push({ dir.first,dir.second });
            move.push(cnt + 1);
        }
    }
    return -1;
}
