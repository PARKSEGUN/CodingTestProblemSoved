#include <string>
#include <vector>
#include<unordered_map>

using namespace std;

bool notuse[26]; //사용가능한 알파벳
unordered_map<char,char> connect;

bool chktrue(string str){
    bool visited[26]={0,};
    for(int i=0;i<str.size();i++){
        if(notuse[str[i]-'A']==false){visited[str[i]-'A']=true;}
        else{
            if(visited[connect[str[i]]-'A']==true){visited[str[i]-'A']=true;}
            else{return false;}
        }
    }
    return true;
}

int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    for(int i=1;i<skill.size();i++){        notuse[skill[i]-'A']=true;    }
    for(int i=1;i<skill.size();i++){   connect[skill[i]]=skill[i-1];     }
    
    for(int i=0;i<skill_trees.size();i++){
        if(chktrue(skill_trees[i])==true){answer++;}
    }
    
    return answer;
}
