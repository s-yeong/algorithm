import java.io.*;
import java.util.*;

class Main {


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        // N과 N을 이루는 각 자리수의 합
        for(int i=1; i<N; i++) {
            int sum = i;
            int tmp = i;
            while(tmp > 0) {
                sum += tmp % 10;
                tmp = tmp / 10;
            }
            if(sum == N) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);

    }
}
