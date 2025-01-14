//처음에 visited를 2차원 변수로 설정해서 진행했지만 25번 예제를 통과하지못했다.
//각각 다른 방향에서 들어온 값들을 비교해줘야 하기때문에 [4] 를 추가해서 3차원으로 해결한다.
//https://school.programmers.co.kr/questions/30355

#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int dix[4]={0,0,1,-1};
int diy[4]={-1,1,0,0};
int visited[33][33][4];

void init(int N){
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            for(int k=0;k<4;k++){
                visited[i][j][k]=987654321;
            }
            
        }
    } 
    visited[0][0][0]=visited[0][0][1]=visited[0][0][2]=visited[0][0][3]=0;
}

int solution(vector<vector<int>> board) {
    int answer = 0;
    int N=board.size();
    init(N);
    queue<pair<int,int>> cur;
    queue<pair<int,int>> q; //<전 이동방향, 금액>
    cur.push({0,0});
    q.push({-1,-500});
    while(cur.size()!=0){
        int x=cur.front().first;
        int y=cur.front().second;
        int before=q.front().first;
        int sum=q.front().second;
        cur.pop();
        q.pop();
        for(int i=0;i<4;i++){
            int val=sum;
            int dx=x+dix[i];
            int dy=y+diy[i];
            if(dx>=0&&dx<N&&dy>=0&&dy<N&&board[dx][dy]==0){
                if(before==i)
                    val+=100;
                else
                    val+=600;
                if(visited[dx][dy][i]>=val){
                    visited[dx][dy][i]=val;
                    cur.push({dx,dy});
                    q.push({i,val});
                }
            }
        }
    }
    return min(visited[N-1][N-1][1],visited[N-1][N-1][2]);
}
