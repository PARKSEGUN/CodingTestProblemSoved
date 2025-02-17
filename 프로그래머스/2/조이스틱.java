내가 해결한 방식은 dfs 사용해서 모든 경우를 확인하는 방법
왼쪽으로 이동했을때와 오른쪽으로 이동하는 두 경우를 모든 상황마다 dfs로 진행하고 매번 dfs가 진행할때마다 현재의 name이 모두 A로 이루어져있는지를 판단하는 로직을 사용
밑에 따로 다른 사람의 코드를 참고해서 가져왔는데 해당 코드가 더 최적이라고 생각 추가적으로 이해해보자
=> min=Math.min(min,i+length-next+Math.min(i,length-next));
    먼저 min= length-1로 설정 값으로 나올 수 있는 가장 큰수가 그냥 오른쪽으로 진행되었을때의 수이기 때문
        i+length-next => i를 기준으로 선택했을때의 A를 제외한 나머지를 모두 탐색 가능하게 하는 횟수 (단, 되돌아가는 횟수는 포함되지 않은 수)
            Ex. BCAADE 이고 i가 1(C) 이라면 i+length-next 값은 현재 i에 위치하였을때 A를 제외한 모든 값을 탐색하기 위한 수 => 3(B,D,E) (현재 i 는 B 이고 나머지 A를 제외한 B의 개수는 3개이기때문)
        Math.min(i,length-next) => 이 부분에서 i 가 의미하는 것은 처음에 오른쪽으로 탐색을 시작하고 다시 왼쪽으로 되돌아가는 수를 의미, length-next는 처음에 왼쪽으로 탐색을 진행하여 next까지 도착한 후에, '다시 오른쪽으로 되돌아 갔을때의 수'
            Ex. BCAADE 이고 i가 1(C) 이라면 next는 4(D)가 될것, 처음에 오른쪽으로 탐색한 후에 다시 왼쪽으로 되돌아간다면 다시 1칸만 이동해서 B만 거치면 반대쪽으로 이동이 가능하기에 i를 min으로 비교하는 것, 
               반대로 length-next 부분은 처음에 왼쪽으로 이동하여서 next까지만큼 진행하였을때 다시 오른쪽으로 이동하여 원래자리로 돌아오기위해서는 length-next 즉, 2가 되기에 1과 2를 비교 하여 낮은 1을 추출
               결과적으로 i+length-next = 3 , Math.min(i,length-next) = 1 이 되기때문에 정답은 4가된다.
        이런식으로 i를 0 부터 length-1까지 반복문을 돌려서 최적의 해를 찾아내는것


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
