처음에는 모든 경우의 수를 코드로 작성했고 후에 3번 반복되는 코드를 반복문으로 합쳤다.

#include <string>

using namespace std;

int solution(string dartResult) {
    int num[3]={0,0,0};
    int i=0;
    for(int k=0;k<3;k++){
        num[k]=dartResult[i]-'0';i++;
    if(dartResult[i]=='0'){num[k]=10;i++;}
    if(dartResult[i]=='D'){num[k]*=num[k];}
    else if(dartResult[i]=='T'){num[k]*=(num[k]*num[k]);}
    i++;
    if(dartResult[i]=='*'){num[k]*=2;if(k-1>=0){num[k-1]*=2;}}
    else if(dartResult[i]=='#'){num[k]*=-1;}
    else{i--;}
    i++;
    }
   return num[0]+num[1]+num[2];
}
