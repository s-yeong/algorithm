import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] permu;
    static boolean[] ch;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        /*
        1. 1부터 n까지 자연수 중에서 m개를 고른 수열
        2. 고른 수열은 오름차순
        ex) 2 1 -> 내림차순이기 때문에 X
        => 조합 구하기
         */

        permu = new int[m];
        ch = new boolean[n + 1];
        recur(0,1);
        System.out.print(sb);

    }
    static void recur(int L, int S) {

        if(L==m) {
            for(int x : permu) sb.append(x).append(" ");
            sb.append("\n");
        }
        else {

            for(int i=S; i<=n; i++) {
                permu[L] = i;
                recur(L+1, i+1);
            }
        }

    }
}