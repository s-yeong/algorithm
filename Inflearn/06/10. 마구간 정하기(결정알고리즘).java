import java.io.*;
import java.util.*;

public class Main {
    public static int count(int[] arr, int dis) {

        int cnt = 1;
        int ep = arr[0];    // end point - 제일 왼쪽 좌표에 한마리 배치

        for(int i=1;i<arr.length; i++) {
            if(arr[i] - ep >= dis) {    // 가장 가까운 말의 거리가 dis니까, dis보다 크거나 같으면 된다.
                cnt++;
                ep = arr[i];
            }
        }

        return cnt;
    }
    public static int solution(int n, int c, int[] arr) {

        int answer = 0;
        Arrays.sort(arr);
        int lt = 1;
        int rt = arr[n-1] - arr[0];

        while(lt <= rt) {
            int mid = (lt + rt) / 2;

            if(count(arr, mid) >= c) {
                answer = mid;
                lt = mid + 1;
            } else rt = mid - 1;
        }


        return answer;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 마구간 수
        int c = Integer.parseInt(st.nextToken());   // 말 수

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(solution(n,c,arr));


    }
}