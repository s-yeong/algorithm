import java.util.*;
import java.io.*;

class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++) {
            String str = br.readLine();
            String[] split = str.split("X");
            int score = 0;
            for(String x : split) {
                int len = x.length();
                for(int j=len; j>=1; j--) {
                    score += j;
                }
            }
            sb.append(score).append("\n");
        }
        System.out.println(sb);
    }
}
