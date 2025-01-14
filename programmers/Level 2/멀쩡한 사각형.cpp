접근 방법을 찾지못해서 해결하지 못했다
패턴이 있는 직사각형을 최대공약수로 나눠지는 것을 알았지만 나눠진 직사각형을 대각선으로 나누면 가로+세로-1 개의 조각이 사라진다는것을 알지못했다
패턴이 생기지않는 직사각형(X,Y)을 대각선으로 나누면 X+Y-1 개의 조각이 제거된다
패턴이 생기는 직사각형 형태는 최대 공약수로 나눠서 한조각의 패턴을 구할 수 있다.
  
using namespace std;

long long gcd(int w,int h){
    if(w==0)
        return h;
    return gcd(h%w,w);
}

long long solution(int w,int h) {
    return (long long)w*h-(long long)(w+h-gcd(w,h));
}
