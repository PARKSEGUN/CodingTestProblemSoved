#include <iostream>
#include <string>
using namespace std;

int answer = 1;

void palindrome(string& s, int x, int y) {
    int i = 1;
    if (y == -1) {
        while (x - i >= 0 && x + i < s.size() && s[x - i] == s[x + i]) {
            answer = max(answer, i * 2 + 1);
            i++;
        }
    }
    else {
        i = 0;
        while (x - i >= 0 && y + i < s.size() && s[x - i] == s[y + i]) {
            answer = max(answer, 2 * i + 2);
            i++;
        }
    }

}

int solution(string s)
{
    for (int i = 1; i < s.size(); i++) {
        if (s[i] == s[i - 1]) {   //짝수개의 팰린드롬의 조건
            palindrome(s, i - 1, i);
        }
        if (i + 1 < s.size() && s[i - 1] == s[i + 1]) {  //홀수개의 팰린드롬의 조건
            palindrome(s, i, -1);
        }
    }
    return answer;
}
