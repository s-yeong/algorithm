import java.io.*;
import java.util.*;

class Main {

    static Integer[][] dp = new Integer[41][2];
    static Integer[] fibo(int n) {

        if(dp[n][0] == null || dp[n][1] == null) {
            dp[n][0] = fibo(n-2)[0] + fibo(n-1)[0];
            dp[n][1] = fibo(n-2)[1] + fibo(n-1)[1];
        }

        return dp[n];
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        dp[0][0] = 1; dp[0][1] = 0;
        dp[1][0] = 0; dp[1][1] = 1;
        for(int tc=0; tc<T; tc++) {

            // 0과 1이 각각 몇 번 출력되는지
            int n = Integer.parseInt(br.readLine());

            fibo(n);
            sb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
        }


        System.out.println(sb);
    }
}