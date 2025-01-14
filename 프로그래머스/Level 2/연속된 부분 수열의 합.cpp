sum 이라는 합을 구해주는 변수를 만들어주고 앞에서부터 하나씩 더해주면서 진행한다
sum > k 라면 연속된 부분 수열의 합이기 때문에 start변수를 정해줘서 앞에서 부터 제거해주고
sum < k 라면 계속해서 추가한다.

#include <string>
#include <vector>

using namespace std;

vector<int> answer;
int sum = 0;
int start = 0;
int finish = -1;

void chkAnswer(vector<int>& sequence, int k) {
    if (finish - start < answer[1] - answer[0]) {
        answer[0] = start;
        answer[1] = finish;
    }
    else if (finish - start == answer[1] - answer[0]) {
        if (start < answer[0]) {
            answer[0] = start;
            answer[1] = finish;
        }
    }
}

vector<int> solution(vector<int> sequence, int k) {
    answer.push_back(0);
    answer.push_back(sequence.size() - 1);

    for (int i = 0; i < sequence.size(); i++) {
        sum += sequence[i];
        finish++;
        if (sum == k)
            chkAnswer(sequence, k);
        else if (sum > k) {
            while (sum > k) {
                sum -= sequence[start];
                start++;
            }
            if (sum == k)
                chkAnswer(sequence, k);
        }
    }
    return answer;
}
