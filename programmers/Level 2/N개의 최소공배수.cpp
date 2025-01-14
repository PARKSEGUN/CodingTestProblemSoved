1부터 증가시키면서 나누어 떨어지는지 확인한다.
  
#include <string>
#include <vector>
#include<algorithm>

using namespace std;

int solution(vector<int> arr) {
   int answer=1;
    while(1){
        bool chk=true;
        for(int i=0;i<arr.size();i++){
            if(answer%arr[i]!=0){chk=false;break;}
        }
        if(chk==true){return answer;}
        answer++;
    }
}
