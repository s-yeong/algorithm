import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i=0; i<N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        solution();
        System.out.println(answer);
    }
    static int N,M;
    static int answer=0;
    static char[][] board;
    static boolean[][] ch;

    static void solution() {
        dp = new int[N][M];
        ch = new boolean[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                answer += DFS(j, i);
            }
        }
    }
    static int[][] dp;
    static int DFS(int x, int y) {

        // 범위 벗어낫는지
        if(y >= N || x >= M || x < 0 || y < 0) {
            return 1;
        }

        // 방문했는지
        if(ch[y][x]) return dp[y][x];

        // 방문 안했으면
        else {
            ch[y][x] = true;  // 방문

            // 이동
            int nx = x;
            int ny = y;
            if(board[y][x] == 'U') {
                ny=y-1;
            }
            else if(board[y][x] == 'R') {
                nx=x+1;
            }
            else if(board[y][x] == 'D') {
                ny=y+1;
            }
            else {
                nx=x-1;
            }

            return dp[y][x] = DFS(nx, ny);
        }

    }

}