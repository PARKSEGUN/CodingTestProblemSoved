행렬의 곱셈에 대해 제대로 알지못해서 오래걸렸던 문제이다.
나중에 많이 쓰인다고 하니 확실하게 알아두자

#include <string>
#include <vector>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> arr1, vector<vector<int>> arr2) {
    vector<vector<int>> answer;
    for(int i=0;i<arr1.size();i++){
        vector<int> vec;
        for(int j=0;j<arr2[0].size();j++){
            int sum=0;
            for(int k=0;k<arr1[0].size();k++){
                sum+=arr1[i][k]*arr2[k][j];
            }                              
            vec.push_back(sum);
        }
        answer.push_back(vec);
    }
    return answer;
}
