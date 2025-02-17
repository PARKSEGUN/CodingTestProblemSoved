//n을 1씩 증가시키면서 심사대에서 끝나는 사람이 있을때마다 
//모든 심사대에서 걸리는 시간을 비교해서 가장 적게 걸리는 시간을 탐색해서 
//해결하려했지만 해결하지못한 반례 발생
//이분탐색 문제였지만 어디서 이분탐색을 사용해야 될지 생각하지못함

//문제를 혼자 해결하지 못해서 블로그 참조
//https://0xd00d00.github.io/2021/06/29/programmers_entry_test.html

//어느 시간에서 각각의 심사대에서 처리할 수 있는 인원은 몇명인지를 생각했다면 더 쉽게 해결할 수 있었음
//최소와 최대의 시간을 먼저구하고 반씩나눠가면서 반씩나눠진 그 시간에 
//총 몇명이 심사를 받을 수 있는지를 확인해서 1분 앞으로 또는 1분뒤로 가면서 최소시간을 구한다.

#include <string>
#include <vector>
#include<algorithm>

using namespace std;

long long solution(int n, vector<int> times) {
    long long answer = 0;
    //최대시간을 구하기 위한 정렬
    sort(times.begin(),times.end());
    
    long long min=1;
    long long max=(long long)times.back()*n;   //모든 인원이 가장 오래걸리는 심사를 받았을때
    
    while(min<=max){
        long long mid=(min+max)/2;
        long long cnt=0;  //총 몇명이 검사를 맡았는지 알기위함
        for(int i=0;i<times.size();i++){
            cnt+=(mid/(long long)times[i]);
        }
        if(cnt<n){min=mid+1;}
        else{   //cnt와  n 이 같아져도 최솟값이 아닌경우 발생
            //예제에서 28도 6명이지만 29도 6명 처리가능
            //따라서 같은 경우도 answer에 값 넣어주고 범위를 좁혀줌
            answer=mid;
            max=mid-1;
        }
    }
    return answer;
}
