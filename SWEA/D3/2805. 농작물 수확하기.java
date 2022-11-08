import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int sum, n;
    static int[][] board;

    static int solution() {

        int s = n/2;
        int e = n/2;
        int ds = -1;
        int de = +1;

        for(int i=0; i<n; i++) {
            for(int j=s; j<=e; j++) {
                sum += board[i][j];
            }

            if(s == 0) {
                ds = +1;
                de = -1;
            }

            s+=ds;
            e+=de;
        }



        /*
            삼각형으로 푸는 방식
         */
        /*int L = n/2;
        // 위에 삼각형
        for(int i=0; i<=L; i++) {
            sum += board[i][L];
            for(int j=1; j<=i; j++) {
                sum += board[i][L - j] + board[i][L + j];
            }
        }

        // 밑에 삼각형
        for(int i=n-1; i>=L+1; i--) {
            sum += board[i][L];
            for(int j=1; j<=n-1-i; j++) {
                sum += board[i][L - j] + board[i][L + j];
            }
        }*/

        return sum;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 0 ;test_case <T; test_case++) {

            // n x n 농장 - 홀수
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];

            // 입력
            for(int i=0; i<n; i++) {
                String[] split = br.readLine().split("");
                for (int j = 0; j < n; j++) board[i][j] = Integer.parseInt(split[j]);
            }

            sum = 0;
            int answer = solution();

            sb.append("#").append(test_case + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);


    }
}