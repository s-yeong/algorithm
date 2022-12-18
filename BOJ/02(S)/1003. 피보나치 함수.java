import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayList<int[]> dp = new ArrayList<>();
        dp.add(0, new int[]{1, 0});
        dp.add(1, new int[]{0, 1});
        for(int tc=0; tc<T; tc++) {

            // 0과 1이 각각 몇 번 출력되는지
            int n = Integer.parseInt(br.readLine());

            int len = dp.size();
            for(int i=len; i<=n; i++) {

                int[] tmp1 = dp.get(i - 2);
                int[] tmp2 = dp.get(i - 1);
                dp.add(i,new int[]{tmp1[0]+tmp2[0], tmp1[1]+tmp2[1]});
            }

            // 0이 출력되는 횟수와 1이 출력되는 횟수
            int[] tmp = dp.get(n);
            sb.append(tmp[0]).append(" ").append(tmp[1]).append("\n");
        }


        System.out.println(sb);
    }
}