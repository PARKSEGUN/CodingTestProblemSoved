내가 해결한 방식은 dfs 사용해서 모든 경우를 확인하는 방법
왼쪽으로 이동했을때와 오른쪽으로 이동하는 두 경우를 모든 상황마다 dfs로 진행하고 매번 dfs가 진행할때마다 현재의 name이 모두 A로 이루어져있는지를 판단하는 로직을 사용
밑에 따로 다른 사람의 코드를 참고해서 가져왔는데 해당 코드가 더 최적이라고 생각 추가적으로 이해해보자


// 4
// 5
// 6
// 7
// 8
// 9
// 10
// 11
// 12
// 13
// 14
// 15
// 16
// 17
// 18
// 19
// 20
// 21
// 22
// 23
// 24
// // 문제가 개편되었습니다. 이로 인해 함수 구성이나 테스트케이스가 변경되어, 과거의 코드는 동작하지 않을 수 있습니다.
// // 새로운 함수 구성을 적용하려면 [코드 초기화] 버튼을 누르세요. 단, [코드 초기화] 버튼을 누르면 작성 중인 코드는 사라집니다.
// class Solution {
//     public int solution(String name) {
//         int answer = 0;
//         int[] diff={0,1,2,3,4,5,6,7,8,9,10,11,12,13,12,11,10,9,8,7,6,5,4,3,2,1};
//         for(char c:name.toCharArray())
//             answer+=diff[c-'A'];

//         int length=name.length();
//         int min=length-1;

//         for(int i=0;i<length;i++){
//             int next=i+1;
//             while(next<length && name.charAt(next)=='A'){
//                 next++;
//             }                
//             min=Math.min(min,i+length-next+Math.min(i,length-next));
//         }

//         return answer+min;
//     }
// }
// 이 풀이 다시 한번 이해해보기


import java.util.*;
import java.io.*;

class Solution {
    
    char[] nameToArr;
    int answer=Integer.MAX_VALUE;
    public int solution(String name) {
        nameToArr=name.toCharArray();
        dfs(0,0);
        return answer;
    }
    
    private void dfs(int curIdx, int result){
        // System.out.println(nameToArr);
        // System.out.println(curIdx+" "+result);
        if(isClear(nameToArr)){
            answer=Math.min(answer,result);
            return;
        }
        int tmpIdx=curIdx;
        int tmpCnt=0;
        while(nameToArr[tmpIdx]=='A'){
            tmpIdx=(tmpIdx+1<nameToArr.length)?tmpIdx+1:0;
            tmpCnt++;
        }
        char tmpTarget=nameToArr[tmpIdx];
        nameToArr[tmpIdx]='A';
        int diff=(26-(tmpTarget-'A')>tmpTarget-'A')?tmpTarget-'A':26-(tmpTarget-'A');
        dfs(tmpIdx,result+tmpCnt+diff);
        // 다시 원위치
        nameToArr[tmpIdx]=tmpTarget;
        tmpIdx=curIdx;
        tmpCnt=0;
        while(nameToArr[tmpIdx]=='A'){
            tmpIdx=(tmpIdx-1>=0)?tmpIdx-1:nameToArr.length-1;
            tmpCnt++;
        }
        tmpTarget=nameToArr[tmpIdx];
        nameToArr[tmpIdx]='A';
        diff=(26-(tmpTarget-'A')>tmpTarget-'A')?tmpTarget-'A':26-(tmpTarget-'A');
        dfs(tmpIdx,result+tmpCnt+diff);
        nameToArr[tmpIdx]=tmpTarget;
        tmpIdx=curIdx;
        tmpCnt=0;
    }
    
    private boolean isClear(char[] nameToArr){
        for(int i=0;i<nameToArr.length;i++){
            if(nameToArr[i]!='A'){
                return false;
            }
        }
        return true;
    }
}
