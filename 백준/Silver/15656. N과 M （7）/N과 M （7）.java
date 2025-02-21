import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        perm = new int[m];
        sb = new StringBuilder();
        recur(0, n, m, arr);
        System.out.print(sb.toString());

    }
    static StringBuilder sb;
    static int[] perm;
    static void recur(int depth, int n, int m, int[] arr) {

        if(depth == m) {
            for(int num : perm) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            perm[depth] = arr[i];
            recur(depth + 1, n, m, arr);
        }
    }
}