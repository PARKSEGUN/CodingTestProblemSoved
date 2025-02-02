밑으로 탐색하면서 누적합을 만들고 마지막단에 탐색을 돌려서 가장 큰 값을 리턴

import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        for(int i=1;i<triangle.length;i++){
            for(int j=0;j<triangle[i].length;j++){
                int cur=triangle[i][j];
                if(j-1>=0){
                    triangle[i][j]+=triangle[i-1][j-1];
                }
                if(j<triangle[i-1].length){
                    triangle[i][j]=Math.max(triangle[i][j],cur+triangle[i-1][j]);
                }
            }    
        }
        for(int i=0;i<triangle[triangle.length-1].length;i++){
            answer=Math.max(triangle[triangle.length-1][i],answer);
        }
        return answer;
    }
}
