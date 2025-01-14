문제를 보고 최단거리를 구하기위해서는 맨 뒤에지역부터 해결하면서 앞으로 와야한다고 생각
배달과 수거가 끝남을 확인하기위한 총 배달, 수거 개수를 확인
배달과 수거가 끝난 지점을 기억하면서 맨 뒤에 지역부터 앞으로 오면서 
cap수를 넘지 않는 선에서 배달과 수거 진행
(배달이 끝나도 수거해야될 지역이있으면 방문, 반대상황도 동일)

#include <string>
#include <vector>
#include<iostream>

using namespace std;

long long solution(int cap, int n, vector<int> deliveries, vector<int> pickups) {
    long long answer = 0;
    //종료조건을 위한 총 배달, 수거 개수
    int allDeliver=0;
    int allPickup=0;
    for(auto x: deliveries){allDeliver+=x;}
    for(auto x: pickups){allPickup+=x;}

    int i=n-1;  //배달과 수거를 완료한 지역을 기억하기위한 변수
    while(1){
        if(allDeliver==0&&allPickup==0){return answer;} //종료조건

        for(;i>=0;i--){
            if(deliveries[i]!=0||pickups[i]!=0){    //배달이나 수거가끝난지역인지 확인
                answer+=(i+1)*2;
                int deliver=cap;
                //배달
                for(int j=i;j>=0;j--){
                    if(deliveries[j]>=deliver){
                        deliveries[j]-=deliver;
                        allDeliver-=deliver;
                        break;  //더이상 배달 불가
                    }
                    else{
                        deliver-=deliveries[j];
                        allDeliver-=deliveries[j];
                        deliveries[j]=0;
                    }
                }
                //수거
                deliver=cap;
                for(int j=i;j>=0;j--){
                    if(pickups[j]>=deliver){
                        pickups[j]-=deliver;
                        allPickup-=deliver;
                        break;  //이상 수거 불가
                    }
                    else{
                        deliver-=pickups[j];
                        allPickup-=pickups[j];
                        pickups[j]=0;
                    }
                } 
                break;
            }

        }
    }

}
