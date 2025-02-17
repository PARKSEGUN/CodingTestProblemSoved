최대 공약수 gcd 알고리즘을 사용해서 쉽게 해결할 수 있다.

#include <string>
#include <vector>
#include <iostream>

using namespace std;

int gcd(int a,int b){
    if(a==0)
        return b;
    return gcd(b%a,a);
}

int solution(vector<int> arrayA, vector<int> arrayB) {
    int gcdA=arrayA[0],gcdB=arrayB[0];
    for(int i=1;i<arrayA.size();i++){
        gcdA=gcd(gcdA,arrayA[i]);
    }
    for(int i=1;i<arrayB.size();i++){
        gcdB=gcd(gcdB,arrayB[i]);
    }
    for(int i=0;i<arrayA.size();i++){
        if(arrayA[i]%gcdB==0){
            gcdB=0;
            break;
        }
    }
    for(int i=0;i<arrayB.size();i++){
        if(arrayB[i]%gcdA==0){
            gcdA=0;
            break;
        }
    }
    return (gcdA<gcdB?gcdB:gcdA);

}
