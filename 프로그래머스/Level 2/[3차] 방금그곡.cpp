#include <string>
#include <vector>
#include<iostream>

using namespace std;

//#이 붙은 문자를 변형
string change(string& str) {
    string tmp = "";
    for (int i = 0; i < str.size(); i++) {
        if (str[i] == '#') {
            char x = tmp.back();
            tmp.pop_back();
            tmp.push_back(x + 32);
            continue;
        }
        else
            tmp.push_back(str[i]);
    }
    return tmp;
}

string solution(string m, vector<string> musicinfos) {
    m = change(m);
    string answer = "";
    int cmp = 0;
    for (int i = 0; i < musicinfos.size(); i++) {
        string start = musicinfos[i].substr(0, 5);
        string end = musicinfos[i].substr(6, 5);
        int startTime = stoi(start.substr(0, 2)) * 60 + stoi(start.substr(3, 2));
        int endTime = stoi(end.substr(0, 2)) * 60 + stoi(end.substr(3, 2));
        int runningTime = endTime - startTime;
        string str = musicinfos[i].substr(12);
        string name = str.substr(0, str.find(','));
        string music = str.substr(str.find(',') + 1);
        music = change(music);
        string tmp = "";
        int time = runningTime;
        while (music.size() <= time) {
            tmp += music;
            time -= music.size();
        }
        tmp += music.substr(0, time);
        cout << tmp << endl;
        if (tmp.find(m) != string::npos) {
            if (cmp < runningTime) {
                answer = name;
                cmp = runningTime;
            }
        }
    }
    if(answer=="")
        return "(None)";
    return answer;
}
