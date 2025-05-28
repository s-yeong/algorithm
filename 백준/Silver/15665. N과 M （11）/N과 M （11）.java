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
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        //중복순열
        set = new HashSet<>();
        list = new ArrayList<>();
        recur(0, new int[m], n, m, arr);
        StringBuilder sb = new StringBuilder();
        for(String str : list) {
            sb.append(str);
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static Set<String> set;
    static List<String> list;
    static void recur(int depth, int[] perm, int n, int m, int[] arr) {
        if(depth == m) {
            StringBuilder sb = new StringBuilder();
            for(int i : perm) {
                sb.append(i + " ");
            }
            if(set.contains(sb.toString())) {
                return;
            }

            set.add(sb.toString());
            list.add(sb.toString());
            return;
        }

        for(int i=0; i<n; i++) {
            perm[depth] = arr[i];
            recur(depth + 1, perm, n, m, arr);
        }
    }
}
