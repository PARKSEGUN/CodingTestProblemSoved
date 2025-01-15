숫자를 넣게되는 명령어가 나올때 그 해당 숫자의 범위가 모호하여 String으로 설계하여 시작
String을 Map의 Key로 사용해서 해당 숫자가 현재 남아있는지에 대한 판단을 진행
문자열을 정렬하는 것과 숫자를 정렬하는 것은 명백히 다름
다시 Integer로 받아서 Map구조로 진행
결론 : maxHeap, minHeap의 두가지 PQ를 사용하고 현재 존재하지 않는 값을 확인하기 위한 Map을 설정


import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxPq=new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minPq=new PriorityQueue<>();
        Map<Integer,Integer> numberMap=new HashMap<>();
        for(int i=0;i<operations.length;i++){
            String cur=operations[i];
            // System.out.println("cur : "+cur);
            if(cur.equals("D 1")){
                if(!maxPq.isEmpty()){
                    int tmp=maxPq.poll();
                    numberMap.put(tmp,numberMap.get(tmp)-1);
                }
            }else if(cur.equals("D -1")){
                if(!minPq.isEmpty()){
                    int tmp=minPq.poll();
                    numberMap.put(tmp,numberMap.get(tmp)-1);
                }
            }else{
                int curNum=Integer.parseInt(cur.substring(2,cur.length()));
                maxPq.add(curNum);
                minPq.add(curNum);
                numberMap.put(curNum,numberMap.getOrDefault(curNum,0)+1);
            }
            while(numberMap.getOrDefault(maxPq.peek(),-1)==0){
                maxPq.poll();
            }
            while(numberMap.getOrDefault(minPq.peek(),-1)==0){
                minPq.poll();
            }
            // System.out.println(maxPq);
            // System.out.println(minPq);
            // System.out.println(numberMap);
        }
        if(maxPq.isEmpty()){
            return new int[] {0,0};
        }
        return new int[] {maxPq.peek(),minPq.peek()};
    }
}
