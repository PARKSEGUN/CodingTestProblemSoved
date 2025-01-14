일정한 규칙을 찾아서 해결

#include <string>
#include <vector>

using namespace std;

int solution(int storey) {
    int answer = 0;
    while(storey!=0){
        int x=storey%10;
        if(x==5){
            if(storey%100/10>5){
               answer+=(10-x);
                storey+=x; 
            }
            else
                answer+=x;
        }
        else if(x<5)
            answer+=x;
        else{
            answer+=(10-x);
            storey+=x;
        }
        storey/=10;
    }
    return answer;
}
