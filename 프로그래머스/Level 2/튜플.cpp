문자열을 탐색하면서 {}안에있는 값들을 따로 파싱해서 2차원 벡터에 저장시킨다.
저장시킨 값들을 배열의 크기만 큼 오름차순 정렬시킨다.
정렬후 차례로 탐색하면서 중복되지않은 값을 answer에 push 해준다.
처음에 해결한 코드------------------------------
#include <string>
#include <vector>
#include<algorithm>
#include<sstream>

using namespace std;

bool cmp(vector<int> vec1,vector<int> vec2){
    return vec1.size()<vec2.size();
}

vector<int> solution(string s) {
    vector<int> answer; //return 값
    
    int start =1;//{} 쳐져있는 문자열을 뽑아내기위한 변수
    int end=2;
    vector<vector<int>> vec;    //주어진 s 를 정수 벡터에 저장하기위해
    while(end!=s.size()-1){
    while(s[start]!='{'){start++;}start++;
    while(s[end]!='}'){end++;}end++;
        if(end>=s.size()){break;}
    string str=s.substr(start,end-start);
    istringstream ss(str);  //start 부터 end 까지 정수 파싱
    string stringBuffer;
       
    vector<int>v;
    while(getline(ss,stringBuffer,',')){    //파싱 알고리즘
        v.push_back(stoi(stringBuffer));
        } 
        vec.push_back(v);
    }
    sort(vec.begin(),vec.end(),cmp);
    answer.push_back(vec[0][0]);
    for(int i=1;i<vec.size();i++){
        sort(vec[i].begin(),vec[i].end());
        bool chk=false; //중복된 값이 존재하지 않을때에는 i번째 배열의 마지막 값이 찾는 값이다
        for(int j=0;j<vec[i-1].size();j++){
            if(vec[i-1][j]!=vec[i][j]){answer.push_back(vec[i][j]);chk=true;break;}
        }
        if(chk==false){answer.push_back(vec[i][vec[i].size()-1]);}
    }
    return answer;
}

해결한 후에 다른사람의 풀이를 보니 수가 몇번들어왔는지를 확인하는 방법이있음
중복되는 수는 없기때문에 주어진 수가 몇번 들어왔는지를 확인하면 해결할 수 있는 문제였다.
-----------------------------다시해결한 코드---------------  
#include <string>
#include <vector>
#include<algorithm>
#include<unordered_map>

using namespace std;

vector<int> solution(string s) {
    vector<int> answer;
    vector<string> vec;
    unordered_map<string,int> um;
    string str="";
    for(int i=0;i<s.size();i++){
        if(s[i]>='0'&&s[i]<='9'){   //주어지는 숫자는 한자리수가아니기때문에 숫자가 연속으로 들어오는것을 확인
            str+=s[i];
        }
        else{
            if(str.size()>0){
                if(um.find(str)==um.end()){
                    vec.push_back(str);
                    um[str]=1;
                }
                else{um[str]++;}
                str.clear();
            }
        }
    }
    vector<pair<int,string>> cnt;
    for(int i=0;i<vec.size();i++){cnt.push_back({um[vec[i]],vec[i]});}
    sort(cnt.begin(),cnt.end());
    reverse(cnt.begin(),cnt.end());
    for(int i=0;i<cnt.size();i++){answer.push_back(stoi(cnt[i].second));}
    return answer;
}
