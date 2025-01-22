보트에는 2명의 사람이 탑승 가능
보트는 무게가 설정되어있음
가장 최적의 해를 찾는 방법은 현재 탑승하지 않은 인원중에서 가장 무거운 인원을 먼저 태우고 가장 가벼운 인원을 태우는 것이라 생각
투포인터를 사용해서 범위를 가운데로 좁혀나가면서 해결

import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int start=0,end=people.length-1;
        while(start<=end){
            int weight=people[end];
            if(start<end&&weight+people[start]<=limit){
                weight+=people[start];
                start++;
            }
            answer++;
            end--;
        }
        return answer;
    }
}
