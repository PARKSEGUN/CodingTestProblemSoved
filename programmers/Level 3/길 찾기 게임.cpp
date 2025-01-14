rightNode, leftNode의 벡터를 만들어주고 unorderedMap을 사용해서 해당 노드의 번호로 노드의 정보를 가져올수 있게설정
노드의 번호를 또 기억해줘야 하기때문에 새로운 node라는 번호까지 기억하는 vector를 설정해준다.
노드의 번호로 노드의 좌표값을 가져오고 좌표값으로 현재의 노드가 부모의 노드 보다 왼쪽인지 오른쪽인지 비교한다(추가로 주어진 노드의 정보를 y값에따라서 내림차순으로 정렬후에 y값이 같으면 x값 기준으로 오름차순 정렬을 시켯기때문에 이진트리를 구성할 수 있다)
왼쪽이라면 부모노드의 왼쪽노드에 값이 존재하는지 알아보고 존재한다면 그값을 부모로 넣어주고 재귀시킨다 값이 존재하지않다면 현재의 노드를 부모의 노드 왼쪽에 넣어준다. -> 오른쪽일때도 동일

#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_map>

using namespace std;

vector<int> rightNode;  //rightNode[a]=b -> a 노드 오른쪽에 연결된 b
vector<int> leftNode;   //leftNode[a]=b -> a 노드 왼쪽에 연결된 b
unordered_map<int,vector<int>> um;  //노드번호로 노드정도를 가져오기위함
//정렬 조건 설정
bool cmp(vector<int> a,vector<int> b){
    if(a[1]==b[1]){
        return a[0]<b[0];
    }
    return a[1]>b[1];
}
//vector 사용전 초기화
void setting(int n){
    for(int i=0;i<=n;i++){
        rightNode.push_back(0);
        leftNode.push_back(0);
    }
}
//이진 트리이기때문에 노드가 들어왔을때에 연결 함수
void connect(int parent,int cur){
    if(um[parent][0]>um[cur][0]){
        if(leftNode[parent]!=0){
            connect(leftNode[parent],cur);
        }else{
            leftNode[parent]=cur;
        }
    }else{
        if(rightNode[parent]!=0){
            connect(rightNode[parent],cur);
        }else{
            rightNode[parent]=cur;
        }
    }
}
//전위 순회
vector<int> preorder(vector<int>& vec,int cur){
    vec.push_back(cur);
    if(leftNode[cur]!=0){
        preorder(vec,leftNode[cur]);
    }
    if(rightNode[cur]!=0){
        preorder(vec,rightNode[cur]);
    }
    return vec;
}
//후위 순회
vector<int> postorder(vector<int>& vec,int cur){
    if(leftNode[cur]!=0){
        postorder(vec,leftNode[cur]);
    }
    if(rightNode[cur]!=0){
        postorder(vec,rightNode[cur]);
    }
    vec.push_back(cur);
    return vec;
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<vector<int>> node;
    vector<vector<int>> answer;
    setting(nodeinfo.size());
    for(int i=0;i<nodeinfo.size();i++){
            vector<int> vec;
        vec.push_back(nodeinfo[i][0]);
        vec.push_back(nodeinfo[i][1]);
        vec.push_back(i+1);
        node.push_back(vec);
        um[i+1]=node[i];
    }
    sort(node.begin(),node.end(),cmp);
    int root=node[0][2];
    for(int i=1;i<nodeinfo.size();i++){
            connect(root,node[i][2]);
    }
    vector<int> pre;
    vector<int> post;
    pre=preorder(pre,root);
    post=postorder(post,root);
    answer.push_back(pre);
    answer.push_back(post);
    return answer;
}
