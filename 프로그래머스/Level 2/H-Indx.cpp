문제를 제대로 이해하지 못해서 찝찝하게 해결함

#include <string>
#include <vector>
#include<algorithm>

using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    sort(citations.begin(),citations.end());
    int start=1;
    while(1){
        int small=0;
        int big=0;
        for(int i=0;i<citations.size();i++){
            if(citations[i]<start){small++;}
            else if(citations[i]>start){big++;}
            else{small++;big++;}
        }
        
        if(small<=start&&big>=start){answer=max(answer,start);}
        start++;
        if(start>citations[citations.size()-1]){return answer;}
    }
}


---------------참고코드-----------------
    #include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
    sort(citations.begin(), citations.end(), greater<int>());

    for (int i = 0; i < citations.size(); ++i) {
        if (citations[i] < i + 1) {
            return i;
        }
    }

    return citations.size();
}
