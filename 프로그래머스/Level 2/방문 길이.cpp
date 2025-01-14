//a,b 에서 c,d 로 가는 길을 방문 표시하기위해서 
//visited[a][b][c][d] 를 사용했지만 [c][d][a][b] 도 방문처리를 해야된다는 반례가 있었다.
#include <string>
using namespace std;

bool visited[11][11][11][11];   //[x][y][xx][yy] xy에서 xxyy로 가는 길을 지남

int solution(string dirs) {
    int answer = 0;
    int x=5,y=5;
    for(int i=0;i<dirs.size();i++){
        int dx=0,dy=0;
        if(dirs[i]=='R'){dx=0,dy=1;}
        else if(dirs[i]=='L'){dx=0,dy=-1;}
        else if(dirs[i]=='D'){dx=1,dy=0;}
        else if(dirs[i]=='U'){dx=-1,dy=0;}
        int dix=x+dx;
        int diy=y+dy;
        if(dix>=0&&dix<=10&&diy>=0&&diy<=10){
                if(visited[x][y][dix][diy]==false){
                    visited[x][y][dix][diy]=true;
                    visited[dix][diy][x][y]=true;
                    answer++;
                }
        x=dix;
        y=diy;
        }
    }
    return answer;
}
