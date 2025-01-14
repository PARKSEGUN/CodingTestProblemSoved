dp를 사용해서 이전에 구한 값을 사용해서 시간을 줄여보려고 했지만 시간초과가 발생

#include <iostream>
#include<vector>
#include<algorithm>

using namespace std;

int N, M;
int answer = 1;
//(x,y)에서 길이가 len인 정사각형이 만들어지는지
void correct(int x, int y, int len, vector<vector<int>>& board) {
    if (x + len - 1 >= N || y + len - 1 >= M)
        return;
    for (int i = 0; i < len; i++) {
        for (int j = 0; j < len; j++) {
            if (board[x + i][y + j] == 0) {
                return;
            }
        }
    }
    answer = len;
    correct(x, y, len + 1, board);
}

int solution(vector<vector<int>> board)
{
    N = board.size();
    M = board[0].size();
    bool chk = false;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (board[i][j] != 0) {
                chk = true;
                correct(i, j, answer + 1, board);
            }
        }
    }
    if (chk == false)
        answer = 0;
    return answer * answer;
}

재귀함수와 정사각형이 이루어지는지 완전 탐색해서 시간초과가 발생한것 같다.
같은 dp 방식을 사용하지만 정사각형이 만들어질 수 있는 '조건'을 추가했다
현재 탐색하고자 하는 곳의 왼쪽과 위쪽과 왼쪽위(대각선)을 보고 정사각형이 이루어지는지 판단하고 dp 를 적용한다

#include <iostream>
#include<vector>
#include<algorithm>

using namespace std;

int solution(vector<vector<int>> board)
{
    int answer=board[0][0];
    for(int i=1;i<board.size();i++){
        for(int j=1;j<board[i].size();j++){
            if(board[i][j]!=0){
                board[i][j]=min({board[i-1][j],board[i][j-1],board[i-1][j-1]})+1;
                answer=max(board[i][j],answer);
            }
        }
    }
    return answer*answer;
}
