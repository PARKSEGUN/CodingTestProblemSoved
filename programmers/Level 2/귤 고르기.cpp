시간 효율을 위해서 주어진 귤중 가장 큰값으로 기준을 잡는다 (mx)
우리가 알아야되는것은 종류가 아니라 몇개의 종류를 사용했는지기때문에
크기가 몇인 귤이 몇개인지를 알아야되는것보다
같은 크기의 귤이 몇개씩 존재하는지를 알면 해결

#include <string>
#include <vector>
#include<queue>
#include<algorithm>
#include<iostream>

using namespace std;

int tan_cnt[11111111];
int solution(int k, vector<int> tangerine) {
    int mx=0;
    for(int i=0;i<tangerine.size();i++){
        tan_cnt[tangerine[i]]++;
        mx=max(mx,tangerine[i]);
    }
    sort(tan_cnt+0,tan_cnt+mx+1,greater());
    int idx=0;
    while(k>0){
        k-=tan_cnt[idx];
        idx++;
    }
    return idx;
}
