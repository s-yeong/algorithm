import java.io.*;
import java.util.*;
class Main {
    // 상 하 좌 우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int N,M;
    static int[][] board;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for(int i=0; i<N; i++) {
            String[] str = br.readLine().split("");
            for(int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(str[j]);
            }
        }
        int answer = BFS();
        System.out.println(answer);
    }

    public static int BFS() {

        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{0, 0});
        board[0][0] = 0;
        int L = 1;
        while (!Q.isEmpty()) {

            int len = Q.size();
            for (int i = 0; i < len; i++) {
                int[] cur = Q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = cur[0] + dx[j];
                    int ny = cur[1] + dy[j];
                    if (nx >= 0 && ny >= 0 && nx < M && ny < N && board[ny][nx] == 1) {
                        if(ny == N-1 && nx == M-1) return L+1;
                        board[ny][nx] = 0;
                        Q.offer(new int[]{nx, ny});
                    }
                }
            }
            L++;
        }
        return 0;
    }
}