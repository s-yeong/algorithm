import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][2];
        int[] dp = new int[n+2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()) + i - 1;
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=n; i>=1; i--) {
            if(arr[i][0] > n) dp[i] = dp[i+1];
            else dp[i] = Math.max(dp[i+1], dp[arr[i][0] + 1] + arr[i][1]);
        }
        System.out.println(dp[1]);
    }
}