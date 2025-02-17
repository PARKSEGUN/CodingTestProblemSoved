에라토스테네스의 체를 이용해서 소수를 확인해주고 
n^3 반복문으로 3가지 수의 합을 구한다.

#include <vector>
#include <iostream>
using namespace std;
bool chk[3333];
int solution(vector<int> nums) {
    int answer = 0;
    for(int i=2;i<3333;i++){
        for(int j=i*2;j<3333;j+=i){
            if(chk[j]==false){chk[j]=true;}
        }
    }
    
    for(int i=0;i<nums.size();i++){
        int sum=0;
        for(int j=i+1;j<nums.size();j++){
            for(int k=j+1;k<nums.size();k++){
              sum=nums[i]+nums[j]+nums[k];  
                if(chk[sum]==false){answer++;}
            }
        }
    }
    return answer;
}
