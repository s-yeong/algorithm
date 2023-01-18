import java.io.*;
import java.util.*;

class Main {

    static int M,N;
    static int[][] board;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[M][N];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 로봇 출발 지점 위치, 바라보는 방향 - 행 열 방향
        st = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(st.nextToken()) - 1;
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sd = Integer.parseInt(st.nextToken());

        // 로봇 도착 지점 위치, 바라보는 방향 - 행 열 방향
        st = new StringTokenizer(br.readLine());
        int ey = Integer.parseInt(st.nextToken()) - 1;
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ed = Integer.parseInt(st.nextToken());

        /**
         * 로봇을 도착 지점에 원하는 방향으로 이동시키는데 필요한 최소 명령 횟수
         */

        //명령1 : 1, 2, 3 현재 방향 움직이기
        //명령2 : 왼쪽 또는 오른쪽 90도 회전


        //BFS
        //1.3중 체크배열 위치+방향
        //2. 움직이다가 벽만나면 break

        // next에서 정답 체크시 첫 시점에서 정답인지 확인해야함!
        if(sx==ex&&sy==ey&&sd==ed) System.out.println(0);
        else System.out.println(BFS(sx, sy, sd, ex, ey, ed));
    }
    static boolean[][][] ch;
    static int BFS(int sx, int sy, int sd, int ex, int ey, int ed) {

        ch = new boolean[5][M][N];
        ch[sd][sy][sx] = true;
        int L = 0;
        // 0:x 1:y 2:방향
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{sx, sy, sd});
        // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4

        while(!Q.isEmpty()) {

            int len = Q.size();

            while(len --> 0) {

                int[] cur = Q.poll();
                int x = cur[0];
                int y = cur[1];
                int dir = cur[2];

                // 방향움직이기 left or right
                for(int i=0; i<2; i++) {
                    int ndir = turnDir(dir, i);

                    // 정답인지 체크
                    if(x==ex && y==ey && ndir == ed) {
                        return L+1;
                    }

                    // 방문 안했으면
                    if(!ch[ndir][y][x]) {
                        ch[ndir][y][x] = true;
                        Q.offer(new int[]{x, y, ndir});
                    }
                }

                // 로봇 이동 1, 2, 3 => 이동시 1이면 break
                for(int i=1; i<=3; i++) {
                    int[] next = move(x, y, dir, i);
                    // 범위 내인지, 이동할 수 있는점인지, 방문했는지
                    int nx = next[0];
                    int ny = next[1];
                    int ndir = next[2];

                    if(nx>=0 && ny>=0 && nx<N && ny<M) {

                        // 앞으로 가다가 1만나면 break;
                        if(board[ny][nx] == 1) break;

                        // 정답인지 체크
                        if(nx==ex && ny==ey && ndir==ed) {
                            return L+1;
                        }

                        // 방문 안했으면
                        if(!ch[ndir][ny][nx]) {
                            ch[ndir][ny][nx] = true;
                            Q.offer(new int[]{nx, ny, ndir});
                        }
                    }
                }
            }
            L++;
        }
        return 0;
    }

    // 방향 움직이기
    static int turnDir(int dir, int d) {

        // left : d=0, right : d=1
        // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4

        //left
        if(d==0) {
            if(dir==1) return 4;
            if(dir==2) return 3;
            if(dir==3) return 1;
            if(dir==4) return 2;
        }

        //right
        else if(d==1) {
            if(dir==1) return 3;
            if(dir==2) return 4;
            if(dir==3) return 2;
            if(dir==4) return 1;
        }
        return 0;
    }

    static int[] move(int x, int y, int dir, int go) {

        // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4

        // 동쪽 -> x+go
        if(dir==1) {
            x = x+go;
        }
        // 서쪽 -> x-go
        else if(dir==2) {
            x = x-go;
        }
        // 남쪽 -> y+go
        else if(dir==3) {
            y = y+go;
        }
        // 북쪽 -> y-go
        else if(dir==4) {
            y = y-go;
        }

        return new int[]{x, y, dir};
    }

}