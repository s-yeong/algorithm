import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i=1; i<=T; i++) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            long sum = 0;

            int max = Integer.MIN_VALUE;

            // ex) 1 4 3 9 2 3 5    -> 9원에 파는게 최대 이익,   그 다 음 인덱스 부터는 5원에 파는게 최대 이익
            for(int j=n-1; j>=0; j--) {
                if(max < arr[j]) max = arr[j];
                else sum += max - arr[j];
            }

            sb.append("#").append(i).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);


    }
}