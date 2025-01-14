#include <string>
#include <vector>
#include<iostream>
#include<algorithm>

using namespace std;

vector<int> solution(string msg) {
    vector<int> answer;
    vector<string> dir;
    dir.push_back("0"); //인덱스를 맞추기한 0 주입
    for(int i=0;i<=25;i++){ //사전 설정
        string str="";
        str.push_back('A'+i);
        dir.push_back(str);
    }
    
    for(int i=0;i<msg.size();i++){
        string str="";
        str.push_back(msg[i]);
        int cnt=1;
        int sum=0;
        while(find(dir.begin(),dir.end(),str)!=dir.end()){
            sum=find(dir.begin(),dir.end(),str)-dir.begin();
            str.push_back(msg[i+cnt]);
            cnt++;
        }
        dir.push_back(str);
        answer.push_back(sum);
        i+=(cnt)-2; //알맞은 idx를 찾기위함
    }
    return answer;
}
