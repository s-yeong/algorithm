import java.io.*;
import java.util.*;

class Main {
    static int N,M;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new int[M];
        // 1부터 N까지 자연수 중에서 M개 중복 순열
        recur(0);
        System.out.print(sb);
    }

    static void recur(int L) {
    // 시간 복잡도 = N^M

        if(L == M) {
            for(int x : ans) {
                sb.append(x).append(" ");
            }
            sb.append("\n");
        }
        else {
            for(int i=1; i<=N; i++) {
                ans[L] = i;
                recur(L + 1);
            }
        }
    }
}