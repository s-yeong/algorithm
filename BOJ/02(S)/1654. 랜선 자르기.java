import java.io.*;
import java.util.*;

class Main {
    static int N,K;
    static long[] arr;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
        1. N개보다 많이 만드는 것도 N개를 만드는 것에 포함
        2. 만들 수 있는 최대 랜선의 길이를 구하기
         */
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new long[K];
        for (int i = 0; i < K; i++) arr[i] = Long.parseLong(br.readLine());
        System.out.println(solution());
    }

    static long solution() {

        long answer = 0;
        Arrays.sort(arr);

        long lt = 1;
        long rt = arr[K-1];

        while(lt<=rt) {
            // 2147483647

            long mid=(lt+rt)/2;

            // 가능한지 검증
            long cnt = 0;
            for(long x : arr) {
                cnt += x/mid;
            }
            // N개 이상 랜선 만들면,
            if(cnt >= N) {
                answer = mid;
                // 최대값 찾기
                lt = mid+1;
            }

            else {
                rt = mid-1;
            }
        }

        return answer;
    }
}