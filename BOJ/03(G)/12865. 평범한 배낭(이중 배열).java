import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물품의 수 n, 버틸 수 있는 무게 k
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] weight = new int[n+1];
        int[] value = new int[n+1];

        // 각 물건의 무게 w, 해당 물건의 가치 v
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            weight[i] = w;
            value[i] = v;
        }

        // k 무게 안에 가치의 최대값 구하기 => 냅색 알고리즘

        // i번째 물건까지 최대 j 무게까지 가능할 때 가치의 최대값
        int[][] dp = new int[n+1][k+1];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=k; j++) {
                // j 무게안이면,
                if(weight[i] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}