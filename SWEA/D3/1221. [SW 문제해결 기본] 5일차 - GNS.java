import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        String[] num = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};

        for(int tc=1; tc<=T; tc++) {
            sb.append("#").append(tc).append("\n");
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int test_len = Integer.parseInt(st.nextToken());

            // 개수 담기
            HashMap<String, Integer> map = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<test_len; i++) {
                String str = st.nextToken();
                map.put(str, map.getOrDefault(str, 0) + 1);
            }

            // 출력 0->9 순서대로
            for(int i=0; i<10; i++) {
                if(map.get(num[i]) != null) {
                    for(int j=0; j<map.get(num[i]); j++) {
                        sb.append(num[i]).append(" ");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}