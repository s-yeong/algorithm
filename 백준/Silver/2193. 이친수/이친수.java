import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][2];

        //  i자리 마지막 자리가 j인 이친수의 개수
        dp[1][1] = 1;
        if(n >= 2) {
            dp[2][0] = 1;
        }

        for (int i = 3; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }


        System.out.println(dp[n][0] + dp[n][1]);
    }
}