#include <string>
#include <vector>
#include<algorithm>

using namespace std;

string change(int x) {
    string str = "";
    while (x != 0) {
        str.push_back((x % 2)+'0');
        x /= 2;
   }
    reverse(str.begin(), str.end());
    return str;
}

vector<int> solution(string s) {
    vector<int> answer;
    int cnt_change = 0;
    int cnt_zero = 0;
    while (s != "1") {
        int cnt=0;
        for (int i = 0; i < s.size(); i++) {
            if (s[i] == '0') {cnt++;cnt_zero++;}   
            //0을 굳이 지울 필요없이 그냥 0을 없앴을때의 길이만 구하면 되는데
            //0을 지워서 효율이 떨어져서 수정
        }
        s = (change(s.size()-cnt));
        cnt_change++;
    }
    answer.push_back(cnt_change);
    answer.push_back(cnt_zero);
    return answer;
}
