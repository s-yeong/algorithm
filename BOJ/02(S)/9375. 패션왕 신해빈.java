import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        /**
         * 해당 type이 3개 => 경우의수 4가지(3가지+안쓰는 경우)
         */

        while(T-->0) {
            HashMap<String, Integer> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                String type = st.nextToken();
                map.put(type, map.getOrDefault(type, 0) + 1);
            }

            int mul = 1;
            for(String key : map.keySet()) {
                mul *= map.get(key)+1;
            }
            sb.append(mul-1).append("\n");
        }
        System.out.print(sb);
    }
}
