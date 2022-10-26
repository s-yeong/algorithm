import java.io.*;
import java.util.*;


public class Main {

    static int C,R, K;
    static int[][] board;
    // 시계 방향 = 상, 우, 하, 좌
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());    // 대기 번호
        // C X R 격자형    - C 가로 R 세로

        board = new int[R + 1][C + 1];
        // ex) C=7 R=6

        int cnt = 0;
        int dis = 0;

        if(K > C*R) {
            System.out.println(0);
            return;
        }

        int x = 1;
        int y = 1;
        board[x][y] = 1;
        cnt++;

        while(cnt != K) {
            cnt++;

            int nx = x + dx[dis];
            int ny = y + dy[dis];

            if(nx >=1 && ny >=1 && nx <= C && ny <= R && board[ny][nx] == 0) {
                x = nx;
                y = ny;
            } else {
                dis = (dis + 1) % 4;
                x = x + dx[dis];
                y = y + dy[dis];
            }
            board[y][x] = 1;
        }

        System.out.println(x + " " + y);





    }
}
