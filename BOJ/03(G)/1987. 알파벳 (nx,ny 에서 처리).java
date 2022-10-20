import java.util.*;


public class Main {

    static int R, C;
    static char[][] board;
    static int[] ch = new int[91];
    static int max = Integer.MIN_VALUE;
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static void DFS(int x, int y, int cnt) {

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 1 && nx <= R && ny >= 1 && ny <= C && ch[board[nx][ny]] == 0) {
                    ch[board[nx][ny]] = 1;
                    max = Math.max(max, cnt + 1);
                    DFS(nx, ny, cnt + 1);
                    ch[board[nx][ny]] = 0;

                }
            }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        board = new char[R+1][C+1];
        for(int i=1; i<=R; i++) {
            String str = sc.next();
            for(int j=1; j<=C; j++) {
                board[i][j] = str.charAt(j-1);
            }
        }

        ch[board[1][1]] = 1;
        max = 1;    // 1 1 A인 경우가 최대값 1
        DFS(1, 1, 1);

        System.out.println(max);
    }
}