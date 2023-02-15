import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] permu;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        /*
        1. 1부터 n까지 자연수 중에서 m개를 고른 수열
        2. 같은 수를 여러 번 골라도 가능 -> 중복 가능
        3. 고른 수열은 비내림차순
         */

        permu = new int[m];
        recur(0,-1);
        System.out.print(sb);

    }
    static void recur(int L, int prev) {

        if(L==m) {
            for(int x : permu) sb.append(x).append(" ");
            sb.append("\n");
        }
        else {

            for(int i=1; i<=n; i++) {
                // 이전값(prev) 보다 크거나 같은 경우(=비내림차순)
                if(i >= prev) {
                    permu[L] = i;
                    recur(L+1, i);
                }
            }
        }

    }
}