//좌표를 2차원 배열에 넣고 진행하니 연결되지 않은 부분도 연결되어있다고 판단하는 에러 발생
// ex) 예시 1번 그림에서 (3,5) 좌표에서 (4,5)로 가야하는데 배열상으로는 (3,6)이 갈 수 있는 곳이라 넘어가지는 에러
// Set 자료구조를 사용해서 정답으로 나올 수 있는 모든 경우를 넣어주고 가장 큰 두번째 원소를 뽑으면 해결할 수 있을줄 알았지만 예시 2번이 반례

// 맵의 크기를 두배 늘려서 진행 -> 사각형의 크기도 두배 늘리기
// 맵의 기본 눈금을 1*1이 아닌 2*2로 생각하고 진행, 왜냐하면 한칸으로 진행하는 경우에는 근접하지 않은 다른 변으로 이동할 수 있기 때문

import java.util.*;
import java.io.*;

class Solution {
    
    Set<String> insidePoint=new HashSet<>();
    StringBuilder sb=new StringBuilder();
    boolean[][] map=new boolean[111][111];
    boolean[][] visited=new boolean[111][111];
    int answer=Integer.MAX_VALUE;
    int[] dix={-1,0,1,0};
    int[] diy={0,1,0,-1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        //map의 크기를 두배 늘리기
        characterX*=2;
        characterY*=2;
        itemX*=2;
        itemY*=2;
        for(int i=0;i<rectangle.length;i++){
            //map의 크기를 두배 늘리기
            int x1=rectangle[i][0]*2;
            int y1=rectangle[i][1]*2;
            int x2=rectangle[i][2]*2;
            int y2=rectangle[i][3]*2;
            //insidePoint에 모든 사각형의 내부 좌표 추가
            for(int x=x1+1;x<=x2-1;x++){
                for(int y=y1+1;y<=y2-1;y++){
                    sb.append(x).append(",").append(y);
                    insidePoint.add(sb.toString());
                    sb.setLength(0);
                }
            }
            //rectangle의 모든 좌표의 테두리를 지날 수 있는 좌표 (true)로 처리
            for(int j=x1;j<=x2;j++){
                map[j][y1]=true;
                map[j][y2]=true;
            }
            for(int j=y1;j<=y2;j++){
                map[x1][j]=true;
                map[x2][j]=true;
            }
        }
        visited[characterX][characterY]=true;
        dfs(characterX,characterY,0,itemX,itemY);
        return answer/2;
    }
    
    private void dfs(int cx,int cy,int cnt, int ix,int iy){
        // System.out.println(cx+" "+cy+" "+cnt);
        if(cx==ix&&cy==iy){
            answer=Math.min(answer,cnt);
        }
        
        for(int i=0;i<4;i++){
            int dx=cx+dix[i];
            int dy=cy+diy[i];
            if(dx<0||dy<0||dx>=111||dy>=111){
                continue;
            }
            if(visited[dx][dy]){
                continue;
            }
            if(!map[dx][dy]){
                continue;
            }
            sb.append(dx).append(",").append(dy);
            String tmp=sb.toString();
            sb.setLength(0);
            if(insidePoint.contains(tmp)){
                continue;
            }
            visited[dx][dy]=true;
            dfs(dx,dy,cnt+1,ix,iy);
            visited[dx][dy]=false;
        }
    }
}
