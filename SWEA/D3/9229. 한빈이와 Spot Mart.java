import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        /**
         * N개 중 두 봉지 고르는데, M그램 초과X
         * => 부분집합 (dfs)
         */

        for(int tc=1; tc<=T; tc++) {

            sb.append("#").append(tc).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            max = -1;
            dfs(0, 0, n, m, 0, arr);
            sb.append(max).append("\n");
        }
        System.out.print(sb);
    }

    static int max ;
    static void dfs(int L, int S, int n, int m, int sum, int[] arr) {

        if(L==2) {
            if(sum <= m) {
                max = Math.max(max, sum);
            }
            return;
        }

        for(int i=S; i<n; i++) {
            sum += arr[i];
            dfs(L + 1, i + 1, n, m, sum, arr);
            sum -= arr[i];
        }
    }
}