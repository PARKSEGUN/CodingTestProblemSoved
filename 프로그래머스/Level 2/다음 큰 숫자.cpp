이진수 변환이 포인트

#include <string>
#include <vector>

using namespace std;

int change(int x){
    string str;
    int cnt=0;
    while(x!=0){
        if(x%2==1){cnt++;}
        str.push_back(x%2);
        x/=2;
    }
    return cnt;
}

int solution(int n) {
    int answer = n+1;
    while(1){
        if(change(n)==change(answer)){break;}
        else{answer++;}
    }
    return answer;
}
