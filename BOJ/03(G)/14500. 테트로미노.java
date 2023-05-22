import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] board;
    static boolean[][] ch;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /**
         * 테트로미노 총 5가지
         * 크기가 n X m인 종이 위에 테트로미노 하나를 놓으려고 한다. (각각의 칸에는 정수가 하나 쓰여져 있음)
         * 테트로미노 하나를 적절히 놓아서 테트로니모가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램
         * 회전, 대칭 가능
         */

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ch = new boolean[n][m];
        answer = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!ch[i][j]) {
                    ch[i][j] = true;
                    recursion(1, board[i][j], j, i);
                    ch[i][j] = false;
                }
            }
        }

        System.out.println(answer);
    }

    static int answer;;
    // 상하좌우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static void recursion(int L, int sum, int x, int y) {

        if(L==4) {
            answer = Math.max(answer, sum);
        }
        else {

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0 && ny>=0 && nx<m && ny<n && !ch[ny][nx]) {

                    if(L==2) {
                        ch[ny][nx] = true;
                        recursion(L + 1, sum + board[ny][nx], x, y);
                        ch[ny][nx] = false;
                    }

                    ch[ny][nx] = true;
                    recursion(L + 1, sum + board[ny][nx], nx, ny);
                    ch[ny][nx] = false;
                }

            }

        }
    }

}
