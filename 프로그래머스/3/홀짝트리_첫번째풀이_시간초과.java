import java.util.*;
import java.io.*;

class Solution {

    Map<Integer,List<Integer>> connInfo=new HashMap<>();
    boolean[] visited=new boolean[1111111];
    Map<Integer, boolean[]> startOrNotResult=new HashMap<>();   //설명 : [0]에는 n 노드를 시작점으로 했을때의 홀짝트리가 가능하면 true 아니면 false, [1] 에는 n 노드가 중간 지점으로 들어갈때의 홀짝 트리가 가능하면 true, 아니면 false
    List<List<Integer>> trees=new ArrayList<>();
    StringBuilder sb1=new StringBuilder();
    StringBuilder sb2=new StringBuilder();
    int[] answer=new int[] {0,0};
    public int[] solution(int[] nodes, int[][] edges) {
        //각 노드의 연결노드를 연결리스트로 저장
        for(int i=0;i<edges.length;i++){
            if(connInfo.containsKey(edges[i][0])){
                connInfo.get(edges[i][0]).add(edges[i][1]);
            }else{
                List<Integer> tmp=new ArrayList<>();
                tmp.add(edges[i][1]);
                connInfo.put(edges[i][0],tmp);
            }
            if(connInfo.containsKey(edges[i][1])){
                connInfo.get(edges[i][1]).add(edges[i][0]);
            }else{
                List<Integer> tmp=new ArrayList<>();
                tmp.add(edges[i][0]);
                connInfo.put(edges[i][1],tmp);
            }
        }
        //연결 리스트를 확인해서 시작과 시작이 아닐때의 결과를 저장
        for(int i=0;i<nodes.length;i++){
            if(!connInfo.containsKey(nodes[i])) startOrNotResult.put(nodes[i],new boolean[] {nodes[i]%2==0,nodes[i]%2==0/*여기 어떻게 할지*/});
            else{
                boolean[] result=new boolean[] {false,false};
                int connCnt=connInfo.get(nodes[i]).size();
                //시작일때
                if((nodes[i]%2==0&&connCnt%2==0)||(nodes[i]%2==1&&connCnt%2==1)){   //정
                    result[0]=true;
                }else{  //역
                    result[0]=false;
                }
                //중간일때
                if((nodes[i]%2==0&&(connCnt-1)%2==0)||(nodes[i]%2==1&&(connCnt-1)%2==1)){   //정
                    result[1]=true;
                }else{  //역
                    result[1]=false;
                }
                startOrNotResult.put(nodes[i],result);
            }
        }
        
        // 총 트리의 구성 노드들을 저장
        for(int i=0;i<nodes.length;i++){
            if(!visited[nodes[i]]){
                List<Integer> tmp=new ArrayList<>();
                findConnNodes(nodes[i],tmp);
                trees.add(tmp);
            }
        }
        //트리들을 탐색하며 각 노드들의 연결된 개수를 파악하여 해결
        for(int i=0;i<trees.size();i++){
            getResult(trees.get(i));
        }
        return answer;
    }
    
    private void getResult(List<Integer> tree){
        StringBuilder compareF=new StringBuilder();
        StringBuilder compareT=new StringBuilder();
        for(int i=0;i<tree.size();i++) {
            compareF.append("F");
            compareT.append("T");
        }
    
        for(int cur : tree){
            if(startOrNotResult.get(cur)[0]) sb1.append("T");
            else sb1.append("F");
            if(startOrNotResult.get(cur)[1]) sb2.append("T");
            else sb2.append("F");
            
        }
        
        // System.out.println(tree);
        // System.out.println("sb1 : "+sb1);
        // System.out.println("sb2 : "+sb2);
        // System.out.println("compareT : "+compareT);
        // System.out.println("compareF : "+compareF);
        
        for(int i=0;i<tree.size();i++){
            char tmp1=sb1.charAt(i);
            char tmp2=sb2.charAt(i);
            sb2.setCharAt(i,tmp1);
            if(sb2.toString().equals(compareT.toString())) answer[0]++;
            if(sb2.toString().equals(compareF.toString())) answer[1]++;
            sb2.setCharAt(i,tmp2);
        }
        
        sb1.setLength(0);
        sb2.setLength(0);
    }
    
    private void findConnNodes(int node, List<Integer> list){
        
        list.add(node);
        visited[node]=true;
        if(!connInfo.containsKey(node)) return;
        List<Integer> connList=connInfo.get(node);
        for(int next : connList){
            if(!visited[next]) findConnNodes(next,list);
        }
        
    }
}
