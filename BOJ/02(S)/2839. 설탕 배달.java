import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // N킬로를 담을 봉지의 최소 개수

        // 4998/3 = 1666, 5000/5 = 1000
        for(int i=0; i<=1666; i++) {
            for(int j=0; j<=1000; j++) {
                if(j*5 + i*3 == N) {
                    System.out.println(i+j);
                    System.exit(0);
                }
            }
        }

        System.out.println(-1);

    }

}
