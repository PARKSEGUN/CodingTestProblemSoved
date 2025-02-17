문자열의 정렬 조건을 사용
 - 가장 큰 수를 앞으로 보냄 
   Ex. 900, 98, 9 -> 9, 98, 900
또한, 결과값이 0일때 0000으로 반환되는 경우를 배제
   이를 위해 모든 결과값이 0인지를 판단하는 로직 구현
   하지만 모든 것을 확인할 필요없이 맨 앞의 값이 0인지를 확인하면 효율적


import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb=new StringBuilder();
        List<String> numbersToString=new ArrayList<>();
        for(int i=0;i<numbers.length;i++){
            numbersToString.add(numbers[i]+"");
        }
        numbersToString.sort((s1,s2)->-(s1+s2).compareTo(s2+s1));
        for(int i=0;i<numbersToString.size();i++){
            sb.append(numbersToString.get(i));
        }
        if(numbersToString.get(0).equals("0")){
            return "0";
        }
        return sb.toString();
    }
}
