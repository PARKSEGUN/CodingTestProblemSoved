sources를 탐색하면서 BFS에 넣어주고 destination에 도달하는데에 걸리는 시간을 확인해준다

#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

int BFS(int n, int start, int end, vector<vector<int>>& road) {
    vector<bool> visited(n + 1);
    queue<int> move;
    queue<int> count;
    move.push(start);
    visited[start] = true;
    count.push(0);
    while (move.size() != 0) {
        int cur = move.front();
        int cnt = count.front();
        if (cur == end) {
            return cnt;
        }
        move.pop();
        count.pop();
        for (int i = 0; i < road[cur].size(); i++) {
            if (visited[road[cur][i]] == false) {
                visited[road[cur][i]] = true;
                move.push(road[cur][i]);
                count.push(cnt + 1);
            }
        }
    }
    return -1;
}

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    vector<int> answer;
    vector<vector<int>> road(n + 1);
    for (int i = 0; i < roads.size(); i++) {
        road[roads[i][0]].push_back(roads[i][1]);
        road[roads[i][1]].push_back(roads[i][0]);
    }
    for (int i = 0; i < sources.size(); i++) {
        answer.push_back(BFS(n, sources[i], destination, road));
    }
    return answer;
}
