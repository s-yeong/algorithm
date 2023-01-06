import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        // x좌표, y좌표
        int[] red = new int[2];
        int[] blue = new int[2];

        for(int i=0; i<N; i++) {
            board[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(board[i][j] == 'R') {
                    red[0] = j;
                    red[1] = i;
                }
                if (board[i][j] == 'B') {
                    blue[0] = j;
                    blue[1] = i;
                }
            }
        }

        // 빨간 구슬 좌표, 파란 구슬 좌표
        // 이동하는 메서드

        System.out.println(BFS(new Marble(red, blue)));
    }
    static char[][] board;
    static int M,N;
    // 왼 오 위 아래
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Marble {
        int[] red;
        int[] blue;

        public Marble(int[] red, int[] blue) {
            this.red = red;
            this.blue = blue;
        }
    }

    static int BFS(Marble marble) {

        Queue<Marble> Q = new LinkedList<>();
        Q.offer(marble);
        int count = 0;
        // red x y, blue x y
        boolean[][][][] ch = new boolean[M][N][M][N];
        ch[marble.red[0]][marble.red[1]][marble.blue[0]][marble.blue[1]] = true;
        while(!Q.isEmpty()) {
            int len = Q.size();
            while(len --> 0) {
                Marble cur = Q.poll();

                // 움직이기
                for(int i=0; i<4; i++) {
                    Marble next = move(i, cur);

                    // 파란 구슬?
                    if (board[next.blue[1]][next.blue[0]] == 'O') continue;
                    // 빨간 구슬?
                    else if (board[next.red[1]][next.red[0]] == 'O') return count+1;

                    if (!ch[next.red[0]][next.red[1]][next.blue[0]][next.blue[1]]) {
                        Q.offer(next);
                        ch[next.red[0]][next.red[1]][next.blue[0]][next.blue[1]] = true;
                    }
                }
            }
            count++;
        }
        return -1;
    }

    static Marble move(int d, Marble marble) {

        int rx = marble.red[0];
        int ry = marble.red[1];
        int bx = marble.blue[0];
        int by = marble.blue[1];
        int disR = 0;
        int disB = 0;

        // 빨간 구슬 움직이기
        while (board[ry + dy[d]][rx + dx[d]] != '#') {
            rx = rx + dx[d];
            ry = ry + dy[d];
            disR++;
            if(board[ry][rx] == 'O') break;
        }

        // 파란 구슬 움직이기
        while (board[by + dy[d]][bx + dx[d]] != '#') {
            bx = bx + dx[d];
            by = by + dy[d];
            disB++;
            if(board[by][bx] == 'O') break;
        }

        // 겹친 경우
        if(rx==bx && ry==by && board[by][bx] != 'O') {

            if(disR>disB) {
                // 빨강뺵
                rx = rx - dx[d];
                ry = ry - dy[d];
            }
            else {
                // 파랑뺵
                bx = bx - dx[d];
                by = by - dy[d];
            }
        }

        return new Marble(new int[]{rx, ry}, new int[]{bx, by});
    }

}