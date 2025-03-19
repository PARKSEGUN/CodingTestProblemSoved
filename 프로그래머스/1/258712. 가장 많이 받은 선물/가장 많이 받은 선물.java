import java.util.*;
import java.io.*;

class Solution {
    Map<String,int[]> giftInfo=new HashMap<>();
    Map<String,Integer> fromToCnt=new HashMap<>();
    Map<String,Integer> reciveCnt=new HashMap<>();
    public int solution(String[] friends, String[] gifts) {
        for(int i=0;i<friends.length;i++){
            giftInfo.put(friends[i],new int[] {0,0});
            reciveCnt.put(friends[i],0);
        }
        
        for(int i=0;i<gifts.length;i++){
            String[] splits=gifts[i].split(" ");
            String from=splits[0];
            String to=splits[1];
            giftInfo.get(from)[0]++;
            giftInfo.get(to)[1]++;
            fromToCnt.put(gifts[i],fromToCnt.getOrDefault(gifts[i],0)+1);
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<friends.length;i++){
            for(int j=i+1;j<friends.length;j++){
                String first=friends[i];
                String second=friends[j];
                sb.append(first).append(" ").append(second);
                String connFirstSecond=sb.toString();
                int firstSecondCnt=fromToCnt.getOrDefault(connFirstSecond,0);
                sb.setLength(0);
                sb.append(second).append(" ").append(first);
                String connSecondFirst=sb.toString();
                int secondFirstCnt=fromToCnt.getOrDefault(connSecondFirst,0);
                sb.setLength(0);
                if(firstSecondCnt>secondFirstCnt){
                    reciveCnt.put(first,reciveCnt.get(first)+1);
                }else if(firstSecondCnt<secondFirstCnt){
                    reciveCnt.put(second,reciveCnt.get(second)+1);
                }else{ //같은 경우
                    int firstScore=giftInfo.get(first)[0]-giftInfo.get(first)[1];
                    int secondScore=giftInfo.get(second)[0]-giftInfo.get(second)[1];
                    if(firstScore>secondScore){
                        reciveCnt.put(first,reciveCnt.get(first)+1);
                    }else if(firstScore<secondScore){
                        reciveCnt.put(second,reciveCnt.get(second)+1);
                    }
                }
                    
            }
        }
        int answer=0;
        for(Integer x : reciveCnt.values()){
            answer=Math.max(answer,x);
        }
        return answer;
    }
}