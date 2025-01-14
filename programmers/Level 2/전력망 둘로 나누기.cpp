주어진 wires를 순서대로 하나씩 잘라준다 이때 잘라주기위해서 connect 변수를 사용
둘이 아닌 셋이상으로 나누어질 수 있기때문에 allClear() 함수를 사용해서 1에서 시작해서 방문처리한 것과 방문 처리되지않은 정점에서 또 다시 방문처리했을때
모든 정점을 방문했는지 확인 방문했다면 결과 리턴

#include <string>
#include <vector>
#include<queue>
#include<iostream>
#include<cmath>

using namespace std;

bool connect[111][111];
bool visited[111] = { 0, };

void reset(int n) {
    for (int i = 1; i <= n; i++)
        visited[i] = false;
}

bool allClear(int n) {
    for (int i = 1; i <= n; i++)
        if (visited[i] == false)
            return false;
    return true;
}


int check(int start, int n, vector<vector<int>>& graph) {
    queue<int> q;
    q.push(start);
    visited[start] = true;
    int cnt = 0;
    while (q.size() != 0) {
        cnt++;
        int cur = q.front();
        q.pop();
        for (int i = 0; i < graph[cur].size(); i++) {
            if (connect[cur][graph[cur][i]] == true && visited[graph[cur][i]] == false) {
                visited[graph[cur][i]] = true;
                q.push(graph[cur][i]);
            }
        }
    }
    return cnt;
}

int solution(int n, vector<vector<int>> wires) {
    int answer = 100;
    //간선 매핑
    vector<vector<int>> graph(n + 1);
    for (int i = 0; i < wires.size(); i++) {
        connect[wires[i][0]][wires[i][1]] = true;
        connect[wires[i][1]][wires[i][0]] = true;
        graph[wires[i][0]].push_back(wires[i][1]);
        graph[wires[i][1]].push_back(wires[i][0]);
    }
    //순서대로 하나씩 끊기
    for (int i = 0; i < wires.size(); i++) {
        connect[wires[i][0]][wires[i][1]] = false;
        connect[wires[i][1]][wires[i][0]] = false;
        //1부터 무엇이 연결되어있는지 확인
        int x = check(1, n, graph);
        for (int i = 1; i <= n; i++) {
            if (visited[i] == false) {
                //1에서 방문하지 않았던 지점과 무엇이 연결되어있는지 확인
                int y = check(i, n, graph);
                if (allClear(n) == true)
                    answer = min(answer, abs(x - y));
                break;
            }
        }
        reset(n);
        connect[wires[i][0]][wires[i][1]] = true;
        connect[wires[i][1]][wires[i][0]] = true;
    }
    return answer;
}
