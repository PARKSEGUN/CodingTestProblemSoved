//스택을 사용하고 괄호를 없애주면서 해결했다.

#include<string>
#include <iostream>
#include<vector>

using namespace std;

bool solution(string s)
{
    vector<char> vec;
    for(int i=0;i<s.size();i++){
        if(vec.size()!=0&&vec[vec.size()-1]=='('&&s[i]==')'){vec.pop_back();}
        else{vec.push_back(s[i]);}
    }
    return (vec.size()==0)?true:false;
}
