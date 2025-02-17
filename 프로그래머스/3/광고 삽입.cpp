처음에 브루트포스 알고리즘을 사용해서 모든 시간을 초로 나타내면 360000 가 최대범위이고 이 내에서 모든 시간때에 시청한 모든 시청자를 탐색하면서 해당 시간에 +1 을 해주고
다시 처음부터 탐색하면서 주어진 광고시간 범위내에 시청자가 최대가 되는 시간을 구하도록 작성했지만
역시나 시간초과가 발생했고 이중 반복문을 사용하는 순간 시간초과가 발생한다는 것을 알게되었다.
모든 시청자를 탐색하면서 해당 시간에 +1을 해줘서 합을 구한다는 생각에서 '누적합' 이라는 알고리즘을 생각하게되었다.
시청자의 시작 시간과 끝 시간을 알면 시작시간에 +1, 끝 시간에 -1 을 해주게되고 다시 전체를 탐색하면서 앞에서 부터 더해주면서 진행하게되면 처음에 일일이 더했던
  브루트 포스 알고리즘과 같은 결과를 나타나게된다.
  광고 영상의 마지막부분은 포함하지 않는다는 부분에서 시간을 많이 쓰긴했지만 누적합 알고리즘을 떠올린것에 만족한다.


#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int playTime;
int advTime;
vector<pair<int, int>> logTime;
int viewer[3666666];

int changeSec(string str) {
    int hour = stoi(str.substr(0, 2));
    int min = stoi(str.substr(3, 2));
    int sec = stoi(str.substr(6, 2));
    return hour * 60 * 60 + min * 60 + sec;
}

string changeStr(int time) {
    int hour = time / 3600;
    time %= 3600;
    int min = time / 60;
    time %= 60;
    int sec = time;
    string result = "";
    if (hour < 10) {
        result += '0';
    }
    result += to_string(hour);
    result += ':';
    if (min < 10) {
        result += '0';
    }
    result += to_string(min);
    result += ':';
    if (sec < 10) {
        result += '0';
    }
    result += to_string(sec);

    return result;
}

void setting(string& play_time, string& adv_time, vector<string>& logs) {
    playTime = changeSec(play_time);
    advTime = changeSec(adv_time);
    for (int i = 0; i < logs.size(); i++) {
        logTime.push_back({ changeSec(logs[i].substr(0,8)),changeSec(logs[i].substr(9,8)) });
    }
}
string solution(string play_time, string adv_time, vector<string> logs) {
    setting(play_time, adv_time, logs);
    int startTime = 0;
    int endTime = 0;
    for (int i = 0; i < logTime.size(); i++) {
        endTime = max(endTime, logTime[i].second);
        viewer[logTime[i].first] += 1;
        viewer[logTime[i].second] -= 1;
    }
    for (int i = 1; i <= endTime; i++) {
        viewer[i] += viewer[i - 1];
    }
    long long sum = 0;
    long long result;
    long long  resultTime;
    for (int i = 0; i < advTime; i++) {
        sum += viewer[i];
    }
    result = sum;
    resultTime = startTime;
    for (int i = advTime; i <= endTime; i++) {
        sum += viewer[i];
        sum -= viewer[startTime++];
        if (sum > result) {
            result = sum;
            resultTime = startTime;
        }
    }
    return changeStr(resultTime);
}
