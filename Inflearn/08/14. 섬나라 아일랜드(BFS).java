import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    // 상, 하, 좌, 우, 좌상, 우상, 좌하, 우하
    static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[][] board;
    static int n;
    static Queue<Point> Q = new LinkedList<>();

    public static void BFS(int x, int y) {

        Q.offer(new Point(x, y));

        while(!Q.isEmpty()) {
            Point tmp = Q.poll();

            for(int i=0; i<8; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if(nx >=0 && nx<n && ny>=0 && ny<n && board[nx][ny] == 1) {
                    board[nx][ny] = 0;
                    Q.offer(new Point(nx, ny));
                }
            }
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        board = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int cnt = 0;
        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = 0;
                    BFS(i, j);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);


    }
}