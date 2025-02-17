비트연산으로 문제를 해결하는 방법에대해 많이 알고 넘어가는 기회였다
2진법 끼리의 덧셈 뺄셈은 어떻게 진행되는지
and, or, xor 등등 연산에 대해 알게되었고
2진법으로 이루어진 값의 1의 개수를 세는법, 특정한 부분의 수를 변경하는 법 을 알게되었고
shift 연산도 사용할 수 있게되었다.

#include <string>
#include <vector>
#include <iostream>
#include<cmath>

using namespace std;

vector<long long> solution(vector<long long> numbers) {
    vector<long long> answer;
    for(auto x:numbers){
        if(x%2==0)
            answer.push_back(x+1);
        else{
            vector<int> vec;
            long long tmp=x;
            while(tmp!=0){
                vec.push_back(tmp%2);
                tmp/=2;
            }
            for(int i=0;i<vec.size();i++){
                if(vec[i]==0){
                    vec[i]=1;
                    vec[i-1]=0;
                    break;
                }
                if(vec[i]==1&&i==vec.size()-1){
                    vec[i]=0;
                    vec.push_back(1);
                    break;
                }
            }
            long long val=0;
            for(int i=0;i<vec.size();i++){
                val+=(vec[i]*pow(2,i));
            }
            answer.push_back(val);
        }
    }
    return answer;
}
