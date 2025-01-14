#include <string>
#include <vector>
#include<algorithm>
#include<iostream>
#include<cmath>

using namespace std;

string change(int n,int k){
    string str="";
    while(n!=0){
        str+=(n%k)+'0';
        n/=k;
    }
    reverse(str.begin(),str.end());
    return str;
}

//소수인지 판별하는 기능중에 for 문의 범위를 제대로 설정해주자
//얼마나 차이나겠어 라는 생각으로 그냥 x-1을 대입하면 확실하게 시간초과가 발생한다!
//주의하자!
//x의 어떤 값일 들어올 수 있는지를 생각 하는 것도 중요
bool demical(long long x){
    if(x<=1){return false;}
    for(int i=2;i<=sqrt(x);i++){
        if(x%i==0){return false;}
    }
    return true;
}

int solution(int n, int k) {
    int answer = 0;
    string str=change(n,k);
    //substr을 사용해서 해결하려 했지만 오류 발생 탐색을 하면서 넣어주어도 문제가 되지않으니
    //탐색하면서 0을 만날때까지 string 에 넣어주기
    string tmp="";
    for(int i=0;i<str.size();i++){
        if(str[i]=='0'&&tmp.size()!=0){
            if(demical(stoll(tmp))==true)answer++;
            tmp="";
        }
        else{tmp+=str[i];}
    }
    if(tmp.size()!=0){
        if(demical(stoll(tmp))==true)answer++;
    }
    return answer;
}
