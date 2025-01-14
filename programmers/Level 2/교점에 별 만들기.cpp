교점을 구해주는 공식이 있기때문에 그 공식을 사용해서 교점을 찾는다.
교점이 정수인 경우만 정답으로 넣을 수 있기때문에 ceil() 함수 즉 올림 함수를 사용해서 교점에 ceil()함수를 사용했을때와 그렇지 않을때에 값이 같다면
정수로 판별한다.
두 직선이 일치하거나 평행일경우는 제외시켜야할 경우로 제외 시켜준다.
문제를 해결했지만 통과를 받지 못했다 그 이유는
max값이나 min 값을 1000이 최대이고 -1000이 최소인줄알고 계산했지만 범위는 LONG LONG 범위 였다.
따라서 climits를 사용해서 최대 최소 범위로 구해주었다.
처음에는 구글에서 교점 찾아주는 공식을 사용해서 해결하려했지만 알고보니 문제에 마지막에 참고란에 공식이 쓰여져있었다.
항상 문제를 끝까지 읽는 습관을 기르자.
set 자료구조를 사용해주어서 중복되는 경우를 삭제시켜주었고
속도의 향상을 위해서 unordered_set을 사용해서 해결하려고 했지만 unordered_set에는 pair 자료형이 들어가지못한다.
HASH의 이유때문이라는데 조금 아직 확실하게 알지 못해서 unordered 에 대해서는 조금더 공부해야한다.



#include <string>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <algorithm>
#include <climits>
using namespace std;

set<pair<long long, long long>> dots;

pair<long long, long long> findMeet(vector<int> vec1, vector<int> vec2) {
    double a1 = vec1[0];
    double b1 = vec1[1];
    double c1 = vec1[2];
    double a2 = vec2[0];
    double b2 = vec2[1];
    double c2 = vec2[2];
    if (a1 * b2 - a2 * b1 == 0) { //제외시켜야할 경우
        return { 1111,1111 }; //존재할 수 없는 값 리턴
    }
    else {
        double x = (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1);
        double y = (c1 * a2 - a1 * c2) / (a1 * b2 - a2 * b1);
        //정수인지 판별하기위한 ceil() 함수
        double xx = ceil(x);
        double yy = ceil(y);
        if (xx != x || yy != y) {   //제외시켜야할 경우
            return { 1111,1111 };
        }
        else {
            return { (long long)x,(long long)y };
        }
    }
}

vector<string> findAnswer() {
    vector<string> answer;
    long long xMax = LLONG_MIN, xMin = LLONG_MAX, yMax = LLONG_MIN, yMin = LLONG_MAX;
    for (auto x : dots) {
        xMax = max(xMax, x.first);
        xMin = min(xMin, x.first);
        yMax = max(yMax, x.second);
        yMin = min(yMin, x.second);
    }
    cout << xMax - xMin << " " << yMax - yMin << endl;
    //answer 매핑 할 크기 만들어주기
    string str = "";
    for (int i = 0; i <= xMax - xMin; i++) {
        str += ".";
    }
    for (int i = 0; i <= yMax - yMin; i++) {
        answer.push_back(str);
    }
    //교점 대입
    for (auto x : dots) {
        answer[x.second - (yMin)][x.first - (xMin)] = '*';
    }
    return answer;
}

vector<string> solution(vector<vector<int>> line) {
    for (int i = 0; i < line.size(); i++) {
        for (int j = i + 1; j < line.size(); j++) {
            if (i != j) {
                pair<long long, long long> dot = findMeet(line[i], line[j]);
                if (dot.first != 1111) {
                    dots.insert(dot);
                }
            }
        }
    }
    //y값을 반대로 설정
    vector<string> answer = findAnswer();
    reverse(answer.begin(), answer.end());
    return answer;
}
