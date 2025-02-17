주어진 괄호를 queue 속에 저장시켜주고 q에 들어있는 괄호가 올바른 괄호인지를 알려주는 함수 하나 만든다.
그리고 따로 회전시키는 코드도 만들어줘서 q의 크기만큼 회전시키면서 올바른 괄호가 몇번 발생하는지 return 한다.

#include <string>
#include <vector>
#include<queue>

using namespace std;

bool correct(queue<char> q){
    char open[3] = { '(','{','[' };
    char close[3] = { ')','}',']' };
    vector<char> vec;
    vec.push_back(q.front()); q.pop();
    while (q.size() != 0) {
        bool chk = false;
        if (vec.size() == 0) { vec.push_back(q.front()); q.pop(); }
        else {
            for (int i = 0; i < 3; i++) {
                if (vec[vec.size() - 1] == open[i]) {
                    if (q.front() == close[i]) {
                        vec.pop_back(); q.pop(); chk = true; break;
                    }
                }
            }
            if (chk == false) { vec.push_back(q.front()); q.pop(); }
        }
    }
    if (vec.size() == 0) { return true; }
    else { return false; }
}
int solution(string s) {
    int answer = 0;
    queue<char> q;
    for(int i=0;i<s.size();i++){q.push(s[i]);}
    for(int i=0;i<s.size();i++){
        if(correct(q)==true){answer++;}
        q.push(q.front());
        q.pop();
    }
    return answer;
}
