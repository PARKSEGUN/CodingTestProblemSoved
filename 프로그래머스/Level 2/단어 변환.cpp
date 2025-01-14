BFS를 사용해서 쉽게 해결

#include <string>
#include <vector>
#include<queue>
#include<iostream>

using namespace std;

bool visited[55];

int solution(string begin, string target, vector<string> words) {
    queue<pair<string,int>> q;
    q.push({begin,0});
    while(q.size()!=0){
        string str=q.front().first;
        int cnt=q.front().second;
        q.pop();
        if(str==target)
            return cnt;
        
        for(int i=0;i<words.size();i++){
            int diff=0;
            if(visited[i]==false){
                for(int j=0;j<str.size();j++)
                    if(str[j]!=words[i][j])
                        diff++;
                if(diff==1){
                    q.push({words[i],cnt+1});
                    visited[i]=true;
                }
            }
        }
    }
    return 0;
}
