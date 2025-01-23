import java.util.*;
import java.io.*;

class Solution {
    int[] parent;
    public int solution(int n, int[][] costs) {
        // 전역변수초기화
        parent=new int[n];
        // union-find를 위한 변수 초기화
        for(int i=0;i<parent.length;i++){
            parent[i]=i;
        }
        // 크루스칼 알고리즘 사용을 위한 정렬
        Arrays.sort(costs,(o1,o2)->(o1[2]-o2[2]));
        
        int answer = 0;
        int cur=0;
        for(int i=0;i<costs.length;i++){
            int x=costs[cur][0];
            int y=costs[cur][1];
            int weight=costs[cur][2];
            
            if(find(x)!=find(y)){
                union(x,y);
                answer+=costs[cur][2];
            }
            cur++;
        }
        return answer;
    }
    private void union(int x,int y){
        int parentX=find(x);
        int parentY=find(y);
        parent[parentX]=parentY;
    }
    private int find(int x){
        if(parent[x]!=x){
            return parent[x]=find(parent[x]);
        }
        return x;
    }
}

//프림 알고리즘 한번 살펴보기
=> 프림 알고리즘은 임의로 한 노드를 선택한 후에 한 노드로 부터 사용될 수 있는 간선을 우선순위큐에 저장하면서 진행하는 방식
  우선순위큐는 에서 가중치가 낮은 간선을 순서대로 뽑고 뽑힌 노드를 기준으로 다시 간선들을 우선순위큐에 저장하면서 진행
  방문 여부는 사이클 확인은 visited 를 사용해서 해결할 수 있음
  다익스트라 알고리즘(한 정점에서 모든 다른 정점까지의 거리 구하기)과 비슷
