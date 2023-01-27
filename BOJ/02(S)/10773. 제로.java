import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int sum = 0;
        int[] arr = new int[K];
        int p = 0;  // 현재 수를 둘 지점

        for(int i=0; i<K; i++) {
            int num = Integer.parseInt(br.readLine());
            // 0이면, 가장 최근 수 지우기
            if (num == 0) {
                sum -= arr[p-1];
                p--;
            }
            else {
                arr[p++] = num;
                sum += num;
            }
        }

        System.out.println(sum);


    }
}