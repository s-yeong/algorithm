import java.io.*;
import java.util.*;

class Main {


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 합이 M을 넘지않는 카드 3장
        int sum;
        int answer = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                for(int k=j+1; k<N; k++) {
                    sum = arr[i] + arr[j] + arr[k];
                    if(sum <= M) answer = Math.max(answer, sum);
                }
            }
        }


        System.out.println(answer);
    }
}
