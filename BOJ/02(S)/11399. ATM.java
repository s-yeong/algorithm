import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int answer = 0;
        int tmp = 0;
        for(int i=0; i<N; i++) {
            answer = answer + arr[i] + tmp;
            tmp += arr[i];
        }
        System.out.println(answer);


    }
}