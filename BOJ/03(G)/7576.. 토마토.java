import java.io.*;
import java.util.*;

class Main {

    static int M,N;
    static Queue<int[]> Q;
    static int[][] board, dis;
    // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        dis = new int[N][M];
        Q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1) Q.offer(new int[]{j, i});
            }
        }

        BFS();

        int answer = 0;
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] == 0) flag = false;
                answer = Math.max(answer, dis[i][j]);
            }
        }

        if(!flag) System.out.println(-1);
        else System.out.println(answer);
    }

    static void BFS() {

        while(!Q.isEmpty()) {

            int[] tmp = Q.poll();
            for(int i=0; i<4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < M && ny < N && board[ny][nx] == 0) {
                    dis[ny][nx] = dis[tmp[1]][tmp[0]] + 1;
                    board[ny][nx] = 1;
                    Q.offer(new int[]{nx, ny});
                }
            }
        }


    }
}