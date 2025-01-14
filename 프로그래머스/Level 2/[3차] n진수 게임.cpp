문제를 보고 총 문자열을 구해놓자고 생각 길이는 t*m 만큼 필요
진수 변환을 시켜야되는수를 1씩 증가시키면서 진수 변환
10이상 진수변환은 알파벳으로 표시되기에 따로 조건처리


#include <string>
#include <vector>
#include<iostream>

using namespace std;

string solution(int n, int t, int m, int p) {
    string answer = "";
    vector<int>allNum;
    allNum.push_back(0);
    int x=1;    //진법변환을 해야하는 수
    while(allNum.size()<t*m){
        //n진수 구하기
        vector<int> vec;
        int tmp=x;
        while(tmp!=0){
            vec.push_back(tmp%n);
            tmp/=n;
        }
        //뒤집기
        for(int i=vec.size()-1;i>=0;i--){
           allNum.push_back(vec[i]);
       }
        x++;
    }
    vector<int> vec;
    p--;
    while(p<t*m){
        if(allNum[p]>=10){  //10이상은 변환
            answer.push_back('A'+allNum[p]-10);
        }
        else answer.push_back(allNum[p]+'0');
        p+=m;
    }
    return answer;
}
