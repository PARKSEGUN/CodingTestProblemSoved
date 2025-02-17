//BFS를 다시한번 상기시키는 좋은 문제였다.

#include<vector>
#include<queue>

using namespace std;

int dx[4]={0,0,1,-1};
int dy[4]={1,-1,0,0};
bool visited[111][111];

int solution(vector<vector<int> > maps)
{
    int answer = 0;
    int n=maps.size();
    int m=maps[0].size();
    queue<pair<int,int>> q;
    queue<int> cntq;
    q.push({0,0});visited[0][0]=true;
    cntq.push(1);
    while(q.size()!=0){
        int x=q.front().first;
        int y=q.front().second;
        int cnt=cntq.front();
        q.pop();cntq.pop();
        if(x==n-1&&y==m-1){return cnt;}
        for(int i=0;i<4;i++){
            int dix=x+dx[i];
            int diy=y+dy[i];
            if(dix>=0&&dix<n&&diy>=0&&diy<m){
                if(visited[dix][diy]==false&&maps[dix][diy]==1){
                    visited[dix][diy]=true;
                    q.push({dix,diy});cntq.push(cnt+1);
                }
            }
        }
    }
    return -1;
}
