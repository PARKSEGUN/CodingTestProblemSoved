DFS를 사용해서 쉽게 해결

#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

int dix[4]={0,0,1,-1};
int diy[4]={1,-1,0,0};

int BFS(int sx,int sy,int count,vector<string>& maps,bool lever){
    bool visited[111][111]={0,};
    queue<pair<int,int>> move;
    queue<int> time;
    move.push({sx,sy});
    time.push(count);
    visited[sx][sy]=true;
    while(move.size()!=0){
        int x=move.front().first;
        int y=move.front().second;
        int cnt=time.front();
        if(lever==false&&maps[x][y]=='L'){ 
            return BFS(x,y,cnt,maps,true);
        }
        else if(lever==true&&maps[x][y]=='E'){
            return cnt;
        }
        move.pop();
        time.pop();
        for(int i=0;i<4;i++){
            int dx=x+dix[i];
            int dy=y+diy[i];
            if(dx>=0&&dx<maps.size()&&dy>=0&&dy<maps[0].size()){
                if(maps[dx][dy]!='X'&&visited[dx][dy]==false){
                    visited[dx][dy]=true;
                    move.push({dx,dy});
                    time.push(cnt+1);
                }
            }
        }
    }
    return -1;
}

int solution(vector<string> maps) {
    int answer = 0;
    for(int i=0;i<maps.size();i++){
        for(int j=0;j<maps[i].size();j++){
            if(maps[i][j]=='S'){
               return BFS(i,j,0,maps,false);
            }
        }
    }
    return answer;
}
