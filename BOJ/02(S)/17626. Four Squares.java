import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        /**
         * n을 최소 개수의 제곱수 합으로 표현
         * 자연수를 넷 혹은 그 이하의 `제곱수` 합으로 나타내라는 것
         *
         * 1=1^2, 2=1^2+1^2, 3=1^2+1^2+1^2, 4=2^2, 5=2^2+1^1, 6=2^2+1^2+1^2, 7=2^2 + 1^1 + 1^1 + 1^1
         * 8=2^2 + 2^2, 9=3^2
         */
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;


        for(int i=2; i<=n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        System.out.println(dp[n]);
    }
}
