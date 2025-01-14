#include <string>
#include <vector>
#include<queue>
#include<iostream>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 0;
    queue<int> bridge;    
        for(int i=0;i<truck_weights.size();i++){
            if(weight<truck_weights[i]){ //다리에 들어올 수 없음
                bridge.push(0);
                i--;
            }
            else{ //다리 입장 가능
                if(i==truck_weights.size()-1){
                    return (answer+bridge_length+1);
                }
                bridge.push(truck_weights[i]);
                weight-=truck_weights[i];
            }
            answer++;
            if(bridge.size()==bridge_length){
                weight+=bridge.front();
                bridge.pop();
            }
        }
}
