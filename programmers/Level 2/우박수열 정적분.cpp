우박수열을 먼저 구해준뒤 정적분 넓이를 알아야되는데 가로세로 길이를 알기때문에 가능
매 좌표마다 넓이를 저장해주고 start, end 를 설정해줘서 넓이를 더해주는 과정에서 주어지는 범위가 좌표로 주어지기때문에 조금 어려움
내가 저장한 area 벡터에 인덱스와 좌표가 맞아떨어지는 규칙을 찾음


#include <string>
#include <vector>
#include <cmath>
#include <iostream>
#include <algorithm>

using namespace std;

vector<double> solution(int k, vector<vector<int>> ranges) {
    vector<double> answer;
    vector<double> area;
    while (k != 1) {
        int before = k;
        if (k % 2 == 0) {
            k /= 2;
        }
        else {
            k = k * 3 + 1;
        }
        area.push_back(min(k, before) + (double)abs(before - k) / 2);
    }
    for (int i = 0; i < ranges.size(); i++) {
        int start = ranges[i][0];
        int end = area.size() + ranges[i][1] - 1;
        cout << start << " " << end << endl;
      //맞아 떨어지는 규칙
        if (start > end) {
            if (start - end == 1) {
                answer.push_back(0.0);
            }
            else {
                answer.push_back(-1.0);
            }
        }
        else {
            double sum = 0.0;
            for (int i = start; i <= end; i++) {
                sum += area[i];
            }
            answer.push_back(sum);
        }
    }
    cout << area.size();
    return answer;
}
