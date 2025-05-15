#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

int dix[4] = { 0,0,1,-1 };
int diy[4] = { -1,1,0,0 };

bool BFS(int sx, int sy, vector<string>& place) {
    queue<pair<int, int>> move;
    queue<int> count;
    move.push({ sx,sy });
    count.push(0);
    bool visited[5][5] = { 0, };
    visited[sx][sy] = true;
    while (move.size() != 0) {
        int x = move.front().first;
        int y = move.front().second;
        int cnt = count.front();
        move.pop();
        count.pop();
        for (int i = 0; i < 4; i++) {
            int dx = x + dix[i];
            int dy = y + diy[i];
            if (dx >= 0 && dx < 5 && dy >= 0 && dy < 5) {
                if (visited[dx][dy] == false && cnt + 1 <= 2 && place[dx][dy] != 'X') {
                    if (place[dx][dy] == 'P')
                        return false;
                    visited[dx][dy] = true;
                    move.push({ dx,dy });
                    count.push(cnt + 1);
                }
            }
        }
    }
    return true;
}

bool solve(vector<string>& place) {
    vector<pair<int, int>> people;
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            if (place[i][j] == 'P')
                people.push_back({ i,j });
        }
    }
    for (int i = 0; i < people.size(); i++) {
        if (BFS(people[i].first, people[i].second, place) == false) {
            return false;
        }
    }
    return true;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    for (int i = 0; i < places.size(); i++) {
        if (solve(places[i]) == true)
            answer.push_back(1);
        else
            answer.push_back(0);
    }
    return answer;
}