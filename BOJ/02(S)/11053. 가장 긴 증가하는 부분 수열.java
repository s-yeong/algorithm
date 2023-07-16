import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 가장 긴 증가하는 부분 수열
         * => i번째 원소가 마지막 원소일 때 가장 긴 부분 수열
         */

        int[] dp = new int[n];
        dp[0] = 1;

        for(int i=1; i<n; i++) {

            for(int j=i-1; j>=0; j--) {
                if(arr[i] > arr[j] && dp[i] < dp[j]) {
                    dp[i] = dp[j];
                }
            }
            dp[i]++;
        }

        int answer = 0;
        for(int i=0; i<n; i++) answer = Math.max(answer, dp[i]);

        System.out.println(answer);
    }
}