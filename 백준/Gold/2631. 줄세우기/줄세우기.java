        import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for(int idx=0; idx<n; idx++) {
            arr[idx] = Integer.parseInt(br.readLine());
        }

        // n번을 마지막 값으로 했을 떄 최대 증가 수열
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1; i<n; i++) {
            int max = 1;
            for(int j=i-1; j>=0; j--) {
                if(arr[i] > arr[j]) {
                    max = Math.max(dp[j] + 1, max);
                }
            }
            dp[i] = max;
        }

        int answer = 0;
        for(int i=0; i<n; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(n - answer);
    }
}