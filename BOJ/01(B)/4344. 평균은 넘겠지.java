import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());

        while(c-->0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // 평균이 넘는 학생들의 비율을 반올림
            int sum = 0;
            int[] arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] += Integer.parseInt(st.nextToken());
                sum += arr[i];
            }

            double avg = (double)sum / n;
            double count = 0;
            for(int x : arr) {
                if(x > avg) count++;
            }

            double answer = (count/n)*100;
            System.out.printf("%.3f%%\n", answer);
        }
    }

}