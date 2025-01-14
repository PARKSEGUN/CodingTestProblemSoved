열쇠를 회전시키고 이동시키는 경우를 모두 확인하기위해서 열쇠를 회전시키는 함수와
열쇠를 이동시키는 함수를 작성
 -> 열쇠를 이동시키는 함수는 큰 틀을 만들어주고 가운데에 lock 을 놓아주고 key를 큰 틀의 왼쪽위부터 시작해서 오른쪽 아래까지 이동시키면서 lock이 다 채워졌는지 확인

#include <string>
#include <vector>
#include <iostream>

using namespace std;

//열쇠가 맞는지 확인하는 함수
bool correct(vector<vector<int>>& vec, vector<vector<int>>& key, vector<vector<int>>& lock) {
    //lock 매핑
    for (int i = 0; i < lock.size(); i++) {
        for (int j = 0; j < lock.size(); j++) {
            if (vec[i + key.size() - 1][j + key.size() - 1] == 0)
                return false;
        }
    }
    return true;
}
//열쇠를 이동시키는 함수
bool moveKey(vector<vector<int>>& key, vector<vector<int>>& lock) {
    int NM = lock.size() + key.size() * 2 - 2;

    for (int k = 0; k < NM - key.size() + 1; k++) {
        for (int l = 0; l < NM - key.size() + 1; l++) {
            vector<vector<int>> vec(NM, vector<int>(NM, 0)); //2차원 벡터 초기화
            //lock 매핑
            for (int i = 0; i < lock.size(); i++) {
                for (int j = 0; j < lock.size(); j++) {
                    vec[i + key.size() - 1][j + key.size() - 1] = lock[i][j];
                }
            }
            //key 매핑
            bool overlab = false; //겹치는지 확인
            for (int i = 0; i < key.size(); i++) {
                if (overlab == true)
                    break;
                for (int j = 0; j < key.size(); j++) {
                    if (key[i][j] == 1)
                        if (vec[k + i][l + j] == 1) {
                            overlab = true;
                            break;
                        }
                        else
                            vec[k + i][l + j] = key[i][j];
                }
            }
            if (overlab == true)
                continue;
            if (correct(vec, key, lock) == true)
                return true;
        }
    }
    return false;
}
//키를 회전시키는 함수
void rotationKey(vector<vector<int>>& key) {
    int M = key.size();
    vector<vector<int>> newKey(M, vector<int>(M, 0));
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < M; j++) {
            newKey[i][j] = key[j][M - i - 1];
        }
    }
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < M; j++) {
            key[i][j] = newKey[i][j];
        }
    }
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    for (int i = 0; i < 4; i++) {
        rotationKey(key);
        if (moveKey(key, lock) == true)
            return true;
    }
    return false;
}
