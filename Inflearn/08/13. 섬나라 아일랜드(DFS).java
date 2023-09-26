import java.util.*;

public class Main {

    // 상, 하, 좌, 우, 좌상, 우상, 좌하, 우하
    static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[][] board,ch;
    static int n;

    public static void DFS(int x, int y) {

        for(int i=0; i<8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >=0 && nx<n && ny>=0 && ny<n && board[nx][ny] == 1) {
                board[nx][ny] = 0;
                DFS(nx, ny);
            }
        }


    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        board = new int[n][n];
        ch = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int cnt = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == 1) {
                    board[i][j] = 0;
                    DFS(i, j);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }
}