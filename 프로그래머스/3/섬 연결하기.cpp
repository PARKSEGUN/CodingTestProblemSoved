프림 알고리즘을 사용해서 해결
한 정점에서 이어진 간선중 작은것을 선택하면서 큐에 넣어줘야하기때문에 우선순위큐 사용
사이클을 제거해주기위해서 이미 방문했던 곳이라면 패스
크루스칼 알고리즘(간선위주)은 간선을 가중치를 오름차순으로 정렬하고 작은것부터 하나씩 연결하면서 사이클이 만들어지는지 확인한다
이때 사이클을 확인하는 알고리즘으로는 union알고리즘으로 부모노드 즉, 현재의 노드중에 가장 높은 부모노드와 내가 연결하고자하는 노드의 가장 높은 부모노드가 같은 지를 
확인해서 서로 사이클이 형성되는지를 확인한다.

#include <string>
#include <vector>
#include<queue>
#include<iostream>

using namespace std;

int dis[111][111];
bool visited[111];

bool chkVisited(int n) {
    for (int i = 0; i < n; i++)
        if (visited[i] == false)
            return false;
    return true;
}

int solution(int n, vector<vector<int>> costs) {
    int answer = 0;
    for (int i = 0; i < costs.size(); i++) {
        int x = costs[i][0];
        int y = costs[i][1];
        int val = costs[i][2];
        dis[x][y] = val;
        dis[y][x] = val;
    }

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({ 0,0 });
    int cnt = 0;
    while (pq.size() != 0 && cnt != 100) {
        int cur = pq.top().second;
        //사이클이 생겻을때를 제거해주기위함
        if(visited[cur]==true){
            pq.pop();
            continue;
        }
        visited[cur] = true;
        int val = pq.top().first;
        cout << cur << " " << val << endl;
        answer += val;
        pq.pop();
        if (chkVisited(n) == true)
            return answer;

        for (int i = 0; i < n; i++) {
            if (visited[i] == false && dis[cur][i] != 0) {
                pq.push({ dis[cur][i],i });
            }
        }
        cnt++;
    }
    return answer;
}
