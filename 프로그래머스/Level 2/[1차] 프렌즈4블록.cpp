같은 모양의 블록을 없애주는 함수와, 사라진 칸으로 내려주는 함수를 만들어줌
빈공간을 표시하기위해 0표시 result값을 알기위해서 중간에 1로 설정
블록 내려주는 함수는 while 문으로 해결

#include <string>
#include <vector>
#include<iostream>
#include<algorithm>

using namespace std;

int result;

void boardDown(vector<string>& board){
    for(int i=board.size()-2;i>=0;i--)
        for(int j=0;j<board[i].size();j++)
            if(board[i][j]!='0'){
                int k=0;
                while(i+k+1<board.size()&&board[i+k+1][j]=='0'){
                    swap(board[i+1+k][j],board[i+k][j]);
                    k++;
                }
            }
}

bool deleteBoard(vector<string>& board){
    vector<string> vec=board;
    bool chk=false;
    for(int i=0;i<board.size()-1;i++){
        for(int j=0;j<board[i].size()-1;j++){
            if(board[i][j]!='0'&&
               board[i][j]==board[i][j+1]&&board[i][j]==board[i+1][j]&&board[i][j]==board[i+1][j+1]){
                chk=true;
                vec[i][j]=vec[i][j+1]=vec[i+1][j]=vec[i+1][j+1]='1';
            }
        }
    }
    //종료조건
    if(chk==false)
        return false;
    
   for(int i=0;i<board.size();i++)
        for(int j=0;j<board[i].size();j++){
            //result 값을 구하기위한 1로 설정
            if(vec[i][j]=='1'){
                result++;
                vec[i][j]='0';
            }
            board[i][j]=vec[i][j];
        }
    return true;
}

int solution(int m, int n, vector<string> board) {
    while(1){
        if(deleteBoard(board)==false)
            return result;
        boardDown(board);
    }  
}
