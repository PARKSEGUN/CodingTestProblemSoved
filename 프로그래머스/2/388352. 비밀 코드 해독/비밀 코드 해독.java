import java.util.*;
import java.io.*;



class Solution {
    
    int[] arr=new int[5];
    boolean[] visited;
    int answer;
    Set<Integer>[] set;
    
    public int solution(int n, int[][] q, int[] ans) {
        set=new Set[q.length];
        for(int i=0;i<q.length;i++){
            set[i]=new HashSet<>();
            for(int j=0;j<5;j++){
                set[i].add(q[i][j]);
            }
            // System.out.println(i +" "+set[i]);
        }
        visited= new boolean[n+1];
        makeArr(0,1,n,q,ans);
        return answer;
    }
    
    private void makeArr(int cnt,int cur,int n,int[][] q,int[] ans){
        if(cnt==5){
            // System.out.println(Arrays.toString(arr));
            if(compareAns(q,ans)) answer++;
            return;
        }
        for(int i=cur;i<=n;i++){
            if(visited[i]) continue;
            arr[cnt]=i;
            visited[i]=true;
            makeArr(cnt+1,i+1,n,q,ans);
            visited[i]=false;
        }
    }
    
    private boolean compareAns(int[][] q, int[] ans){
        for(int i=0;i<q.length;i++){
            int sum=0;
            for(int j=0;j<5;j++){
                if(set[i].contains(arr[j])) sum++;
            }
            if(ans[i]!=sum) return false; 
            
        }
        return true;
    }
}