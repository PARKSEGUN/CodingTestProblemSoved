// 1.map을 사용해서 장르별 총 재생횟수를 구해서 따로 vector에 재생횟수가 많은 순으로 정렬시켜줌
// 2.map에 장르별로 고유번호를 저장해준다
// 3.정렬된 vector에 장르이름을 key값으로 고유번호를 찾고 그 고유번호들의 plays 값을 비교해서 각 장르마다 2개씩 answer에 push 해준다.


//해결후 다른 사람들의 풀이를 본후
//map에 저장되어있는 정보를 갖고 내장되어있는 sort 알고리즘을 사용하고 싶었지만 해결하지못해서 버블정렬을 사용해서 정렬했다.
//map으로 저장시켜주었던 정보를 2차원 벡터에 옮겨주는 것이 가능  for(auto x:(map이름)){} 사용
//map<string,vector<int>> 를 사용해서 vector<int> 에 고유번호를 넣어주었지만 
//map<string,vector<pair<int,int>>> 를 사용해서 plays 값과 고유번호를 동시에 저장 시켜주면 
//bool cmp(pair<int,int>)를 사용해서 sort 알고리즘을 사용하는 것이 가능해져서 더 효율적이고 간단한 코드를 작성할 수 있었다.

#include <string>
#include <vector>
#include<unordered_map>
#include<iostream>
#include<algorithm>

using namespace std;

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    unordered_map<string,int> count;    //장르별 재생횟수
    unordered_map<string,vector<int>> genre;    //장르별 고유번호
    vector<string> vec; //장르를 많이 재생된 순으로 정렬하기위한 vector
    //장르별 재생횟수를 구하기, 장르별 고유번호들 저장
    for(int i=0;i<genres.size();i++){
        if(count.find(genres[i])==count.end()){vec.push_back(genres[i]);}//장르별 재생횟수를 정렬하기위한 vec
        count[genres[i]]+=plays[i];  //총 재생횟수 저장
        genre[genres[i]].push_back(i);  //장르별 고유번호 저장
    }
    //장르를 재생된 순으로 정렬(버블정렬사용)
    for(int i=0;i<vec.size()-1;i++){
        for(int j=i+1;j<vec.size();j++){
            if(count[vec[j]]>count[vec[i]]){
                string tmp=vec[i];
                vec[i]=vec[j];
                vec[j]=tmp;
            }
        }
    }
    //정렬된 vector에 장르이름을 key값으로 고유번호를 찾고 그 고유번호들의 plays 값을 비교해서 각 장르마다 최댓값2개씩 answer에 push 해준다.
    for(int i=0;i<vec.size();i++){ 
        if(genre[vec[i]].size()==1){answer.push_back(genre[vec[i]][0]);}  //장르에 들어있는 곡이 한곡인경우
        else{
            int cnt=0;
            while(cnt!=2){  //두개를 
                int max=-1;
                int idx=0;
                for(int j=0;j<genre[vec[i]].size();j++){
                    if(plays[genre[vec[i]][j]]>max){  //최댓값을 찾기위함
                        max=plays[genre[vec[i]][j]];
                        idx=genre[vec[i]][j];
                    }
                }
                answer.push_back(idx);
                plays[idx]=0;
                cnt++;
            }
            
        }
    }
    return answer;
}
