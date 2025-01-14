//처음에 접했던 방법
//dp[x]=y 주어진 N으로 x를 만들 수있는 최소한의 N의 개수를 y 로 dp를 설정해주었다.
//하지만 수가 붙어있는 즉 5 3개를 사용해서 555를 만들어내는 경우를 해결하지 못해서
//블로그를 검색했다. (https://velog.io/@euneun/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-N%EC%9C%BC%EB%A1%9C-%ED%91%9C%ED%98%84-DP-%EB%8F%99%EC%A0%81%EA%B3%84%ED%9A%8D%EB%B2%95-C)

//처음에 접했던 방법의 반대로 dp를 설정해주었다.
//dp[x] 는 주어진 N을 x개 갖고 만들 수 있는 수를 모두 저장한다 
// 예를 들어 주어진 N 이 5 라면 
// dp[1]=5
// dp[2]= 55,5+5,5*5,5/5,5-5 
// 그렇다면 dp[3]은 555, dp[1]*/-+dp[2], dp[2]/*-+dp[1] 이 모든 가능한 경우이다.
// 최대  N 을 8개를 갖고 만들 수 있는 수를 모두 구한다음 주어진 number 존재하는지 확인한다.
// 여기서 x개를 갖고 만들 수 있는 값 중에 중복되는 값을 없애고 (set 사용) 음수 또는 나눠지지 않는수 ex)4/5 와 같은
// 값들을 제거해주면 더 효율적으로 결과를 도출할 수 있다.

#include <string>
#include <vector>
#include<iostream>
#include<unordered_set>

using namespace std;

int NN(int n,int cnt){  //N을 cnt번 붙여서 만든 수를 리턴해주는 함수
    int x=0;
    for(int i=0;i<cnt;i++){
        x=x*10+n;
    }
    return x;
}

int solution(int N, int number) {
    unordered_set<int> dp[11];  //중복을 제거 해주기위한 set (정렬은 필요없기에 unordered_set 사용)
    dp[1].insert(N);    //N한개로 만들 수 있는 수는 N
    for(int i=2;i<=8;i++){  //최대 8개 갖고 만들 수있는 수 확인
        dp[i].insert(NN(N,i));  //붙여서 만들수 있는수 insert
        for(int j=1;j<i;j++){   //모든 경우를 확인 하기위한 j 설정
            for(auto x:dp[j]){  //dp[j]에 들어있는 모든 수 탐색
                for(auto y:dp[i-j]){    //dp[i-j]에 들어있는 모든 수  탐색
                    dp[i].insert(x+y);  //+한 값 insert
                    if(x-y>0){dp[i].insert(x-y);}   //-한 값 insert
                    dp[i].insert(x*y);  //*한 값 insert
                    if(x/y>0){dp[i].insert(x/y);}   // / 한값 insert
                }
            }
        }
    }
    //구한 모든 수중에서 number 있는지 확인해서 몇개 사용했는지 return
    for(int i=1;i<=8;i++){  
        for(auto x:dp[i]){
            if(x==number){
                return i;
            }
        }
    }
    //없다면 -1 리턴
    return -1;
}
