최소 객실의 수를 알기위해 24시간을 분단위로 쪼개서 들어갈 배열을 만들어주고
해당하는 시간에 몇명이 사용하는지 알기위해서 입력받은 배열을 이용해서
저장해주고 분단위로 만들어놓은 배열중 가장 큰 값을 리턴


#include <string>
#include <vector>
#include<iostream>
#include<algorithm>

using namespace std;

int useTime[2222];

void process(vector<string> vec){
    int start=stoi(vec[0].substr(0,2))*60+stoi(vec[0].substr(3,2));
    int end=stoi(vec[1].substr(0,2))*60+stoi(vec[1].substr(3,2))+9;
    for(int i=start;i<=end;i++)
        useTime[i]++;
}

int solution(vector<vector<string>> book_time) {
    int answer = 0;
    for(auto x:book_time)
        process(x);
    for(int i=0;i<1440;i++)
        answer=max(answer,useTime[i]);
    return answer;
}
