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
        // 1부터 n까지 자연수 중에서 중복 없이 m개를 고른 수열
        permu = new int[m];
        ch = new boolean[n+1];
        recur(0);
        System.out.print(sb);
    }
    static void recur(int L) {

        // m개 골랐을 때
        if(L==m) {
            for(int x : permu) sb.append(x).append(" ");
            sb.append("\n");
        }
        else {
            for(int i=1; i<=n; i++) {
                if(!ch[i]) {
                    ch[i] = true;
                    permu[L] = i;
                    recur(L+1);
                    ch[i] = false;
                }
            }
        }
    }
}