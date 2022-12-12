import java.io.*;
import java.util.*;

class Main {


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc=0; tc<T; tc++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[k + 1][n + 1];

            // 0층, i호에 i명
            for (int i = 1; i <= n; i++) dp[0][i] = i;

            // 1층부터
            for(int i=1; i<=k; i++) {
                for(int j=1; j<=n; j++) {
                    for(int p=1; p<=j; p++) {
                        dp[i][j] += dp[i-1][p];
                    }
                }
            }

            // 자신의 아래(a-1)층의 1호부터 b호까지 사람들의 수의 합만큼
            sb.append(dp[k][n]).append("\n");

        }

        System.out.println(sb);
    }
}
