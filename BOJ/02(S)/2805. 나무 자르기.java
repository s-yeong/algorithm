import java.io.*;
import java.util.*;

class Main {
    static int N,M;
    static int[] arr;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 나무의 높이의 합 >= M
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        /*
       적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
       1. 높이는 1부터 배열의 최대값까지 가능함
       (배열의 최소값이 아닌 1부터 가능함!!)
       2. 범위가 정해져 있다. -> 결정 알고리즘
         */

        Arrays.sort(arr);
        System.out.println(solution());
    }

    static int solution() {

        int lt = 1;
        int rt = arr[N-1];
        int answer = 0;

        while(lt<=rt) {

            int mid = (lt+rt)/2;

            // 가능한지 검증
            long sum = 0;
            for(int x : arr) {
                if(x<mid) continue;
                sum += x - mid;
            }

            // 가능하면 정답으로
            if(sum >= M) {
                answer = mid;
                // 최대값 찾기
                lt = mid+1;
            }
            // 안되면, 줄이기
            else {
                rt = mid-1;
            }
        }
        return answer;
    }
}