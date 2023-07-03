import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int answer = -1;
            int count = x;

            int cx = x; // 고정 - m만큼 이동할 시 cx 계속 나옴
            int cy = x % n;
            if(cy == 0) cy = n;
            int init_y = cy;

            // y가 m만큼 이동
            while(true) {

                // 정답이면,
                if(cy == y) {
                    answer = count;
                    break;
                }

                cy = (cy + m) % n;
                if(cy == 0) cy = n;
                count += m;

                // 종료 시점
                if(init_y == cy) break;
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);

        /*
         *  1-1. M과 N보다 작거나 같은 두 개의 자연수 x, y를  <x:y>와 같은 형식으로 표현
         *  1-2. <1:1>, <2:2>.. x < M이면, x'=x+1, y < N이면, y'=y+1
         *  1-3. <M:N> 달력의 마지막 해
         *  1-4. 유효하지 않으면 -1
         *
         *  2. 증가시키는 방식으로 하면 시간초과 예상 => '규칙' 발견해야함
         *  3. x를 맞춰서 고정시키고 y를 따라가게 한다.
         *  4. %로 계산할 시 0으로 값 나오는거 생각해야함
         */





    }
}