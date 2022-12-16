import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        // N 종류 합 K 만들기
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        int answer = 0;
        for(int i=N-1; i>=0; i--) {
            if(arr[i] <= K) {
                int tmp = K / arr[i];
                K -= tmp * arr[i];
                answer += tmp;
            }
            if(K == 0) break;
        }
        System.out.println(answer);
    }
}