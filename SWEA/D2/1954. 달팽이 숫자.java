import java.io.*;
import java.util.*;

/*
    주석 -> nx, ny 넘어가기 전에 처리하는 방식
 */
public class Main {

    // 시계방향 - 우, 상, 좌, 하
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void DFS(int x, int y, int[][] board, int cnt) {
        if(board[y][x] != 0) return;
        else {
            board[y][x] = cnt;
        }

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx < board.length && ny < board.length) {
//                if(nx>=0 && ny>=0 && nx < board.length && ny < board.length && board[ny][nx] == 0) {
//                board[ny][nx] = cnt;
                DFS(nx, ny, board, cnt+1);

            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for(int i=0; i<T; i++) {

            int n = Integer.parseInt(br.readLine().trim());

            int[][] board = new int[n][n];

//            board[0][0] = 1;
//            DFS(0, 0, board, 2);
            DFS(0, 0, board, 1);

            sb.append("#").append(i+1).append("\n");
            for(int j=0; j<n; j++) {
                for(int k=0; k<n; k++) {
                    sb.append(board[j][k]).append(" ");
                }
                sb.append("\n");
            }
        }






        System.out.println(sb);


    }
}