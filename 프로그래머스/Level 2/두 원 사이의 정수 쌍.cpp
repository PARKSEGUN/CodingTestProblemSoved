원안에 좌표점이 몇개가 들어가지를 구하고 큰원에서 작은원의 수를 빼준다.
원안에 좌표점을 구하는 방법은 피타고라스를 사용한다.
원의 중심에서 가장자리까지의 모든 길이는 반지름으로 일치하기때문에
x가 1일때 2일때 ..... 를 구하게된다면 x가 1일때는 r*r=1*1+y*y 라는 공식이 성립하기때문에 y값을 구할 수가있게되기때문에
x가 1일때에 성립하는 y값 즉 좌표값을 찾을 수있다.

#include <string>
#include <vector>
#include <cmath>
#include <iostream>

using namespace std;

long long solution(int r1, int r2) {
    long long answer = 0;
    if(r1<r2){
        swap(r1,r2);
    }
    long long rr1=1,rr2=0;
    for(int i=1;i<r1;i++){
        rr1+=(long long)sqrt((long long)r1*r1-(long long)i*i)+1;
    }
    for(int i=1;i<r2;i++){
        rr1-=(long long)ceil(sqrt((long long)r2*r2-(long long)i*i));
    }
    return    rr1*4;
}
