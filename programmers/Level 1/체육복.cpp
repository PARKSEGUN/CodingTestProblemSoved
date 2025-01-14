앞 뒤 학생들을 비교해서 각각의 조건에 맞는 방법으로 체육복을 나눠주게 했지만 계속 반례가 발생해서
생각 하던 중에 입력으로 들어오는 reserve 의 값이 정렬되어서 들어온다는 조건이 없었기때문에 반례가 발생했다는 것을 알고 정렬해주는 부분을 추가했다.
  
  
#include <string>
#include <vector>
#include<algorithm>
using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = 0;
    vector<bool> chk(n+2,true);

    for(int i=0;i<lost.size();i++){chk[lost[i]]=false;}

     for(int i=0;i<reserve.size();i++){
         if(chk[reserve[i]]==false){chk[reserve[i]]=true;reserve[i]=-1;}
     }
    sort(reserve.begin(),reserve.end());
    for(int i=0;i<reserve.size();i++){
        if(reserve[i]==-1){continue;}
        if(chk[reserve[i]-1]==false&&chk[reserve[i]+1]==false){chk[reserve[i]-1]=true;}
        else if(chk[reserve[i]-1]==false&&chk[reserve[i]+1]==true){chk[reserve[i]-1]=true;}
        else if(chk[reserve[i]-1]==true&&chk[reserve[i]+1]==false){chk[reserve[i]+1]=true;}
    }

    for(int i=1;i<=n;i++){        if(chk[i]==true){answer++;}    }


    return answer;
}
