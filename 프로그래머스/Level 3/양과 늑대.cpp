BFS를 사용해서 queue에는 [내가 지금 까지 방문했던 정점]과 [방문한 양의 개수], [방문한 늑대의 개수] 이렇게 저장한다
방문한 지점에서 갈 수있는 방향중에 방문했던 지점을 빼고 양과 늑대를 구분해서 큐에 넣어준다  이때 늑대의 개수가 양을 넘는지 확인


#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

int answer = 1;

void BFS(vector<int>& info, vector<vector<int>>& graph) {
    queue<vector<int>> q;   //현재까지 방문한 노드
    queue<pair<int, int>> animal;
    vector<int> vec;
    vec.push_back(0);
    q.push(vec);
    animal.push({ 1,0 });
    while (q.size() != 0) {
        vector<int> v = q.front();
        int sheep = animal.front().first;
        int wolf = animal.front().second;
        q.pop();
        animal.pop();
        bool visited[22] = { 0, };
        for (int i = 0; i < v.size(); i++) {
            visited[v[i]] = true;
        }
        for (int k = 0; k < v.size(); k++) {
            for (int i = 0; i < graph[v[k]].size(); i++) {
                int x = graph[v[k]][i];
                if (visited[x] == false) {
                    if (info[x] == 0) {
                        v.push_back(x);
                        q.push(v);
                        animal.push({ sheep + 1,wolf });
                        answer = max(answer, sheep + 1);
                        v.pop_back();
                    }
                    else if (info[x] == 1 && sheep > wolf + 1) {
                        v.push_back(x);
                        q.push(v);
                        animal.push({ sheep,wolf + 1 });
                        v.pop_back();
                    }
                }

            }
        }
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    vector<vector<int>> graph(info.size()); //[부모]={자식들}
    for (int i = 0; i < edges.size(); i++) {
        graph[edges[i][0]].push_back(edges[i][1]);
        graph[edges[i][1]].push_back(edges[i][0]);
    }
    BFS(info, graph);
    return answer;
}
