10일동안 회원 자격을 준다고 했기때문에
discount를 10개씩 묶어서 비교하고 맨앞부분을 없애고 맨 뒤에부분을 추가해주는 형식으로 작성
그 탐색한 10개의 부분의 개수가 want의 개수와 맞는지 비교

#include <string>
#include <vector>
#include<unordered_map>
#include<iostream>

using namespace std;

unordered_map<string,int> um;
int result;


void chkSame(vector<string> want,vector<int> number){
    for(int i=0;i<number.size();i++){
        if(number[i]!=um[want[i]])
            return;
    }
    result++;
}

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    for(int i=0;i<10;i++)
        um[discount[i]]++;
    int start=0;
    chkSame(want,number);
    while(start+10<discount.size()){
        um[discount[start]]--;
        um[discount[start+10]]++;
        chkSame(want,number);
        start++;
    }
    return result;
}
