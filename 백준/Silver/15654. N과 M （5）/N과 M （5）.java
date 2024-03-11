import java.io.*;
import java.util.*;

/**
 * 15654. N과 M (5)
 * 1. N개의 자연수 중에서 M개를 고른 수열
 */
public class Main {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        sb = new StringBuilder();
        recur(0, new int[m], new boolean[n]);
        System.out.print(sb);
    }
    static StringBuilder sb;
    static void recur(int depth, int[] perm, boolean[] ch) {
        if(depth == m) {

            for(int idx : perm) {
                sb.append(arr[idx] + " ");
            }
            sb.append("\n");

            return;
        }

        for(int idx=0; idx<n; idx++) {
            if(ch[idx]) continue;
            ch[idx] = true;
            perm[depth] = idx;
            recur(depth+1, perm, ch);
            ch[idx] = false;
        }
    }
}