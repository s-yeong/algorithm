import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        /**
         * solved.ac 문제 난이도 출력
         * 0 < n < 3*x10^5
         *
         * 1. 난이도 계산 기준
         * 1-1. 아직 아무 의견이 없다면 문제의 난이도는 0으로 결정한다.
         * 1-2. 의견이 하나 이상 있다면, 문제의 난이도는 모든 사람의 난이도 의견의 30% 절사평균으로 결정한다.
         * 1-2.1. 절사평균 : 위에서 15% 아래에서 15% 제외
         * 1-2-2. 인원수가 소수점이면 `반올림`
         * 1-3. 마지막에 계산된 평균도 `반올림`
         *
         * 2. 계산방법
         * 2-1. 정렬하기
         * 2-2. n * 0.15 계산후 그만큼 lt, rt 초기화
         * 2-3. 평균 계산 후 반올림
         */

        Arrays.sort(arr);
        int lt = (int)Math.round(n*0.15);
        int rt = n-1 - lt;

        int sum = 0;
        for(int i=lt; i<=rt; i++) {
            sum += arr[i];
        }

        double len = rt-lt+1;

        int avg = (int)Math.round(sum/len);

        // 아무 의견 X
        if(n == 0) System.out.println(0);
        else {
            // 의견이 있는 경우
            System.out.println(avg);
        }

    }
}
