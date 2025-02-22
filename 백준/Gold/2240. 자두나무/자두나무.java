import java.io.*;
import java.util.*;


/**
 * 1. 매 초마다, 두 개의 나무 중 하나의 나무에서 열매가 떨어짐
 * 2. 그 순간 나무 아래 서있으면, 열매 받아먹음
 * 3. 나무 이동 시간 1초
 * 4. 처음, 1번 나무 위치
 *
 * => 짝수/홀수번 이동으로 사람 위치 구분
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //t초 동안 자두가 떨어질 때 w만큼 움직여서 열매 받아먹을 수 있는 최대 개수
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] arr = new int[t+1];
        for(int i = 1; i <= t; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // i초 동안 j번 움직였을 때 최대 개수
        int[][] dp = new int[t+1][w+1];
        dp[1][0] = (arr[1] == 1) ? 1 : 0;
        dp[1][1] = (arr[1] == 2) ? 1 : 0;

         for(int i = 2; i <= t; i++) {
            for(int j = 0; j < i; j++) {
                if(j > w) continue;

                //1. 이동 안할 경우
                int pos = (j % 2 == 0)? 1 : 2;
                int plus = (arr[i] == pos)? 1 : 0;
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + plus);

                //2. 이동 할 경우
                if(j+1 <= w) {
                    // 사람 위치
                    pos = ((j+1) % 2 == 0)? 1 : 2;
                    // 열매 먹는지
                    plus = (arr[i] == pos)? 1 : 0;
                    dp[i][j+1] = Math.max(dp[i][j+1], dp[i-1][j] + plus);
                }
            }
        }
        int answer = 0;
        for(int i = 0; i <= w; i++) {
            answer = Math.max(answer, dp[t][i]);
        }
        System.out.println(answer);

    }
}