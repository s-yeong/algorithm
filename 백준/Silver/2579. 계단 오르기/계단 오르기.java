import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        //지금까지 몇 개의 계단을 밟았는지에 대한 정보가 추가적으로 있어야 함
        //dp[i][j]: 현재까지 j개의 계단을 연속해서 밟고 i번째 계단까지 올라섰을 때 점수 합의 최대 함, 단 i번쨰 계단은 반드시 밟아야 함
        //=> j=1 혹은 2
        int[][] dp = new int[n+1][3];
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if(n <= 2) {
            int sum = 0;
            for(int i = 1; i <= n; i++) {
                sum += arr[i];
            }
            System.out.println(sum);
            System.exit(0);
        }

        dp[1][1] = arr[1];
        dp[2][1] = arr[2];
        dp[2][2] = dp[1][1] + arr[2];
        for(int i=3; i<=n; i++){
            dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + arr[i];
            dp[i][2] = dp[i-1][1] + arr[i];
        }
        System.out.println(Math.max(dp[n][1], dp[n][2]));
    }
}