import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        int[][] dis = new int[n][m];
        Queue<int[]> Q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            Arrays.fill(dis[i], -1);
        }

        for(int i=0; i<n; i++) {

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) dis[i][j] = 0;
                if(board[i][j] == 2) {
                    dis[i][j] = 0;
                    Q.offer(new int[]{j,i});
                }
            }
        }

        /**
         * n : 세로, m : 가로
         * 모든 지점에 대해서 목표지점 까지 거리 구하기
         * 1. 가로와 세로로만 움직일 수 있음
         * 2. 0: 갈수 없는 땅, 1: 갈 수 있는 땅, 2: 목표지점(한개)
         * 3. 출력방법
         * 3-1. 원래 갈 수 없는 땅인 위치는 0 출력
         * 3-2. 갈 수 있는 땅인데 도달할 수 없는 경우 -1 출력
         * 3-3. 갈 수 있으면 거리 출력
         *
         * 1. 목표지점 위치 저장
         * 2. 이전 지점이에서 +1하기(메모이제이션?)
         * 3. 시작 지점을 목표지점 위치로
         * 4. bfs로 거리 배열 따로 만들기
         */

        int[][] answer = bfs(Q, board, dis, n, m);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] bfs(Queue<int[]> Q, int[][] board, int[][] dis, int n, int m) {



        while(!Q.isEmpty()) {

            int[] cur = Q.poll();

            for(int i=0; i<4; i++) {

                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 갈수 있는 땅이면서 탐색X(-1)
                if(nx >= 0 && ny>= 0 && nx < m && ny < n && board[ny][nx] == 1 && dis[ny][nx] == -1) {

                    // 현재 거리 +1
                    dis[ny][nx] = dis[cur[1]][cur[0]] + 1;
                    Q.offer(new int[]{nx,ny});
                }
            }
        }

        return dis;
    }
}