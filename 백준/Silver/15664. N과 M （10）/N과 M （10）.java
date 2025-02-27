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
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        perm = new int[m];
        ch = new boolean[n];
        set = new LinkedHashSet<>();
        Arrays.sort(arr);
        recur(0, 0);

        StringBuilder answer = new StringBuilder();
        set.forEach(num -> {
            answer.append(num).append("\n");
        });
        System.out.print(answer);
    }
    static int[] perm;
    static boolean[] ch;
    static LinkedHashSet<String> set;
    static void recur(int depth, int pre) {
        if(depth == m){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<m; i++){
                sb.append(perm[i]).append(" ");
            }
            set.add(sb.toString());
            return;
        }

        for(int i=0; i<n; i++){
            if(ch[i] || pre > arr[i]) continue;
            perm[depth] = arr[i];
            ch[i] = true;
            recur(depth+1, perm[depth]);
            ch[i] = false;
        }
    }
}