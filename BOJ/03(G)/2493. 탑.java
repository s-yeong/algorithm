
import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // N개의 높이가 서로 다른 탑

        /*
        1. 각 탑의 "꼭대기"에 레이저 송신기를 설치
        2. 레이저 송신기는 레이저 신호를 지표면과 평행하게 "수평 직선의 왼쪽 방향"으로 발사
        3. 탑의 "기둥 모두"에는 레이저 신호를 수신하는 장치가 설치
        4. 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능
         */

        /*
        1. N 최대 500,000 => O(N^2)으로 문제 풀 수X
        2. 자신의 왼쪽에있는 탑 중 큰 값이 레이저 받는다.
        3. 받지 않으면 0
         */

        int[] answer = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<int[]> stack = new Stack<>();

        for(int i=0; i<N; i++) {
            int x = Integer.parseInt(st.nextToken());

            boolean flag = true;
            if (!stack.isEmpty()) {

                // 0:위치, 1:값
                int[] next = {};
                // 수신할 탑 찾기
                while (true) {

                    if (!stack.isEmpty()) next = stack.peek();
                    // 더이상 찾을 스택이 없으면 어느 탑도 수신X
                    else {
                        flag = false;
                        break;
                    }

                    // 스택에 있는 값보다 현재 값이 더 크면, pop 후 찾기
                    if (x > next[1]) {
                        stack.pop();
                    }
                    // 탑 찾음
                    else break;
                }
                if(flag) answer[i] = next[0];
            }

            // 현재값 넣기
            stack.push(new int[]{i+1,x});
        }

        StringBuilder sb = new StringBuilder();
        for(int x : answer) sb.append(x).append(" ");
        System.out.println(sb);
    }
}