end라는 범위를 지정해주는 변수를 설정해주고
end로 while문의 반복 범위를 정해준다.
visited 변수를 통해서 이미 보트에 탑승한 인원인지 체크한다.

#include <string>
#include <vector>
#include<algorithm>

using namespace std;

bool visited[55555];

int solution(vector<int> people, int limit) {
    int answer = 0;
    sort(people.begin(),people.end());
    int end=people.size()-1;
    for(int i=0;i<people.size();i++){
        if(visited[i]==false){
        while(i<end){
            if(people[i]+people[end]<=limit){
                visited[end]=true;
                end--;
                break;
            }
            end--;
        }
        answer++;
        }
    }
    return answer;
}
