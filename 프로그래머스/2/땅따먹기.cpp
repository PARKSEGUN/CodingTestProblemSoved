dp를 사용해서 updown방식으로 해결함

#include <iostream>
#include <vector>
#include<algorithm>

using namespace std;

int dp[111111][4];

int solution(vector<vector<int> > land)
{
    int answer=0;
    dp[0][0]=land[0][0];dp[0][1]=land[0][1];
    dp[0][2]=land[0][2];dp[0][3]=land[0][3];
    int n=land.size();
    int m=4;
    for(int i=1;i<n;i++){
        for(int j=0;j<m;j++){
            int mx=0;
            for(int k=0;k<m;k++){
                if(j!=k){mx=max(dp[i-1][k],mx);}
            }
            dp[i][j]=land[i][j]+mx;
        }
    }
    for(int i=0;i<m;i++){answer=max(dp[n-1][i],answer);}
    return answer;
}
