처음으로 생각했던 방법은 완전 탐색으로 문제에서 알려준 그대로 적용했다.
- 문장에서 110을 찾고 그부분을 없애주기
- 110이 들어갔을때에 가장 작은 값이 되는 부분에 110을 넣어주기
- 다시 처음부터 반복하는데 문장의 변화가 없다면 -> 가장 작은 값이라면 리턴

110이 들어가야하는 자리에는 규칙이있다.
이 값을 넣었을때는 가장 작은 값이 되어야하기때문에 맨뒤에 110을 넣었을때와 값을 비교하면서 
확인해보다가 규칙을 찾았다.
  
1이 연속으로 두번나오는 (11) 의 앞에 위치해야한다.
마지막 값이 1이라면 그 앞에 위치해야한다
위에 두개의 규칙에 겹치지 않는다면 맨 뒤에 위치한다.

  그리고 또한 110을 뽑고 비교하고 다시 뽑고 하는것이 아닌 처음부터 다 뽑아버려도 문제될것이 없는 점이
  110이 들어갈 자리에 한번에 모든 110이 들어가도 문제가 발생하지 않았다.

  따라서 110을 모두 뽑아서 남은 문장으로 해결한다.

  이런 점을 확인하고 다시 규칙을 확인해봤는데 11이왔다면 뒤에 0 은 오지 못한다 110이 되면 뽑히기때문이다
  그렇다면 1이 두번이상 겹쳐져있는 것은 항상 문장에 마지막에 위치해야한다.
  마지막 값이 1일때 또한 그 앞에 110이 와야한다는 점을 봤을때 결론적으로
  
  110 이 올 수 있는 규칙은 마지막 0 이 존재하는 idx 뒤에 오면되는 것이었다
  0이 존재하지않는다면 맨 앞에 오면된다.

  이 규칙을 기준으로 설계하였다.

  #include <string>
#include <vector>
#include <iostream>

using namespace std;

string all110;

string find110(string str) {
    all110 = "";
    int i = 0;
    while (i < str.size() - 2 && str.size() >= 3) {
        if (str.substr(i, 3) == "110") {
            all110 += "110";
            str.erase(i, 3);
            i = i - 2 < 0 ? 0 : i - 2;
        }
        else {
            i++;
        }
    }
    return str;
}

int findFinallZero(string str) {
    for (int i = str.size() - 1; i >= 0; i--) {
        if (str[i] == '0') {
            return i;
        }
    }
    return -1;
}

vector<string> solution(vector<string> s) {
    vector<string> answer;
    for (int i = 0; i < s.size(); i++) {
        string remake = find110(s[i]);
        int count1 = 0;
        if (remake.size() == 0) {
            answer.push_back(all110);
            continue;
        }
        int zeroIdx = findFinallZero(remake);
        cout << zeroIdx << endl;
        if (zeroIdx == -1) {
            answer.push_back(all110 + remake);
        }
        else {
            string str = remake.substr(0, zeroIdx + 1);
            str += all110;
            str += remake.substr(zeroIdx + 1);
            answer.push_back(str);
        }

    }
    return answer;
}
