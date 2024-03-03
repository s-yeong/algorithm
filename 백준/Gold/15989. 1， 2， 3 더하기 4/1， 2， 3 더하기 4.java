import java.io.*;
import java.util.*;

/**
 * 15989. 1,2,3 더하기 4
 * 1. 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 4가지가 있다.
 * 2. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
 * 3. 합을 이루고 있는 수의 순서만 다른 것은 같은 것으로 친다.
 *
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        // 정수, 수식이 1,2,3으로 끝남
        int[][] dp = new int[10001][4];
        dp[1][1] = 1; // 1
        dp[2][1] = 1; // 1+1
        dp[2][2] = 1; // 2
        dp[3][1] = 1; // 1+1+1
        dp[3][2] = 1; // 1+2
        dp[3][3] = 1; // 3
        dp[4][1] = dp[3][1]; // 1+1+1+1
        dp[4][2] = dp[2][2] + dp[2][1]; // 1+1+2, 2+2
        dp[4][3] = dp[1][1]; // 1+3
        dp[5][1] = dp[4][1]; // 1+1+1+1+1
        dp[5][2] = dp[3][2] + dp[3][1]; // 1+1+1+2, 1+2+2
        dp[5][3] = dp[2][1] + dp[2][2]; // 1+1+3 2+3
        dp[6][1] = dp[5][1]; // 1+1+1+1+1+1
        dp[6][2] = dp[4][2] + dp[4][1]; // 1+1+1+1+2, 1+1+2+2, 2+2+2
        dp[6][3] = dp[3][1] + dp[3][2] + dp[3][3]; // 1+1+1+3, 1+2+3, 3+3

        for(int num=7; num<=10000; num++) {
            dp[num][1] = dp[num-1][1];
            dp[num][2] = dp[num-2][2] + dp[num-2][1];
            dp[num][3] = dp[num-3][1] + dp[num-3][2] + dp[num-3][3];
        }

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n][1] + dp[n][2] + dp[n][3] + "\n");
        }
        System.out.println(sb);

    }
}