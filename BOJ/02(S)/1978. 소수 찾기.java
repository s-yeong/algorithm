import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int x = Integer.parseInt(st.nextToken());
            boolean flag = true;

            if(x == 1) flag = false;
            for (int j = 2; j < x; j++) {
                if (x % j == 0) {
                    flag = false;
                    break;
                }
            }

            if(flag) cnt++;
        }
        System.out.println(cnt);

    }

}
