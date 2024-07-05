import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        perm = new int[n];
        ch = new boolean[n+1];
        recur(0, n);
    }
    static int[] perm;
    static boolean[] ch;
    static void recur(int depth, int n) {
        if(depth == n) {
            for(int num : perm) System.out.print(num + " ");
            System.out.println();
            return;
        }

        for(int idx=1; idx<=n; idx++) {
            if(ch[idx]) continue;
            ch[idx] = true;
            perm[depth] = idx;
            recur(depth+1, n);
            ch[idx] = false;
        }

    }
}