//테스트케이스 1번의 시간초과의 원인을 알아내지못함
//DFS를 사용해서 값을 구하게된다면 시간초과가 발생하기때문에
//결과 값을 도출하는 수학적 공식을 사용해야한다.
//다항식과 관련된 수학적 공식을 도출한다.
//3개가 된다면? (a+b+c) + (ab+bc+ca) + (abc)가지
//즉 옷의 종류가 3가지고 각각의 옷의 개수가 a, b, c라면 (x+a)(x+b)(x+c) = x3 + (a+b+c)x2 + (ab+bc+ca)x + (abc)라는 식이 정립됩니다. 보이시죠? 총 조합의 개수가 계수에 다 포함되어 있습니다.

//해당 식의 계수의 합을 구하려면 x=1을 대입해주면 됩니다. 그 후 맨 앞 x3 의 계수는 정답에 포함되지 않으므로 마지막에 1을 빼주는 겁니다.
//따라서 공식을 사용하게되면 DFS를 사용하지 않아도 된다.


#include <string>
#include <vector>
#include<unordered_map>

using namespace std;
/*int sum;
int arr[33];
void DFS(int cnt,int range,vector<int> vec){
    if(cnt>0){
        int x=1;
        for(int i=0;i<cnt;i++){
            x*=arr[i];
        }
        sum+=x;

    }
    for(int i=range;i<vec.size();i++){
        arr[cnt]=vec[i];
        DFS(cnt+1,i+1,vec);
    }
}
*/
int solution(vector<vector<string>> clothes) {
    unordered_map<string,int> um;
    for(int i=0;i<clothes.size();i++){
        if(um.find(clothes[i][1])==um.end()){
            um[clothes[i][1]]=1;
        }
        else{um[clothes[i][1]]++;}
    }
    vector<int>vec;
    for(auto elem : um){
        vec.push_back(elem.second);
    }
   // DFS(0,0,vec);
    int result=1;
    for(int i=0;i<vec.size();i++){
        result*=vec[i]+1;
    }
    result--;
    return result;
}
