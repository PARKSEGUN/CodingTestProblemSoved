//처음에 작성한 코드는 
//30,34,3 을 예로 들면 수를 string 으로 변경해주고
//각 자리 인덱스를 비교하고 같다면 다음 인덱스를 비교해준다 만약 3 과 34처럼 크기가 같지 않은 값들은
//string.size() 를 사용해서 마지막 인덱스와 현재 비교하고있는 인덱스가 같다면 다음 인덱스로 넘겨주지 않는다.
//3과 34를 예로들면 처음에 3 과 3 을 비교하고 다음에는 3 과 4를 비교 하게된다.
// 그러면 34가 더 앞에있어야한다고 판단함.
//하지만 97,978 과 같은 반례발생
//따라서 다른 방법을 생각하다가 비교해야되는 두 값중에 누가 먼저 앞으로 와야하는지가 중요
//두 값을 이어 붙였을 두가지 경우를 비교해서 더 큰 값을 앞으로 정렬해줌




#include <string>
#include <vector>
#include<algorithm>

using namespace std;

bool cmp(int a,int b){
    string str1=to_string(a);   //문자열로 변환
    string str2=to_string(b);
    
    string sum1=str1+str2;  //앞+뒤
    string sum2=str2+str1;  //뒤+앞
    
    return stoi(sum1)>stoi(sum2);   //다시 int 형으로 변환후에 더 큰값 리턴
}

string solution(vector<int> numbers) {
    string answer = "";
    sort(numbers.begin(),numbers.end(),cmp);    //cmp 방식으로 정렬
    for(int i=0;i<numbers.size();i++){answer+=to_string(numbers[i]);}   //반환은 문자열 이므로 answer에 쌓아주기
    if(answer[0]=='0'){return "0";} //0값이 중복으로 들어오면 0이 쌓이는 반례 발생을 위한 조건
    return answer;
}
