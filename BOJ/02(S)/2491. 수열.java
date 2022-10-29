import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 1; // 수열 크기가 1인 경우 1 출력

        int cnt1 = 1;
        for(int i=1; i<n; i++) {
            if(arr[i-1] <= arr[i]) cnt1++;
            else cnt1 = 1;
            answer = Math.max(answer, cnt1);
        }

        int cnt2 = 1;
        for(int i=1;i<n; i++) {
            if(arr[i-1] >= arr[i]) cnt2++;
            else cnt2 = 1;
            answer = Math.max(answer, cnt2);
        }




        System.out.println(answer);
    }
}