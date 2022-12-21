import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 게임에서 얻을 수 있는 총 점수의 최댓값

        // dp[n-1] => dp[n-2], dp[n-3]
        // dp[n-2]에서 도착 => dp[n-3]을 밟지 못함
        // dp[n-3]에서 도착 => dp[n-4] 밟을 수 있음

        // i번쨰 계단에서 얻을 수 있는 최대값
        int[] dp = new int[n];

        dp[0] = arr[0];
        if(n>1) dp[1] = arr[0] + arr[1];
        if(n>2) dp[2] = Math.max(arr[0] + arr[2], arr[1] + arr[2]);

        if(n>3) {
            for (int i = 3; i < n; i++) {
                dp[i] = Math.max(arr[i] + arr[i - 1] + dp[i - 3], arr[i] + dp[i - 2]);
            }
        }

        System.out.println(dp[n-1]);
    }
}