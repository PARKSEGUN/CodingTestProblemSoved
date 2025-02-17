import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    /*
    * 특정 장애물을 미리 설치해두고 선생님의 좌표를 돌아가면서 선생님이 학생을 한명이라도 발견할 수 있을지를 판단해서 해결
    * 특정 장애물을 설치하는 것은 3중 반복문으로 해결, 만약 장애물의 개수가 많아진다면 재귀문으로 해결해야함
    * 선생님의 좌표에서 4가지 방향으로 계속적으로 특정 장애물이나 또다른 선생님이나 학생을 만날때까지 반복해서 이동
    * 선생님들 중에 한명이라도 학생을 발견하면 안되기에 and 연산 사용
    * */
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    private static char[][] map;
    private static List<int[]> teacherPoints = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        setting();

        if (findAnswer()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    private static boolean findAnswer() {
        int j1 = 0, j2 = 0, j3 = 0;
        for (j1 = 0; j1 < N * N; j1++) {
            if (map[j1 / N][j1 % N] == 'X') {
                map[j1 / N][j1 % N] = 'O';
                for (j2 = j1 + 1; j2 < N * N; j2++) {
                    if (map[j2 / N][j2 % N] == 'X') {
                        map[j2 / N][j2 % N] = 'O';
                        for (j3 = j2 + 1; j3 < N * N; j3++) {
                            if (map[j3 / N][j3 % N] == 'X') {
                                map[j3 / N][j3 % N] = 'O';
//                                printMap();
                                boolean isAnswerFind = true;
                                for (int j = 0; j < teacherPoints.size(); j++) {
                                    int tx = teacherPoints.get(j)[0];
                                    int ty = teacherPoints.get(j)[1];
                                    isAnswerFind &= !findStudent(tx, ty);
                                }
                                if (isAnswerFind == true) {
                                    return true;
                                }
                                map[j3 / N][j3 % N] = 'X';
                            }
                        }
                        map[j2 / N][j2 % N] = 'X';
                    }
                }
                map[j1 / N][j1 % N] = 'X';
            }
        }
        return false;
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean findStudent(int tx, int ty) {
//        System.out.println(tx + " " + ty);
        int[] dix = {-1, 0, 1, 0};
        int[] diy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int dx = tx + dix[i];
            int dy = ty + diy[i];
            while (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                if (map[dx][dy] == 'O') break;
                if (map[dx][dy] == 'T') break;
                if (map[dx][dy] == 'S') {
//                    System.out.println("학생 만났다! : " + dx + " " + dy);
                    return true;
                }
                dx += dix[i];
                dy += diy[i];
            }

        }
//        System.out.println("학생 못 ! 만났다! : ");

        return false;
    }

    private static void setting() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    teacherPoints.add(new int[]{i, j});
                }
            }
        }
    }
}
