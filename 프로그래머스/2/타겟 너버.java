주어지는 수는 최대 20가지
한 숫자당 +,- 로 2가지 적용가능
  2^20 = 약 1,000,000 으로 완전탐색 가능 
  재귀문을 사용하여 해결

import java.util.*;
import java.io.*;

class Solution {
    int n;
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        n=numbers.length;
        dfs(0,0,numbers,target);
        return answer;
    }
    
    private void dfs(int cnt, int cur, int[] numbers, int target){
        if(cnt==n){
            if(cur==target){
                answer++;
            }
            return;
        }
        dfs(cnt+1,cur+numbers[cnt],numbers,target);
        dfs(cnt+1,cur-numbers[cnt],numbers,target);
    }
}
