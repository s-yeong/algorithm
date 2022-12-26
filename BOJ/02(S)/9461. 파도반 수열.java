import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[101];
        StringBuilder sb = new StringBuilder();
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for(int tc=0; tc<T; tc++) {
            // 나선에서 가장 긴 변의 길이를 k
            int num = Integer.parseInt(br.readLine());
            if(dp[num] == 0) {
                for (int i = 6; i <= num; i++) {
                    dp[i] = dp[i - 1] + dp[i - 5];
                }
            }
            sb.append(dp[num]).append("\n");
        }
        System.out.println(sb);
    }
}