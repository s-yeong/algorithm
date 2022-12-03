import java.util.*;
import java.io.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int R, C;
    static char[][] board;
    static int[][] ch;
    static Queue<Point> Q = new LinkedList<>();

    static boolean BFS() {


        while(!Q.isEmpty()) {
            Point tmp = Q.poll();

            for(int i=0; i<4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < C && ny < R && ch[ny][nx] == 0) {
                    ch[ny][nx] = 1;
                    if(board[ny][nx] == 'S') return false;
                    else {
                        board[ny][nx] = 'D';
                    }
                }
            }
        }
        return true;
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        ch = new int[R][C];

        for(int i=0; i<R; i++) {
            String[] split = br.readLine().split("");
            for(int j=0; j<C; j++) {
                board[i][j] = split[j].charAt(0);
                if(board[i][j] == 'W') {
                    Q.offer(new Point(j, i));
                    ch[i][j] = 1;
                }
            }
        }

        boolean flag = BFS();

        if(!flag) System.out.println(0);
        else {
            System.out.println(1);
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }


    }
}
