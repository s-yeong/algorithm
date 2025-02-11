import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        //n개의 자연수 중에서 m개를 고른 수열
        //고른 수열은 오름차순

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        ch = new boolean[n];
        perm = new int[m];
        sb = new StringBuilder();
        recur(0, arr, n, m, 0);
        System.out.print(sb);
    }
    static boolean[] ch;
    static int[] perm;
    static StringBuilder sb;
    static void recur(int depth, int[] arr, int n, int m, int before) {

        if(depth == m) {
            for(int x : perm) {
                sb.append(x + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<n; i++) {
            if(ch[i]) continue;
            if(before > arr[i]) continue;
            ch[i] = true;
            perm[depth] = arr[i];
            recur(depth + 1, arr, n, m, perm[depth]);
            ch[i] = false;
        }
    }
}