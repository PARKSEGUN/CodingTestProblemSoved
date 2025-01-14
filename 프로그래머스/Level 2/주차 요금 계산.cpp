map을 이용해서 해당 차량 번호에 대한 시간을 저장시키고
주어진 값들을 파싱해서 총 주차시간을 계산하고 계산식에 대입한다

#include <string>
#include <vector>
#include<unordered_map>
#include<set>
#include<iostream>
#include<cmath>

using namespace std;
//주어진 string을 정수형으로 변환
int changeTime(string str){
    int sum=0;
    sum+=stoi(str.substr(0,2))*60;
    sum+=stoi(str.substr(3,5));
    return sum;
}

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    unordered_map<string,int> allTime;  //총 주차시간을 위한 변수 
    unordered_map<string,int> calTime;  //주차시간 계산을 위한 변수
    set<string> s;   //총 사용되는 차량번호 저장, 차량 번호가 작은 자동차부터 꺼내기위함
    for(int i=0;i<records.size();i++){
        //주어진 정보 파싱
        int time=changeTime(records[i].substr(0,5));
        string num=records[i].substr(6,4);
        s.insert(num);
        bool trueIn=(records[i][11]=='I')?true:false;        
        //입차,출차 확인해서 누적 주차 시간 계산
        if(trueIn==true)
            calTime[num]=time;
        else{
            allTime[num]+=(time-calTime[num]);
            calTime[num]=-1;
        }
    }
    //입차된 이후, 출차된 내역이 없을때
    for(auto x:calTime){
        if(x.second!=-1){
            allTime[x.first]+=(1439-calTime[x.first]);
        }
    }
    //주차 요금 계산식
    for(auto x:allTime){
        if(x.second<=fees[0])
            allTime[x.first]=fees[1];
        else{
           allTime[x.first]=(
                fees[1]+ceil(((double)x.second-fees[0])/fees[2])*fees[3]
            );
        }
    }
    for(auto x:s)
        answer.push_back(allTime[x]);
    return answer;
}
