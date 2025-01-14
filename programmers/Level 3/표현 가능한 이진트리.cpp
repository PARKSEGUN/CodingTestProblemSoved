주어진 number 를 이진수로 변환시킨다
변환시킨 이진수를 포화이진트리로 만들어주기위해서 앞에 알맞은 개수만큼0을 추가한다 ex) 111111 -> 0111111, 10000 -> 0010000
여기서만들어진 포화이진트리는 올바른 포화이진트리가 아니기때문에 올바른 이진트리인지 확인해준다
루트가 0이면 왼쪽서브트리와 오른쪽 서브트리에 1이 올수없다 -> 이과정을 재귀함수를통해 확인


#include <string>
#include <vector>
#include<iostream>
#include<algorithm>
#include<cmath>

using namespace std;
//이진수로 변환
string changeBinary(long long x){
    string str="";
    while(x!=0){
        str+=x%2+'0';
        x/=2;
    }
    return str;
}
//포화 이진트리로 변환
string maxBinary(string str){
    int x=2;
    while(str.size()>x-1){
        x*=2;
    }
    x=x-1-str.size();
    for(int i=0;i<x;i++){
        str+='0';
    }
    reverse(str.begin(),str.end());
    return str;
}
//올바른 포화이진트리인지 확인
bool correctTree(string str){
    if(str.size()==1){return true;}
    if(str[str.size()/2]=='0'){
        for(int i=0;i<str.size()/2;i++){
            if(str[i]=='1'){cout<<"fasdfasdf"<<endl;return false;}
        }
        for(int i=str.size()/2+1;i<str.size();i++){
            if(str[i]=='1'){cout<<"fasdfasdf"<<endl;return false;}
        }
    }
    else{
        string str1=str.substr(0,str.size()/2);
        string str2=str.substr(str.size()-str.size()/2,str.size()/2);
        return correctTree(str1)&correctTree(str2);
    }
    return true;
}

vector<int> solution(vector<long long> numbers) {
    vector<int> answer;
    for(int i=0;i<numbers.size();i++){
        string str=maxBinary(changeBinary(numbers[i]));
        if(correctTree(str)==true)answer.push_back(1);
        else if(correctTree(str)==false)answer.push_back(0);
            
    }
     return answer;
}
