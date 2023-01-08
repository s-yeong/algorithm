import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        m -= 45;
        if(m < 0) {
            m += 60;
            h--;
            if(h < 0) {
                h+=24;
            }
        }
        System.out.printf("%d %d", h, m);
    }
}