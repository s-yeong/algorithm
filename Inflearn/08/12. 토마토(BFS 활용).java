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

    // 왼쪽 오른쪽 앞 뒤
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] board, dis;
    static int n, m;
    static Queue<Point> Q = new LinkedList<>();
    public static void BFS() {

        while(!Q.isEmpty()) {

                Point tmp = Q.poll();
                for (int i = 0; i < 4; i++) {

                    int nx = tmp.x + dx[i];
                    int ny = tmp.y + dy[i];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 0) {
                        board[nx][ny] = 1;
                        Q.offer(new Point(nx, ny));
                        dis[nx][ny] = dis[tmp.x][tmp.y] + 1;

                    }

                }
            }

    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();

        board = new int[n][m];
        dis = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                board[i][j] = sc.nextInt();
                if(board[i][j] == 1) Q.offer(new Point(i, j)); // 1이 되는 인덱스 찾기 (시작점)
            }
        }

        BFS();
        boolean flag = true;
        // 익었는지 아닌지
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == 0) flag = false;
            }
        }

        int answer = Integer.MIN_VALUE;
        if(flag) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    answer = Math.max(answer, dis[i][j]);
                }
            }
            System.out.println(answer);
        } else System.out.println(-1);


    }
}