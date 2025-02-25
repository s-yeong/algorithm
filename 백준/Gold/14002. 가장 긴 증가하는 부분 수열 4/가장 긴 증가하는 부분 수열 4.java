import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // i를 마지막 항목으로 선택했을 때 가장 긴 수열
        int[] dp = new int[n];
        dp[0] = 1;

        // i의 이전 인덱스
        int[] pre = new int[n];

        for(int i = 1; i < n; i++){
            int target = arr[i];
            for(int j = 0; j < i; j++){
                if(target > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j];
                    pre[i] = j;
                }
            }
            dp[i] += 1;
        }
        int maxIndex = 0;
        for(int i=1; i < n; i++){
            if(dp[maxIndex] < dp[i]){
                maxIndex = i;
            }
        }
        recur(maxIndex, pre, dp, arr);
        System.out.println(dp[maxIndex]);
        System.out.print(sb);
    }
    static StringBuilder sb = new StringBuilder();
    static void recur(int index, int[] pre, int[] dp, int[] arr) {
        if(dp[index] == 1){
            sb.append(arr[index]).append(" ");
            return;
        }

        recur(pre[index], pre, dp, arr);
        sb.append(arr[index]).append(" ");
    }
}