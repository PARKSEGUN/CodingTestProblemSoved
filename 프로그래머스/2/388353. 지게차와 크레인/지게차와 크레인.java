import java.util.*;
import java.io.*;



class Solution {
    int N,M;
    char[][] map;
    Map<Character,Set<String>> pointMap=new HashMap<>();
    boolean[][] deleted;
    boolean[][] connOut;
    int[][] dir={{-1,0,1,0},{0,1,0,-1}};
    
    public int solution(String[] storage, String[] requests) {
        //전역 변수 초기화
        N=storage.length;
        M=storage[0].length();
        deleted=new boolean[N][M];
        connOut=new boolean[N][M];
        map=new char[N][M];
        //외부와 연결 세팅
        for(int i=0;i<N;i++){
            connOut[i][0]=true;
            connOut[i][M-1]=true;
        }
        for(int i=0;i<M;i++){
            connOut[0][i]=true;
            connOut[N-1][i]=true;
        }
        //Map 세팅
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j]=storage[i].charAt(j);
                if(!pointMap.containsKey(map[i][j])){
                    pointMap.put(map[i][j],new HashSet<>());
                }
                pointMap.get(map[i][j]).add(i+","+j);   //StringBuilder 쓰는거랑 비교해보기
            }
        }
        
        
        //requests 탐색
        for(int i=0;i<requests.length;i++){
            // System.out.println();
            char alpha=requests[i].charAt(0);
            if(!pointMap.containsKey(alpha)) continue;
            Set<String> set=pointMap.get(alpha);
            if(requests[i].length()==1){
                List<int[]> deletedList=new ArrayList<>();
                for(String str : set){
                    String[] splits=str.split(",");
                    int x=Integer.parseInt(splits[0]);
                    int y=Integer.parseInt(splits[1]);
                    // System.out.println(x+" "+y+" "+connOut[x][y]);
                    if(connOut[x][y]){
                        deletedList.add(new int[] {x,y});
                        // delete(x,y);
                    }
                }
                for(int[] cur:deletedList){
                    delete(cur[0],cur[1]);
                    set.remove(cur[0]+","+cur[1]);
                }
            }
            else{
                for(String str : set){
                    String[] splits=str.split(",");
                    int x=Integer.parseInt(splits[0]);
                    int y=Integer.parseInt(splits[1]);
                    delete(x,y);
                }
                set.clear();
            }
        }
        int answer=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(deleted[i][j]==false){
                    // System.out.println(i+" "+j);
                  answer++;  
                }          
            }
        }
        return answer;
    }
    
    private void delete(int x,int y){
        if(connOut[x][y]){
            for(int i=0;i<4;i++){
                int dx=x+dir[0][i];
                int dy=y+dir[1][i];
                //주변에 삭제는 되었지만 밖과 연결되지 않은 애들이 있는지
                if(dx<0||dx>=N||dy<0||dy>=M) continue;
                //이렇게 되면 이미 이전에 탐색했던 곳도 다시 한번 탐색하는 비효율 아닌가
                if(deleted[dx][dy]&&!connOut[dx][dy]){
                    connOut[dx][dy]=true;
                    delete(dx,dy);
                }else{
                    connOut[dx][dy]=true;
                }
            }
        }
        deleted[x][y]=true;
    }
    

}