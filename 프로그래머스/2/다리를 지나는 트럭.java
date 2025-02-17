두개의 큐를 사용해서 대기하고있는 큐와 다리를 지나는 큐를 설정
다리의 무게를 통해서 다리의 진입 여부를 판단해야하기에 다리에 들어오는 시점을 저장
1초에 대기큐와 다리큐 동시에 진행되어야하기에 다리큐 작업을 먼저 진행해주고 대기큐를 후에 진행

import java.util.*;
import java.io.*;

class Solution {
    Queue<Integer> readyQueue =new LinkedList<>();
    Queue<int[]> bridgeQueue=new LinkedList<>();
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        for(int i=0;i<truck_weights.length;i++){
            readyQueue.add(truck_weights[i]);
        }
        int time=0;
        int bridgeWeight=0;
        while(!readyQueue.isEmpty()||!bridgeQueue.isEmpty()){
            if(bridgeQueue.size()>0&&time-bridgeQueue.peek()[1]>=bridge_length){
                int[] outTruck=bridgeQueue.poll();
                bridgeWeight-=outTruck[0];
            }
            //레디큐에서 다리를 건널 수 있다면
            if(!readyQueue.isEmpty()&&bridgeWeight+readyQueue.peek()<=weight){
                int moveTruck=readyQueue.poll();
                bridgeQueue.add(new int[] {moveTruck,time});
                bridgeWeight+=moveTruck;
            }
            time++;
//                 System.out.print("[");
//             for(int[] tmp : bridgeQueue){
//                 System.out.print("{"+tmp[0]+", "+tmp[1]+"}, ");
//             }
//                 System.out.print("] ");
            
//             System.out.println(readyQueue+" | "+time);
        }
        return time;
    }
}
