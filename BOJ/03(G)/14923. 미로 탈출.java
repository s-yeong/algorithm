import java.io.*;
import java.util.*;

class Main {

    static int N,M;
    static int[][] board;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][M+1];

        // 현재 위치
        st = new StringTokenizer(br.readLine());
        int hx = Integer.parseInt(st.nextToken());
        int hy = Integer.parseInt(st.nextToken());

        // 탈출 위치
        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());

        // 입력
        for(int i=1; i<=N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 빈칸:0, 벽:1
        // 딱 한 번 벽을 길로 만들 수 있음
        // 미로에서 탈출할 수 있는 가장 빠른 거리 => BFS

        System.out.println(BFS(hx, hy, ex, ey));

    }
    // 상 하 좌 우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int BFS(int hy, int hx, int ey, int ex) {

        boolean[][][] ch = new boolean[N+1][M+1][2];
        Queue<int[]> Q = new LinkedList<>();
        // 0:x, 1:y, 2:지팡이 사용 -> 0이면 사용X, 1이면 사용O
        // 큐에 꺼냈을 때 방문처리 -> 메모리 초과! -> 큐에 넣을때 방문처리 하기(3차원)

        ch[hy][hx][0] = true;
        Q.offer(new int[]{hx, hy, 0});

        int time = 0;
        while(!Q.isEmpty()) {

            int len = Q.size();
            while(len --> 0) {

                int[] cur = Q.poll();

                for(int i=0; i<4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    int cane = cur[2];

                    // 탈출 지점이면
                    if(nx == ex && ny == ey) return time+1;

                    // 범위 내인지 + 방문X
                    if(nx>=1 && ny>=1 && nx<=M && ny<=N && !ch[ny][nx][cane]) {

                        // 벽이면서 + 지팡이 사용안했을 때
                        if(board[ny][nx] == 1 && cane == 0) {

                            // next 좌표 + 지팡이 사용 체크
                            ch[ny][nx][1] = true;
                            Q.offer(new int[]{nx, ny, 1});
                        }

                        // 빈칸인 경우
                        else if(board[ny][nx] == 0) {
                            ch[ny][nx][cane] = true;
                            Q.offer(new int[]{nx, ny, cane});
                        }
                    }
                }


            }
            time++;
        }
        return -1;
    }

}