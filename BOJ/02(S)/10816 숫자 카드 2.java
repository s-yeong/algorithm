
import java.io.*;
import java.util.*;

class Main {
    static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num,0) + 1);
        }

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        while(M --> 0) {

            int num = Integer.parseInt(st.nextToken());
            sb.append(map.getOrDefault(num,0)).append(" ");
        }
        System.out.print(sb);
    }
}