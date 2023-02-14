import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물품의 수 n, 버틸 수 있는 무게 k
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] weight = new int[n];
        int[] value = new int[n];

        // 각 물건의 무게 w, 해당 물건의 가치 v
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            weight[i] = w;
            value[i] = v;
        }

        // k 무게 안에 가치의 최대값 구하기 => 냅색 알고리즘

        // j무게 안에서 가치의 최대값
        int[] dp = new int[k+1];
        for(int i=0; i<n; i++) {
            for(int j=k; j>=weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-weight[i]] + value[i]);
            }
        }
        System.out.println(dp[k]);
    }
}