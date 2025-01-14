문제를 보고 문제에서 알려주는 조건에 맞춰서 코딩하면 해결할 수 있다고 생각했다.
제거하는 부분에서 어려움이 있었다.
제거를 시킨후에 내가 지금까지 설치한 건물들이 설치가 가능한지를 보는 방법
제거해야되는 구조물을과 연결되어있는 구조물들을 있다면 다시 설치가 가능한지 확인해보는 방법
이렇게 두가지가 있는거 같다.
또한 이 문제는 얼마나 코딩을 알아보기쉽게 구조적으로 작성할 수가 있는지가 중요한 점이였던것 같다.
코딩을 할때 무작정 써내려가는 것이 아닌 얼마나 보기쉬운 코드를 작성할 수 있는지도 확인하자.

해결하기가 힘들었던 문제이다. 아직 이런 구현쪽으로는 많이 접해보지 않아서 간단한 코드 또는 보기쉬운 코드를 작성하기가 어려웠고 접근도 쉽지않았다.
건물을 제거할때 사용했던것은 후자인 연결되어있는 구조물들을 다시 설치할 수 있는지 확인하는 것이다.
제거 하는 부분에서 조건문을 작성하는 부분에서 애를먹었고 
if(a&&b&&c) return true; 라는 조건과 if(a&&b)return c 라는 구문과 헷갈려서 시간을 너무 많이 써버렸다.
첫번째 조건은 c까지 만족하는 상황에서 true를 리턴시키고 두번째 조건은 a와 b만 만족한다면 리턴을 시킨다 이렇게 간단한 조건문에서도 
애를 먹은거 보니 문제를 무작정 접근하지말고 어떻게 해결할 것인지 어떤 알고리즘을 사용해야될것인지 생각을 한뒤에 접근하자


#include <string>
#include <vector>

using namespace std;

bool column[111][111];   //기둥
bool beam[111][111]; //보
int N;

bool install_column(int x, int y) {
    if (x == 0) { return true; }
    if (column[x - 1][y] == 1) { return true; }
    if (beam[x][y] == 1) { return true; }
    if (y - 1 >= 0 && beam[x][y - 1] == true) { return true; }
    return false;
}
bool install_beam(int x, int y) {
    if (column[x - 1][y] == 1) { return true; }
    if (y + 1 <= N && column[x - 1][y + 1] == 1) { return true; }
    if (y - 1 >= 0 && y + 1 <= N && beam[x][y - 1] == 1 && beam[x][y + 1] == 1) { return true; }
    return false;
}
bool remove_column(int x, int y) {
    column[x][y] = 0;
    if (x + 1 <= N && column[x + 1][y] == 1 && !install_column(x + 1, y)) { return false; }
    if (x + 1 <= N && beam[x + 1][y] == 1 && !install_beam(x + 1, y)) { return false; }
    if (x + 1 <= N && y - 1 >= 0 && beam[x + 1][y - 1] == 1 && !install_beam(x + 1, y - 1)) { return false; }
    return true;
}
bool remove_beam(int x, int y) {
    beam[x][y] = 0;
    if (column[x][y] == 1 && !install_column(x, y)) { return false; }
    if (y + 1 <= N && column[x][y + 1] == 1 && !install_column(x, y + 1)) { return false; }
    if (y + 1 <= N && beam[x][y + 1] == 1 && !install_beam(x, y + 1)) { return false; }
    if (y - 1 >= 0 && beam[x][y - 1] == 1 && !install_beam(x, y - 1)) { return false; }
    return true;
}

vector<vector<int>> solution(int n, vector<vector<int>> build_frame) {
    vector<vector<int>> answer;
    N = n;
    for (int i = 0; i < build_frame.size(); i++) {
        int x = build_frame[i][1];
        int y = build_frame[i][0];
        int a = build_frame[i][2];
        int b = build_frame[i][3];
        if (b == 1) {//설치
            if (a == 0) {//기둥설치
                if (install_column(x, y) == true) {
                    column[x][y] = 1;
                }
            }
            else {//보 설치
                if (install_beam(x, y) == true) {
                    beam[x][y] = 1;
                }
            }
        }
        else {//삭제
            if (a == 0) {//기둥 삭제
                if (remove_column(x, y) == false) {
                    column[x][y] = 1;
                }
            }
            else {//보 삭제
                if (remove_beam(x, y) == false) {
                    beam[x][y] = 1;
                }
            }
        }
    }
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            if (column[j][i] == 1) {
                answer.push_back({ i,j,0 });
            }
            if (beam[j][i] == 1) {
                answer.push_back({ i,j,1 });
            }
        }
    }
    return answer;
}
