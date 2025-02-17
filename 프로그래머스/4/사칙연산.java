최종적으로 구해야하는 값을 기준으로 dp를 진행
Ex. ["1", "-", "3", "+", "5", "-", "8"]	
  숫자를 기준으로 진행 숫자 5개 -> 인덱스 5개
  dp[0][3] => 0 인덱스의 숫자(1) 부터 3인덱스의 숫자(8) 까지의 모든 연산을 끝냈을때의 값
  앞의 부호가 음수일 수 있기 때문에 최대값과 최소값을 정함
  dp[0][3][1] => 0 인덱스의 숫자(1) 부터 3인덱스의 숫자(8) 까지의 모든 연산을 끝냈을때의 최대값
  dp[0][3][0] => 0 인덱스의 숫자(1) 부터 3인덱스의 숫자(8) 까지의 모든 연산을 끝냈을때의 최소값
  그렇다면, dp[0][3][1] = dp[0][0]-dp[1][3] or dp[0][1]+dp[2][3] or dp[0][2]-dp[3][3] 중에 하나
  중요한것은, 위 3가지의 값들중에 어떤 값을 사용할 것인지이다.
  앞의 부호가 음수일 경우에는 뒤에 오는 숫자가 가장 작은 경우가 최선이고, 앞의 부호가 양수일 경우에는 뒤에 오는 숫자가 가장 커야한다.
  때문에 최대값과 최소값만 dp배열에 저장함
  getResult() 메서드로 구하고자하는 값이 최대값인지 최솟값인지를 판단 -> isBig 변수로 받아옴
  최대값과 최솟값을 계속해서 유지하면서 점점더 넓은 범위의 최대값과 최솟값을 구함
  최대값과 최솟값을 구하는 방법은 총 4가지
  최솟값 + 최솟값
  최대값 + 최대값
  최대값 - 최솟값
  최솟값 - 최대값
  
import java.util.*;
import java.io.*;

class Solution {
    int[][][] dp;
    public int solution(String arr[]) {
//         dp 배열 -1로 초기화(정의되지않았음을 의미)
        dp=new int [arr.length/2+1][arr.length/2+1][2];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++){
                dp[i][j][0]=dp[i][j][1]=-1;
            }
        }
//         dp[x][x] 처럼 수를 나타낼 수 있는 부분을 초기화
        for(int i=0;i<arr.length/2+1;i++){
            dp[i][i][0]=dp[i][i][1]=Integer.parseInt(arr[i*2]);
        }
//         출력문
        //         for(int i=0;i<dp.length;i++){
        //     for(int j=0;j<dp[i].length;j++){
        //         System.out.print("("+dp[i][j][0]+" "+dp[i][j][1]+")");
        //     }System.out.println();
        // }System.out.println();
//         
        int answer=getResult(0,arr.length/2,true,arr);
        // int answer=getResult(0,,true,arr);
        // for(int i=0;i<dp.length;i++){
        //     for(int j=0;j<dp[i].length;j++){
        //         System.out.print("("+dp[i][j][0]+" "+dp[i][j][1]+")");
        //     }System.out.println();
        // }System.out.println();
            return answer;
    }
    
    private int getResult(int start, int end,boolean isBig,String arr[]){
//         한번 정의가 되었으면 다시 사용하기 위한 조건문
        if(isBig&&dp[start][end][1]!=-1){
            return dp[start][end][1];
        }else if(!isBig&&dp[start][end][0]!=-1){
            return dp[start][end][0];
        }
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int i=start;i<end;i++){
            int result1=0,result2=0;
            if(arr[i*2+1].equals("-")){
                result1=getResult(start,i,true,arr)-getResult(i+1,end,false,arr);
                result2=getResult(start,i,false,arr)-getResult(i+1,end,true,arr);
            }else{
                result1=getResult(start,i,true,arr)+getResult(i+1,end,true,arr);
                result2=getResult(start,i,false,arr)+getResult(i+1,end,false,arr);
            }
            max=Math.max(max,result1);
            min=Math.min(min,result1);
            max=Math.max(max,result2);
            min=Math.min(min,result2);
        }
        dp[start][end][1]=max;
        dp[start][end][0]=min;
        return isBig?max:min;
    }
}
