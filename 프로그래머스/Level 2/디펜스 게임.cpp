우선순위 큐를 사용해서 해결
언제 큐에 넣어주어야하고 빼줘야하는지가 중요
내가 싸우고자 하는 라운드의 병사의 수와 내가 처리했던 라운드의 병사들의 수를 비교해서 더 큰쪽인 경우에 스킬을 사용하는 것이 최적
만약 내가 이미 처리했던 경우가 더 크다면 그때사용했던 병사들에서 내가 지금 싸우고자하는 병사들의 수를 빼준뒤 내 병사들에 더해준다
이미 지나간 전투를 스킬을 사용했다고 할것이기 때문에 현재의 병사수 + 지나간 전투때 사용한 병사 - 지금 싸울 라운드의 병사수

#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

int solution(int n, int k, vector<int> enemy) {
    int answer = 0;
    priority_queue<int> pq;
    pq.push(0);
    for (int i = 0; i < enemy.size(); i++) {
        if (n - enemy[i] >= 0) {
            answer++;
            pq.push(enemy[i]);
            n -= enemy[i];
        }
        else {
            if (k == 0)
                return answer;
            if (pq.top() < enemy[answer]) {
                answer++;
                k--;
            }
            else {
                pq.push(enemy[answer]);
                n+=pq.top()-enemy[answer];
                answer++;
                pq.pop();
                k--;
            }
        }
    }
}
