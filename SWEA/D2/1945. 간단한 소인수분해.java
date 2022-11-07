import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        int[] arrN = {2, 3, 5, 7, 11};

        for(int i=0; i<T; i++) {
            int num = Integer.parseInt(br.readLine());

            sb.append("#").append(i+1);
            for(int j=0; j<5; j++) {
                int cnt = 0;
                while (num % arrN[j] == 0) {
                    cnt++;
                    num = num / arrN[j];
                }
                sb.append(" ").append(cnt);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}