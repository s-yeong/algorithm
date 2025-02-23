import java.io.*;
import java.util.*;


public class Main {
    static int n,m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        perm = new int[m];
        sb = new StringBuilder();
        recur(0, 0);
        System.out.print(sb);
    }
    static int[] perm;
    static StringBuilder sb;
    static void recur(int depth, int preNum) {
        if(depth == m) {
            for(int x : perm) {
                sb.append(x + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<n; i++) {
            if(preNum > arr[i]) continue;
            perm[depth] = arr[i];
            recur(depth+1, perm[depth]);
        }
    }
}