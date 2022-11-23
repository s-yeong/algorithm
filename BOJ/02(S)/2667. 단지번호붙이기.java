import java.util.*;
import java.io.*;

class Main {

    static ArrayList<Integer> answer = new ArrayList<>();
    static int N, cnt;
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static void DFS(int x, int y) {

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >=0 && nx<N && ny >=0 && ny<N && board[ny][nx] == 1) {
                board[ny][nx] = 0;
                cnt++;
                DFS(nx, ny);
            }
        }

    }


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());   //열
        board = new int[N][N];

        for(int i=0 ; i<N; i++) {
            String[] sArr = br.readLine().split("");
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] == 1) {
                    cnt = 1;
                    board[i][j] = 0;
                    DFS(j, i); // x, y
                    answer.add(cnt);
                }
            }
        }

        System.out.println(answer.size());
        Collections.sort(answer);
        for(int x : answer) System.out.println(x);

    }
}
