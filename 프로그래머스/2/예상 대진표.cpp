간단하게 접할 수 있다고 생각했으나 해결하지 못해서
재귀함수를 사용해서 반씩 나눠가면서 해결했다.
후에 정답을 받고 다른 사람의 코드를 확인해보니 훨씬 더 효율적인 코드를 발견했다.
후에 발견했던 코드를 대진표관련해서 문제를 만났을때 빠르게 해결하기위해 확실하게 암기하자
  
--내가 작성한 코드--
#include <iostream>
#include<algorithm>

using namespace std;

int meeting(int start,int end,int res,int a,int b){
    if(res==0){return 0;}
    int mid=start+(end-start)/2;
    if(a<=mid&&b>mid){return res;}
    else{
        int x=meeting(start,mid,res-1,a,b);
        int y=meeting(mid+1,end,res-1,a,b);
        return max(x,y);
    }
}

int solution(int n, int a, int b)
{
    if(a>b){swap(a,b);}
    int round=0;
    int n_=n;
    while(n_!=1){n_/=2;round++;}
    return meeting(1,n,round,a,b);;
}

--후에 발견한 코드--
  #include <iostream>

using namespace std;

int solution(int n, int a, int b)
{
    a--;
    b--; 
    int answer=0;
    while(a!=b){
        a=a/2;
        b=b/2;
        answer++;
    }
    return answer;
}

//a--,b--를 왜 해줬는지를 다시 
