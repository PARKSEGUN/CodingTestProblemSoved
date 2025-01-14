주어진 두개의 A,B 배열을 오름차순으로 정렬
A의 첫번째 값을 기준으로 B 배열을 탐색하면서 
B보다 A의 idx 가 작다면 idx 와 result를 하나 더해준다

#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<int> A, vector<int> B) {
    int answer = 0;
    sort(A.begin(),A.end());
    sort(B.begin(),B.end());
    int idx=0;
    for(auto x : B){
        if(A[idx]<x){
            idx++;
            answer++;
        }
    }
    return answer;
}
