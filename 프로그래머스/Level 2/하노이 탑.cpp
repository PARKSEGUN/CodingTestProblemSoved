//재귀함수를 사용해서 1에서 3으로 n 개를 옮기기 위해서는 1에서 2로 n-1개를 옮기고 
//1에서 3으로 n번째 블록을 옮기고 다시 2에서 3으로 n-1개를 옮겨주는 행동이 반복된다는 것을 이용한다.

#include <string>
#include <vector>

using namespace std;

vector<vector<int>> answer;

void solve(int start,int end,int empty,int n){
    if(n==1){
        answer.push_back({start,end});
        return;
    }
    solve(start,empty,end,n-1);
    answer.push_back({start,end});
    solve(empty,end,start,n-1);
}

vector<vector<int>> solution(int n) {
    solve(1,3,2,n);
    return answer;
}
