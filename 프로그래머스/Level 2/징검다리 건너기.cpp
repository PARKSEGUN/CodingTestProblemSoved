for 문으로 탐색하면서 최대 사람의 수를 찾기에는 시간초과 발생 stone(2억)
  stones의 값이 사람의 수를 뜻하기도 하기때문에 stone의 최대 최소를 구한뒤에 mid 값을 구해주고
  그에 따라 알맞은 수를 구한다 -> 이분탐색사용

#include <string>
#include <vector>
#include<algorithm>
#include<iostream>

using namespace std;

bool success(vector<int>& stones,int k,int mid){
    int cnt=0;
    for(int i=0;i<stones.size();i++){
        if(stones[i]<mid)
            cnt++;
        else
            cnt=0;
        if(cnt>=k)
            return false;
    }
    return true;
}

int solution(vector<int> stones, int k) {
    int answer = 0;
    int end=0;
    int start=987654321;
    for(int i=0;i<stones.size();i++){
        start=min(start,stones[i]);
        end=max(end,stones[i]);
    }
    while(start<=end){
        int mid=(start+end)/2;
        if(success(stones,k,mid)){
            start=mid+1;
            answer=max(mid,answer);
        }
        else
            end=mid-1;
    }
    return answer;
}
