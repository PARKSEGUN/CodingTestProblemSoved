#include <iostream>
#include <string>
#include <vector>

using namespace std;

int solution(string str1, string str2) {
    int answer = 0;
    //소문자를 대문자로 일치
    for(int i=0;i<str1.size();i++){
        if(str1[i]>='a'&&str1[i]<='z'){str1[i]-=32;}
    }
    for(int i=0;i<str2.size();i++){
        if(str2[i]>='a'&&str2[i]<='z'){str2[i]-=32;}
    }
    //2개씩 잘라서 vector에 저장
    vector<string> vec1;
    vector<string> vec2;
    for(int i=1;i<str1.size();i++){
        if(str1[i-1]>='A'&&str1[i-1]<='Z'&&str1[i]>='A'&&str1[i]<='Z'){
            string str="";
            str+=str1[i-1];str+=str1[i];
            vec1.push_back(str);
        }
    }
    for(int i=1;i<str2.size();i++){
        if(str2[i-1]>='A'&&str2[i-1]<='Z'&&str2[i]>='A'&&str2[i]<='Z'){
              string str="";
            str+=str2[i-1];str+=str2[i];
            vec2.push_back(str);
        }
    }
    //같은 문자열이 몇개인지 확인
    if(vec1.size()==0&&vec2.size()==0){return 65536;}
    bool visited[1111]={0,};
    double same=0;
    for(int i=0;i<vec1.size();i++){
        for(int j=0;j<vec2.size();j++){
            if(vec1[i]==vec2[j]&&visited[j]==false){
                visited[j]=true;
                same++;
                break;
            }
        }
    }
    //정답 리턴
    return same/(vec1.size()+vec2.size()-same)*65536;
}
