합의 중복을 알기위해서 합이 인덱스로 만들어진 val 이라는 bool 배열을 하나 만들어주고 
원형이기때문에 주어진 배열뒤에 똑같이 배열을 추가로 더해줌 (이부분은 %연산자를 써서 대체가능)
어느 시작지점부터 몇개의합을 구하는지 모든 경우의 수를 구해준다 3중 for문을 사용했지만 굳이 몇개의 합을 구하는지가 필요하지않음
모든 수의 값을 더해주는 것은 따로 빼줌
set을 사용하면 시간이 너무 오래 걸림


#include <string>
#include <vector>
#include<iostream>

using namespace std;

bool val[1111111];

int solution(vector<int> elements) {
    int answer = 0;
    int N=elements.size();
    int mx=0;
    vector<int> vec=elements;
    for(auto x:elements){
        mx+=x;
        vec.push_back(x);
    }
    val[mx]=true;

   for(int i=1;i<N;i++){
       for(int j=0;j<N;j++){
           int sum=0;
           for(int k=0;k<i;k++){
               sum+=vec[j+k];
           }
           val[sum]=true;
       }
   }

  //대체
   //for(int j=0;j<N;j++){
   //        int sum=0;
   //        for(int k=0;k<N-1;k++){
   //            sum+=vec[j+k];
   //            val[sum]=true;
   //        }
   //    }
    for(int i=0;i<=mx;i++){
        if(val[i]==true){
            answer++;
        }
    }
    return answer;
}
