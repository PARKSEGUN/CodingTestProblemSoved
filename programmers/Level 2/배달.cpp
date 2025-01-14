다익스트라 알고리즘을 사용해서 현재 (누적거리값(val)+현재지점에서 다음 지점 거리 값) 이 시작점부터 다음 지점까지의 거리 값보다 작을 때, 
계속 초기화 시켜주면서 진행한다
BFS와는 다르게 우선순위큐를 사용하기때문에 거리값이 낮은 순으로 저장 시켜주면서 진행한다.

#include <iostream>
#include <vector>
#include<queue>
#include<algorithm>

using namespace std;

int dis[55][55];
bool visited[55];
void dijkstra(int N) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; //{cur,val}
    pq.push({ 0,1 });
    while (pq.size() != 0) {

        int cur = pq.top().second;
        int val = pq.top().first;
        visited[cur] = true;
        pq.pop();
        for (int i = 1; i <= N; i++) {
            if (visited[i] == false) {
                int next = i;
                int nextVal = val + dis[cur][next];
                if (dis[1][next] >= nextVal) {
                    dis[1][next] = nextVal;
                    dis[next][1] = nextVal;
                    pq.push({ nextVal,next });
                }
            }
        }
    }
}

int solution(int N, vector<vector<int> > road, int K) {
    int answer = 0;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            dis[i][j] = 987654321;
        }
    }

    for (int i = 0; i < road.size(); i++) {
        int x = road[i][0];
        int y = road[i][1];
        int val = road[i][2];
        dis[x][y] = min(dis[x][y], val);
        dis[y][x] = min(dis[x][y], val);
    }
    for (int i = 1; i <= N; i++)
        dis[i][i] = 0;
    dijkstra(N);
    for(int i=1;i<=N;i++)
        if(dis[1][i]<=K)
            answer++;
    return answer;
}
