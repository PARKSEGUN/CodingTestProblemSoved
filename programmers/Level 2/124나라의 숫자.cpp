//3진수와 비슷하지 않을까하다가 직접 만들어보면서 규칙을 찾음
//3진수는 0,1,2 를 사용 
//문제는 1,2,4 사용
//하지만 1 은 3진수로 1이고 문제에서도 1 이다 차이가 한칸씩 나기때문에 n+2 를 %3 해줫고
//n%3 이 나왔을때 -1을 해줘야 올바른 값이 나온다는 것을 확인

//추가로 string str="abc";
//str[2] 이 표현과 "abc"[2] 이 표현이 같다는 재밋는 사실을 확인했다

#include <string>
#include <vector>
#include <iostream>

using namespace std;

string solution(int n) {
    string answer = "";
    while(n>0){
        answer="124"[(n+2)%3]+answer;
        if(n%3==0)
            n--;
        n/=3;
    }    
    return answer;
}
