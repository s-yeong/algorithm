import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 배추 밭 가로길이, 세로길이, 배추 개수
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            board = new int[N][M];
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[y][x] = 1;
            }

            int answer = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(board[i][j] == 1) {
                        answer++;
                        board[i][j] = 0;
                        DFS(j, i);
                    }
                }
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
    // 상, 하, 좌, 우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int M,N;
    static int[][] board;
    static void DFS(int x, int y) {

        for(int i=0; i<4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < M && ny < N && board[ny][nx] == 1) {
                board[ny][nx] = 0;
                DFS(nx,ny);
            }
        }

    }
}