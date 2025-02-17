보석의 모든 종류를 저장한다
unorder_map을 이용해서 문자열을 인덱스로 표현한다 
첫번째 보석부터 시작해서 배열에 하나씩 저장하면서, 모든 보석을 저장했는지 확인한다
모든 보석을 저장했다는 앞쪽부터 하나씩 제거하면서 모든 보석이 여전히 저장중인지 확인한다
모든 보석에서 벗어낫다면 이전상황은 모든 보석을 갖고있는 최소범위이기에 저장한다

#include <string>
#include <vector>
#include <iostream>
#include<algorithm>
#include<unordered_map>

using namespace std;

bool allChk(vector<int>& chk) {
    for (auto x : chk)
        if (x == 0)
            return false;
    return true;
}

vector<int> solution(vector<string> gems) {
    vector<pair<int, int>> vec;
    unordered_map<string, int> um;
    int N = 0;
    //um을 통해 string을 index로 저장
    for (auto x : gems) {
        if (um.find(x) == um.end()) {
            um[x] = N;
            N++;
        }
    }
    vector<int> chk(N);    //모든 보석을 저장했는지 확인
    int start = 0;
    int range = 987654321;
    for (int i = 0; i < gems.size(); i++) {
        chk[um[gems[i]]]++;
        if (allChk(chk) == true) {
            while (allChk(chk) == true) {
                chk[um[gems[start]]]--;
                start++;
            }
            range = min(range, i + 1 - start);
            vec.push_back({ start,i + 1 });
        }
    }
    for (auto x : vec)
        if (x.second - x.first == range) {
            vector<int> answer{ x.first,x.second };
            return answer;
        }
}
