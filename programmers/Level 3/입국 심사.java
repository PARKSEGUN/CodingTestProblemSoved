// 일반적으로 주어진 배열을 정렬시킨 후 중간지점부터 원하는 결과를 도출하는 값까지 찾아내는 이분탐색과는 다르게 답이 될 수 있는 값(result)을 지정해놓고 result를 기준으로 진행했을때에 어떤 값(x)이 있어야하는지를 판단해서 x값을 기준으로 도출하고 싶은 값보다 큰지 작은지를 판단하는데에 이분탐색을 사용하여 해결하는 문제
// 주로 매개변수 탐색 알고리즘이라고 한다.


import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] times) {
        long start=0,end=1_000_000_000_000_000_000L;
        while(start<end){
            long mid=(start+end)/2;
            long sum=0;
            for(int i=0;i<times.length;i++){
                sum+= mid/times[i];
            }
            if(sum>=(long)n){
                end=mid;
            }else{
                start=mid+1;
            }
        }
        // System.out.println(start+" "+end);
        return end;
    }
}
