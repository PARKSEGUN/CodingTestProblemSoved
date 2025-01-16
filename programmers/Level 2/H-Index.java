h의 값은 주어진 값이아니라 어떤 값이라도 될 수 있다는 점
때문에 주어진 값의 범위를 전체 탐색
주어진 citations를 정렬하여 해당 수보다 큰 수의 개수가 몇개인지를 효율적으로 계산
시간의 효율을 위해 i값에 따른 j값을 설정해서 시간복잡도가 n^2이 되지 않게 설정

import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int i=0,j=0;
        for(i=0;i<=10000;i++){
            while(j<citations.length&&i>citations[j]){
                j++;
            }
            int upCnt=citations.length-j;
            if(upCnt>=i){
                answer=i;
            }else{
                break;
            }
        }
        return answer;           
    }
}
