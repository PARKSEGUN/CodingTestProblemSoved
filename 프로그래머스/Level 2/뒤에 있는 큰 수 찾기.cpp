2중 for 문을 사용해서 앞에서 부터 순서대로 찾기 -> 시간초과
뒤에서 부터 2중 for문 사용하기 -> 시간초과
max와 min 값을 설정해주고 시간 줄이기 -> 반례발생
조건을 걸어주어도 반례가 계속 발생
스택을 활용해서 top보다 더 큰 값이 들어왔을때에 경우 인덱스를 따로 저장시켜줘서 answer를 저장시켜주고 pop해준다.

#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<int> answer(numbers.size(),-1);
    vector<pair<int,int>> stack;   // <val/idx>
    for(int i=0;i<numbers.size();i++){
            while(stack.size()!=0&&stack.back().first<numbers[i]){
                answer[stack.back().second]=numbers[i];
                stack.pop_back();
            }
        stack.push_back({numbers[i],i});
    }
    return answer;
}
