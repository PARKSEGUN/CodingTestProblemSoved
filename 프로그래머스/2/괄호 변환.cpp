재귀함수를 사용해서 해결

#include <string>
#include <vector>

using namespace std;

pair<string, string> separate(string p) {
    int open = 0, close = 0;
    string u = "", v = "";
    for (int i = 0; i < p.size(); i++) {
        int x = p[i];
        if (x == '(')
            open++;
        else if (x == ')')
            close++;
        if (open != 0 && open == close) {
            u = p.substr(0, i + 1);
            v = p.substr(i + 1);
            return { u,v };
        }
    }
}

bool chkString(string str) {
    vector<char> vec;
    for (int i = 0; i < str.size(); i++) {
        if (vec.size() != 0) {
            if (vec.back() == '(' && str[i] == ')') {
                vec.pop_back();
                continue;
            }
        }
        vec.push_back(str[i]);
    }
    if (vec.size() == 0)
        return true;
    return false;
}

string recursive(string p) {
    if (p.size() == 0)
        return p;
    pair<string, string> ps = separate(p);
    string u = ps.first;
    string v = ps.second;
    if (chkString(u) == true)
        return u + recursive(v);
    else {
        string str = "(" + recursive(v) + ")";
        for (int i = 1; i < u.size() - 1; i++)
            (u[i] == '(') ? str += ')' : str += '(';
        return str;
    }
}

string solution(string p) {
    return recursive(p);
}
