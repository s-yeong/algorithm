import java.io.*;
import java.util.*;

public class Main {

    static int n, m;

    static int solution(int xs, int ys, int[][] board) {

        int sum = 0;
        for(int i=ys; i<ys+m; i++) {
            for(int j=xs; j<xs+m; j++) {
                sum += board[i][j];
            }
         }
        return sum;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {

            // n, m
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // n줄
            int[][] board = new int[n][n];

            // n X n 배열
            for(int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<n; k++) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for(int j=0; j<=n-m; j++) {
                for(int k=0; k<=n-m; k++) {
                    answer = Math.max(answer, solution(j, k, board));
                }
            }

            sb.append("#").append(i + 1).append(" ").append(answer).append("\n");
        }







        System.out.println(sb);


    }
}