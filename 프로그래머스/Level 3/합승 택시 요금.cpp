BFS를 사용해서 현재지점에서 두갈래로 나누어서 갈 수 있는 모든 경우를 확인하고 같은 방향으로 갔을때에는 요금을 한번만 내고 진행하게 작성했지만 시간 초과 발생
다익스트라 알고리즘(지정된 정점에서의 다른 정점까지의 최단거리)을 사용해서 어느 한 지점을 X 라고 할때 
S에서 X까지와 A에서 X까지, B에서 X까지의 거리의 합중에 최소가 되는 값을 찾는다

#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

void dijkstra(vector<int>& result, vector<pair<int, int>> move[], int s) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({ 0,s });
    while (pq.size() != 0) {
        int cur = pq.top().second;
        int sum = pq.top().first;
        pq.pop();
        for (int i = 0; i < move[cur].size(); i++) {

            int next = move[cur][i].first;
            int val = move[cur][i].second;
            if (result[next] == 87654321) {
                result[next] = sum + val;
                pq.push({ sum + val,next });
            }
            else {
                if (result[next] > sum + val) {
                    result[next] = sum + val;
                    pq.push({ sum + val,next });
                }
            }
        }
    }
    result[s] = 0;
}


int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int answer = 987654321;
    vector<pair<int, int>> move[222];
    for (int i = 0; i < fares.size(); i++) {
        move[fares[i][0]].push_back({ fares[i][1],fares[i][2] });
        move[fares[i][1]].push_back({ fares[i][0],fares[i][2] });

    }
    vector<int> S(n + 1, 87654321);
    dijkstra(S, move, s);
    vector<int> A(n + 1, 87654321);
    dijkstra(A, move, a);
    vector<int> B(n + 1, 87654321);
    dijkstra(B, move, b);
    for (int i = 1; i <= n; i++) {
        answer = min(S[i] + A[i] + B[i], answer);
    }
    return answer;
}
