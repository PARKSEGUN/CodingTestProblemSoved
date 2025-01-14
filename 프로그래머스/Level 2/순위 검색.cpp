주어진 문제의 조건을 보고 브루트포스 알고리즘을 사용해서 일일이 조건에 대해서 확인하면 시간초과가 발생한다.
문제에 효율성을 체크한다는 말도 있었기때문에 브루트포스는 생각하지않고 어떻게하면 효율적으로 해결할 수 있을까 생각
주어진 속성은 언어, 분야, 숙련도, 음식, 점수 이렇게 총 5개이다.
처음에 4차원 배열을 만들어주고 알맞은 속성으로 배치하고 마지막에 점수를 push 해주는 방식을 생각했다.
vector로 5차원 벡터를 만들어주고 마지막 5번째 벡터는 크기를 0으로 해서 직접 값을 넣어줄 수 있게 설정
속성의 종류는 3, 2, 2, 2, 로 총 24가지의 경우가 발생한다고 생각
해결하고 이제 query에 대해서 값을 비교하려했지만 '-' 문자 즉 모든 경우를 포함하고있는경우에서 어떻게 해결해야할지 몰라서 실패.

블로그를 찾아보니 배열대신에 unordered_map을 사용해서 주어진 info 정보를 하나의 문자열로 합쳐서 해결했었다.
unordered_map<string,vector<int>> 를 사용하는데 확실히 간단하게 진행할 수 있었다.
정보 매핑은 unordered_map을 사용하고 주어진 점수와 같거나 높은지를 확인할 때에는 lower_bound() 알고리즘을 사용한다.
이분탐색을 사용하는 알고리즘으로 나중에 문제 해결할때 도움이 될 알고리즘이라고 생각하기때문에 기억하자.

lower_bound - 주어진 조건보다 같거나 높은 수가 나오는 idx를 반환
upper_bound - 주어진 조건보다 높은 수가 나오는 idx를 반환
반드시 오름차순 정렬이 되어있어야한다는 것을 기억

그렇다면 '-' 즉 모든 경우가 들어갈 수 있는 이 표시를 해결해야하는데 처음에 
1번 방법
java backend junior pizza 150
이렇게 정보가 들어온다면 
'-'가 들어갈 수 있는 모든 경우의 수를 대입해보자고 생각
예를 들어서 java - - -, java - junior - , - backend - - , 등등 '-'가 들어갈 수 있는 모든 경우의 수를 unordered_map에 넣어주자고 생각했지만
탐색하는 모든 info 마다 이 과정을 진행하게되면 효율성이 떨어진다고 생각했다..

2번 방법
주어지는 속성이 몇개 안되기때문에 배열로 먼저 정해놓자고 생각
{cpp,java,python}
{backend,frontend}
{junior,senior}
{chicken,pizza}
이렇게 모든 경우의 수를 배열로 만들어 놓고 '-'값이 나왔는지 확인해보고 나왔다면 설정해둔 배열을 사용하자고 생각

2번째 방법으로 진행하던중에 '-'가 들어있는지 아닌지를 확인하는 if 문을 작성해주는 과정에서 
if{
  else{

    if{
      
    }else{
      if{
        
      }else{
     if{
       
    }else{

    }
    if{
      
    }else{

    }
  }
}

    }
  }
}
else{}

이런 식으로 너무 많고 깔끔하지 못하는 코드가 되어서 중간에 포기하고 다른 방법을 생각해봤다.
충분히 생각해봤지만 좋은 알고리즘을 생각하지못해서 블로그를 참고했는데 첫번째 방법을 사용했다
알고보니 첫번째 경우의수는 한 탐색당 16번밖에 추가되지않았다. 따라서 시간초과도 발생하지않았다.
첫번째 방법을 사용해서 나올 수 있는 모든 경우에 '-'를 넣어주었고 lower_bound() 알고리즘을 사용해서 해결했다.

이 문제를 해결하면서 배운 점
- 문자열 쪼개기 (istringstream, sstream)
- unordered_map의 다양한 사용법
- 비트 마스킹
- lower_bound, upper_bound 이분탐색 알고리즘

#include <string>
#include <vector>
#include <sstream>
#include <iostream>
#include <unordered_map>
#include <algorithm>

using namespace std;

unordered_map<string, vector<int>> um;

vector<string> parsing(string str) {
    char separator = ' ';
    istringstream iss(str);
    string str_buf;
    string answer = "";
    vector<string> vec;
    while (getline(iss, str_buf, separator)) {
        if (str_buf == "and")
            continue;
        vec.push_back(str_buf);
    }
    return vec;
}

void mapping(vector<string>& info) {
    for (int i = 0; i < info.size(); i++) {
        vector<string> vec = parsing(info[i]);
        //모든 경우 대입
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        string str = "";
                        if (i == 0)str += '-';
                        else str += vec[0];
                        if (j == 0)str += '-';
                        else str += vec[1];
                        if (k == 0)str += '-';
                        else str += vec[2];
                        if (l == 0)str += '-';
                        else str += vec[3];
                        um[str].push_back(stoi(vec.back()));
                    }
                }
            }
        }
    }
}

vector<int> findCount(vector<string>& query) {
    vector<int> answer;
    for (int i = 0; i < query.size(); i++) {
        int cnt = 0;
        string str = "";
        vector<string> vec = parsing(query[i]);
        for (int i = 0; i < vec.size() - 1; i++) {
            str += vec[i];
        }
        int num = stoi(vec.back());
        vector<int> numVec = um[str];
      //lower_bound 알고리즘 사용
        int idx = lower_bound(numVec.begin(), numVec.end(), num) - numVec.begin();
        answer.push_back(numVec.size() - idx);
    }
    return answer;
}

vector<int> solution(vector<string> info, vector<string> query) {
    vector<int> answer;
    mapping(info);
    //lower_bound()를 사용하기위한 오름차순 정렬
    for (auto& x : um) {
        sort(x.second.begin(), x.second.end());
    }
    return findCount(query);
}
