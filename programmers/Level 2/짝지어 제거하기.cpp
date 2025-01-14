
--------------시간초과 코드-------------
erase를 사용해서 짝지어지는 부분이 있으면 제거하고 인덱스 맞춰주면서 while문 사용  
  
#include <iostream>
#include<string>
using namespace std;

int solution(string s)
{
    int i=1;
    while(i<s.size()){
        if(s[i-1]==s[i]){
            s.erase(i-1,2);
            if(i>1){i--;}
        }
        else{i++;}
    }
    return (s.size()==0)?1:0;
}

----------통과 코드-------------
erase는 효율적이지 못해서
겹치지 않는것들을 저장하는 string 을 하나 만들어서 
  
#include <iostream>
#include<string>
using namespace std;

int solution(string s)
{
    string str;
    for(int i=0;i<s.size();i++){
        if(str[str.size()-1]==s[i]){str.pop_back();}
        else {str.push_back(s[i]);}
    }

    return (str.size()==0)?1:0;
}
