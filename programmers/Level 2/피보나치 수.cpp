//점화식을 사용해서 해결 전전의 값과 전값의 합

#include <string>
#include <vector>

using namespace std;

long long fibonacci[111111];
int solution(int n) {
    fibonacci[0] = 0; fibonacci[1] = 1;
    for (long long i = 2; i <= n; i++) {
        fibonacci[i] = fibonacci[i - 2] + fibonacci[i - 1];
        fibonacci[i]%=1234567;
    }
    return fibonacci[n];
}
