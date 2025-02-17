문제의 내용을 이해하면 간단하게 접근할 수 있는 문제였다
시작 시간순으로 정렬
다음 업무 시작끼리 진행 이때 다 끝내거나 못끝내는 경우로 나눔
  다 끝냈을때는 이전에 처리하지못한 업무들을 확인해서 남은 업무 처리
  다 끝내지못했을때에는 이전에 처리하지못한 업무들의 묶음에 넣어주고 다음 업무 진행

#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<pair<string, pair<int, int>>> plan;
vector<pair<string, int>> notEnd;
vector<string> answer;

bool compare(pair<string, pair<int, int>> a, pair<string, pair<int, int>> b) {
    return a.second.first < b.second.first;
}
void changePlayTime(vector<string> plans) {
    int sum = 0;
    sum += stoi(plans[1].substr(0, 2)) * 60;
    sum += stoi(plans[1].substr(3, 2));
    plan.push_back({ plans[0],{sum,stoi(plans[2])} });
}
void workNotEnd(int time) {
    while (time > 0 && notEnd.size() != 0) {
        string name = notEnd.back().first;
        int& remainTime = notEnd.back().second;
        if (time >= remainTime) {   //남은 일 끝냄
            time -= remainTime;
            notEnd.pop_back();
            answer.push_back(name);
        }
        else {  //못 끝냄
            remainTime -= time;   
            return;
        }
    }
}
void work() {
    for (int i = 0; i < plan.size(); i++) {
        string name = plan[i].first;
        int start = plan[i].second.first;
        int playTime = plan[i].second.second;
        if (i == plan.size() - 1) {   //마지막 과제일때
            notEnd.push_back({ name,0 });
            while (notEnd.size() != 0) {
                answer.push_back(notEnd.back().first);
                notEnd.pop_back();
            }
        }
        else {
            int nextStart = plan[i + 1].second.first;
            if (nextStart - start >= playTime) {   //과제를 끝내고 시간이 남음
                answer.push_back(name);
                workNotEnd(nextStart - start - playTime);
            }
            else {  //과제가 밀림
                notEnd.push_back({ name,playTime - (nextStart - start) });
            }
        }
    }
}
vector<string> solution(vector<vector<string>> plans) {
    for (int i = 0; i < plans.size(); i++) {
        changePlayTime(plans[i]);
    }
    sort(plan.begin(), plan.end(), compare);
    work();
    return answer;
}
