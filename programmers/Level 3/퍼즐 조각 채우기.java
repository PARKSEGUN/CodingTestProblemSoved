// 첫번째 문제점 :  Set으로 진행하니 겹치는 도형이 생겼을때의 처리가 불가능, 회전시켰을때의 같은 모양이 성립한다면 같은 모양의 개수를 파악할 수 있어야함
// 두번째 문제점 : Set의 에러를 막기위해 Map으로 진행, 하지만 dfs의 탐색을 따라서 방향에 대한 Integer 값을 저장했기에 12 라는 방향이 설정되었을때에 1 다음에 2 인지, 아니면 dfs의 재귀 때문에 1이 끝나고 다시 돌아와서 2를 선택한 것인지 파악이 불가능\
// 세번째 문제점 : 두번째 문제점을 해결하기 위해 dfs가 끝났을때의 표시를 문자열에 넣어주고 해결하려 했지만, 다시 첫번째의 문제점 발생, 같은 모양이 성립한다면 개수를 없애주도록 했지만 같은 도형으로 돌려서 만들어질 수 있는 모든 경우를 없애야함, 처음부터 설계를 잘못 했다고 생각, 도형을 사용했을때의 로직을 미리 생각했어야함
// 네번째 시도 : 위 문제점을 해결하기 위해 도형의 라벨링을 넣어주고 진행, 라벨링을 한 후에 game_board와 hash과정으로 알맞는 도형을 찾지 못하는 문제
// 다섯번째 시도 : 도형마다 HashSet으로 변경, 하나의 도형에서 회전시켜서 나올 수 있는 경우를 HashSet에 저장, 도형마다 HashSet을 할당
// 여섯번째 시도 : 라벨링을 넣어주고 도형마다 Set을 할당, dfs의 결과값(StringBuilder)가 재귀문의 특성으로 중복되는 경우를 없애기 위해 dfs마지막에 추가적으로 표시(.)를 추가해서 해결


import java.util.*;
import java.io.*;

class Solution {
    
    int[] dix={-1,0,1,0};
    int[] diy={0,1,0,-1};
    Set<String>[] moveHistory;
    int n;
    StringBuilder sb=new StringBuilder();
    int answer = 0;
    int blockSize=1;
    boolean[] isUsed;
    
    public int solution(int[][] game_board, int[][] table) {
        n=table.length;
        addLabel(table);
        moveHistory=new Set[blockSize+1];
        for(int i=0;i<=blockSize;i++){
            moveHistory[i]=new HashSet<>();
        }
        isUsed=new boolean[blockSize+1];
    
        searchDownRight(table,0);
        searchRightUp(table,1);
        searchUpLeft(table,2);
        searchLeftDown(table,3);
        
        for(int i=1;i<blockSize;i++){
            
            // System.out.println(i+" : "+moveHistory[i]);
        }
        searchGameBoard(game_board);
        
        return answer/2;
    }
    
    private void addLabel(int[][] table){
        boolean[][] visited=new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(table[i][j]==1&&!visited[i][j]){
                    visited[i][j]=true;
                    table[i][j]=blockSize;
                    labelDfs(i,j,0,visited,table);
                    blockSize++;
                }
            }
        }
    }
        private void labelDfs(int x,int y,int wall,boolean[][] visited,int[][] table){
        for(int i=0;i<4;i++){
            int dx=x+dix[i];
            int dy=y+diy[i];
            if(dx<0||dx>=n||dy<0||dy>=n){
                continue;
            }
            if(visited[dx][dy]){
                continue;
            }
            if(table[dx][dy]==wall){
                continue;
            }
            visited[dx][dy]=true;
            table[dx][dy]=blockSize;
            labelDfs(dx,dy,wall,visited,table);
        }
    }
    private void searchGameBoard(int[][] game_board){
        boolean[][] visited=new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(game_board[i][j]==0&&!visited[i][j]){
                    visited[i][j]=true;
                    dfs(i,j,1,visited,game_board,0);
                    String result=sb.toString();
                    // System.out.println(i+" "+j+" | "+"result : "+result);
                    sb.setLength(0);
                    for(int k=1;k<=blockSize;k++){
                        if(!isUsed[k]&&moveHistory[k].contains(result)){
                            // System.out.println("answer : "+ result);
                            isUsed[k]=true;
                            answer+=result.length()+2;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void searchDownRight(int[][] table,int turn){
        boolean[][] visited=new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(table[i][j]!=0&&!visited[i][j]){
                    int label=table[i][j];
                    visited[i][j]=true;
                    dfs(i,j,0,visited,table,turn);
                    moveHistory[label].add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }
    
    private void searchRightUp(int[][] table,int turn){
        boolean[][] visited=new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=n-1;j>=0;j--){
                if(table[j][i]!=0&&!visited[j][i]){
                    int label=table[j][i];
                    visited[j][i]=true;
                    dfs(j,i,0,visited,table,turn);
                    moveHistory[label].add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }
    private void searchUpLeft(int[][] table,int turn){
        boolean[][] visited=new boolean[n][n];
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(table[i][j]!=0&&!visited[i][j]){
                    int label=table[i][j];
                    visited[i][j]=true;
                    dfs(i,j,0,visited,table,turn);
                    moveHistory[label].add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }
    private void searchLeftDown(int[][] table,int turn){
        boolean[][] visited=new boolean[n][n];
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<n;j++){
                if(table[j][i]!=0&&!visited[j][i]){
                    int label=table[j][i];
                    visited[j][i]=true;
                    dfs(j,i,0,visited,table,turn);
                    moveHistory[label].add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }
    private void dfs(int x,int y,int wall,boolean[][] visited,int[][] table,int turn){
        for(int i=0;i<4;i++){
            int dx=x+dix[(i-turn+4)%4];
            int dy=y+diy[(i-turn+4)%4];
            if(dx<0||dx>=n||dy<0||dy>=n){
                continue;
            }
            if(visited[dx][dy]){
                continue;
            }
            if(table[dx][dy]==wall){
                continue;
            }
            visited[dx][dy]=true;
            sb.append(i);
            dfs(dx,dy,wall,visited,table,turn);
            sb.append(".");
        }
    }
}
