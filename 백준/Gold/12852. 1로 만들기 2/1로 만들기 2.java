import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // i가 주어졌을 때 연산을 사용하는 횟수의 최소값
        int[] dp = new int[n+1];

        // 경로 복원 테이블
        int[] pre = new int[n+1];

        for(int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            if(i % 3 == 0) {
                if (min > dp[i / 3]) {
                    min = dp[i / 3];
                    minIndex = i / 3;
                }
            }
            if(i % 2 == 0) {
                if (min > dp[i / 2]) {
                    min = dp[i / 2];
                    minIndex = i / 2;
                }
            }
            if (min > dp[i - 1]) {
                min = dp[i - 1];
                minIndex = i - 1;
            }

            dp[i] = min + 1;
            pre[i] = minIndex;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n]).append("\n");

        while (n >= 1) {
            sb.append(n).append(" ");
            n = pre[n];
        }
        System.out.println(sb);

    }
}