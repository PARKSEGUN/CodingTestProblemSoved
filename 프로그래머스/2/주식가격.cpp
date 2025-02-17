비교하려는 인덱스를 i로 두고 i보다 작은 값들을 비교하기위한 인덱스를 j 로 두면 쉽게 해결할수있다.

#include <string>
#include <vector>
#include<queue>

using namespace std;

vector<int> solution(vector<int> prices) {
    vector<int> answer;
    for(int i=0;i<prices.size();i++){
        int cnt=1;
        for(int j=i+1;j<prices.size();j++){
            if(prices[i]>prices[j]||j==prices.size()-1){answer.push_back(cnt);break;}
            cnt++;
        }
    }
    answer.push_back(0);
    return answer;
    
}
