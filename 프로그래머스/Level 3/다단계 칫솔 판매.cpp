map을 사용해서 이름과 index를 저장, 추천해준 사람 정보를 vector로 저장
누가 얼마를 팔았는지를 저장후 while문을 통해서 현재 탐색하고 있는 사람과 그 사람이 받는 돈을 초기화 해주면서 진행
이때 누가 얼마를 팔았는지 구하는 과정에서 팔은 가격을 합산하면 오류 발생
1원의 10%는 0이여서 추천해준사람한테 0원이들어가서 이 과정을 10번 반복해도 0원이 들어가지만
1원씩 10번 더해주고 넘겨주게되면 1원을 넘겨주게된다

#include <string>
#include <vector>
#include<unordered_map>
#include<iostream>

using namespace std;

vector<int> solution(vector<string> enroll, vector<string> referral, vector<string> seller, vector<int> amount) {
    vector<int> answer(enroll.size());
    unordered_map<string,int> um;
    vector<int> recommand(enroll.size());
    vector<int> sale(enroll.size());

    for(int i=0;i<enroll.size();i++){
        um[enroll[i]]=i;
    }
    for(int i=0;i<enroll.size();i++){
        if(referral[i]=="-")
            recommand[i]=-1;
        else
            recommand[i]=um[referral[i]];
    }



    for(int i=0;i<seller.size();i++){
            int cur=um[seller[i]];
            int money=amount[i]*100;
            while(money!=0&&cur!=-1){
                int giveMoney=money/10;
                answer[cur]+=money-giveMoney;
                money=giveMoney;
                cur=recommand[cur];

            }
    }
    return answer;

}
