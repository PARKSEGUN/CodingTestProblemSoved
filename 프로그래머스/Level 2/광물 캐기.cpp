주어진 광석들을 5개로 묶어서 값이 비싼순으로 정렬을 시킨뒤에 처리해주는 방식 사용
곡갱이 개수보다 광석이많을때에 사용할 수 없어야되는 광석이 우선순위가 높은 이유로 사용되는 오류 발견
  따라서 곡갱이의 총 개수를 파악하고 처리할 수 있는 광석들을 추려서 5개씩 묶는다

#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

bool cmp(vector<int> a, vector<int> b) {
    int sumA = 0, sumB = 0;
    for (int i = 0; i < a.size(); i++) {
        sumA += a[i];
    }
    for (int i = 0; i < b.size(); i++) {
        sumB += b[i];
    }
    return sumA>=sumB;
}

int solution(vector<int> picks, vector<string> minerals) {
    int answer = 0;
    int sum=picks[0]+picks[1]+picks[2];  //반례 수정을 위한 변수
    sum*=5;
    vector<vector<int>> sumValue;
    for (int i = 0; i < sum; i++) {
        vector<int> vec;
        for (int j = 0; j < 5; j++) {
            if (minerals.size() > i) {
                if (minerals[i] == "diamond") {
                    vec.push_back(25);
                }
                else if (minerals[i] == "iron") {
                    vec.push_back(5);
                }
                else if (minerals[i] == "stone") {
                    vec.push_back(1);
                }
            }
            i++;
        }
        i--;
        sumValue.push_back(vec);
    }
    sort(sumValue.begin(), sumValue.end(), cmp);
for(int i=0;i<sumValue.size();i++){
        for(int j=0;j<sumValue[i].size();j++){
            cout<<sumValue[i][j]<<" ";
        }cout<<endl;
    }
    int cnt = 0;
    for (int i = 0; i < picks.size(); i++) {
        for (int j = 0; j < picks[i]; j++) {
            if (cnt >= sumValue.size()) {
                return answer;
            }
            for (int k = 0; k < sumValue[cnt].size(); k++) {
                if (i == 0) {
                    answer++;
                }
                else if (i == 1) {
                    if (sumValue[cnt][k] == 25) {
                        answer += 5;
                    }
                    else {
                        answer++;
                    }
                }
                else if (i == 2) {
                    if (sumValue[cnt][k] == 25) {
                        answer += 25;
                    }
                    else if (sumValue[cnt][k] == 5) {
                        answer += 5;
                    }
                    else {
                        answer++;
                    }
                }

            }
            cnt++;
        }
    }
    
    
    return answer;
}
