처음에 접근했던 방법은 주어진 strings 에서 n번째 문자만 따로 빼서 저장시킨후에 pair로 인덱스 까지 묶어서 저장하고
pair 로 저장했던 값들을 first에 따라서 정렬을 시키고 그에 맞는 인덱스 값을 알기때문에 같은 값들 끼리 다시
전체 문자열에 대해서 정렬을 하려고 했지만, 같은 값들끼리 다시 묶어서 전체 문자열을 정렬하려고
코드를 작성하던중에 특정원소에 대해서 정렬이 된다면 효율적으로 코드를 작성할 수 있다고 생각해서
cmp를 특정 원소에 따라서 정렬하는 식으로 작성했더니 훨쒼 더 좋은 코드가 되었다.



#include <string>
#include <vector>
#include<algorithm>

using namespace std;

int N;
bool cmp(string a ,string b){
    if(a[N]==b[N]){return a<b;}
    return a[N]<b[N];
}
vector<string> solution(vector<string> strings, int n) {
    N=n;
    sort(strings.begin(),strings.end(),cmp);
    return strings;
}
