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

    static int[][] board = new int[8][8];   // 격자 정보 (입력값)
    static int[][] dis = new int[8][8];     // 경로 길이

    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void BFS(int x, int y) {

        Queue<Point> Q = new LinkedList<>();
        board[x][y] = 1;    // 시작점 체크
        Q.offer(new Point(x, y));   // 시작점 넣기

        while(!Q.isEmpty()) {
                Point tmp = Q.poll();

                for(int i=0; i<4; i++) {
                    int nx = tmp.x + dx[i];
                    int ny = tmp.y + dy[i];
                    if(nx >=1 && nx<=7 && ny >=1 && ny<=7 && board[nx][ny] == 0) {
                        board[nx][ny] = 1;
                        Q.offer(new Point(nx, ny));
                        dis[nx][ny] = dis[tmp.x][tmp.y] + 1;
                    }
                }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for(int i=1; i<=7; i++) {
            for(int j=1; j<=7; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // 출발점 넘김
        BFS(1, 1);

        if(dis[7][7] == 0) {
            System.out.print(-1);
        } else System.out.print(dis[7][7]);

    }
}