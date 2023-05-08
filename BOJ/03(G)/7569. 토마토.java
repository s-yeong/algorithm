import java.io.*;
import java.util.*;
public class Main {

    static int n,m,h;
    static int[][][] board;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   // 상자의 가로 칸의 수
        n = Integer.parseInt(st.nextToken());   // 상자의 세로 칸의 수
        h = Integer.parseInt(st.nextToken());   // 상자
        // 가장 밑의 상자부터 가장 위의 상자까지 저장된 토마토 정보
        board = new int[h][n][m];
        Queue<int[]> Q = new LinkedList<>();

        for(int k=h-1; k>=0; k--) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    board[k][i][j] = Integer.parseInt(st.nextToken());
                    if(board[k][i][j] == 1) {
                        // x,y,z
                        Q.offer(new int[]{j,i,k});
                    }
                }
            }
        }

        // 1 : 익은토마토, 0: 익지않은 토마토, -1: 토마토 X
        // n개의 줄이 h번 반복 =>  x,y,z가 필요
        /**
         * 며칠이 지나면 토마토가 다 익게 되는지
         * 1. 토마토가 모두 익었는지 검증
         * 2. 토마토가 모두 익지 못하는 상황인지 검증
         *
         * 매번 토마토가 다 익었는지 검증해야하나? => 1으로 들어간 것에서 +1 해서 시간을 더해주는 방식으로 풀기
         * 그렇게 모두 끝낸 다음 0이 되는 부분이 있으면 토마토가 익지 못하는 상황
         */
        bfs(Q);

        int max = Integer.MIN_VALUE;
        boolean flag = true;
        for(int i=0; i<h; i++) {
            for(int j=0; j<n; j++) {
                for(int k=0; k<m; k++) {
                    // 토마토가 모두 익지 못하는 상황이면,
                    if(board[i][j][k] == 0) {
                        flag = false;
                        break;
                    }
                    if(board[i][j][k] > max) {
                        max = board[i][j][k];
                    }
                }
            }
        }

        if(!flag) System.out.println(-1);
            // 토마토가 익은게 1부터 시작했기 때문에 -1 해줘야함!!
        else System.out.println(max-1);
    }

    // 상,하,좌,우,위,아래
    static int[] dx = {0,0,-1,1,0,0};
    static int[] dy = {-1,1,0,0,0,0};
    static int[] dz = {0,0,0,0,-1,1};
    // 위, 아래
    static void bfs(Queue<int[]> Q) {

        while(!Q.isEmpty()) {

            int[] cur = Q.poll();

            for(int i=0; i<6; i++) {
                // x,y,z
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int nz = cur[2] + dz[i];

                if(nx >= 0 && ny >= 0 && nz >= 0 && nx < m && ny < n && nz < h && board[nz][ny][nx] == 0) {
                    board[nz][ny][nx] = board[cur[2]][cur[1]][cur[0]] + 1;
                    Q.offer(new int[]{nx, ny, nz});
                }
            }
        }
    }
}