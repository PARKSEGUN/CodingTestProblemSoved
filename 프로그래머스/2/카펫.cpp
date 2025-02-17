규칙을 사용해서 해결
공식을 사용하면 for문을 사용하지 않아도 가능!

#include <string>
#include <vector>
#include<algorithm>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    for(int i=1;i<=yellow;i++){
        if(yellow%i==0){
            if((i+2)*(yellow/i+2)-yellow==brown){
                answer.push_back(max(i+2,yellow/i+2));
                answer.push_back(answer[0]==i+2?yellow/i+2:i+2);
                    return answer;
            }
        }
    }
}
