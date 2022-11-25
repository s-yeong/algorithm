import java.util.*;
import java.io.*;

class Main {
    static int cnt = 0;
    static int N, M;
    static int[][] board;
    // 북 동 남 서  0 1 2 3
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};



    // 0 - 빈칸 1 - 벽 2 - 청소
    static void sol1(int r, int c) {
        if(board[r][c] == 0) {
            board[r][c] = 2;    // 청소 완료
            cnt++;
        }
    }


    static void sol(int r, int c, int d) {

        sol1(r, c);
        boolean flag = false;
        int tmp = d;

        // 왼쪽 방향 부터 차례대로 탐색
        for(int i=0; i<4; i++) {
            int nd = (d + 3) % 4;
            int nr = r + dr[nd];
            int nc = c + dc[nd];

            // 청소하지 않은 공간 존재
            if(nr >=0 && nc>=0 && nr<N && nc<M && board[nr][nc] == 0) {
                sol(nr, nc, nd);
                flag = true;
                break;
            }
            else d = nd;
        }

        if(!flag) {
            int bd = (tmp + 2) % 4;
            int br = r + dr[bd];
            int bc = c + dc[bd];

            // 뒤쪽 방향 벽인 경우 -> 후진
            if (br >= 0 && bc >= 0 && br < N && bc < M && board[br][bc] != 1) {
                sol(br, bc, tmp);
            }
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N X M 크기의 직사각형
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        // r = 북쪽으로부터 떨어진 칸 개수, c = 서쪽으로부터 떨어진 칸의 개수

        // r c d
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        // 북동남서(0123)
        int d = Integer.parseInt(st.nextToken());

        // 0 - 빈칸 1 - 벽
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sol(r, c, d);

        System.out.println(cnt);

    }
}
