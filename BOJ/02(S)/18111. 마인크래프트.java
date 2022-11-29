import java.util.*;
import java.io.*;

class Main {
    static int N, M, B;
    static int[][] board;
    static int max;
    static int min;
    static int answerT;
    static int answerH;

    static void sol(int cur) {
        // cur 높이로 땅 고르기

        int cnt = B;
        int time=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                int tmp = cur - board[i][j];

                // 같음 -> 0초

                // 양수 -> 블록 위에 놓기 => 1초
                if(tmp > 0) {
                    cnt -= tmp;
                    time += tmp;
                }
                // 음수 -> 블록 제거 => 2초
                else if(tmp < 0) {
                    cnt -= tmp;
                    time += 2*Math.abs(tmp);
                }
            }
        }

        // 값이 가능하면
        if(cnt >= 0) {
            if(answerT > time) {
                answerT = time;
                answerH = cur;
            }
        }
    }

    public static void main(String args[]) throws Exception {



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //땅 고르기 작업에 걸리는 최소 시간과 그 경우 땅의 높이를 출력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        max = 0;
        min = 256;
        answerT = Integer.MAX_VALUE;
        answerH = Integer.MIN_VALUE;

        for(int i=0; i<N; i++)  {
            st = new StringTokenizer(br.readLine());
               for(int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                max = Math.max(max, board[i][j]);
                min = Math.min(min, board[i][j]);
            }
        }

        for(int i=max; i>=min; i--) {
            sol(i);
        }

        System.out.println(answerT + " " + answerH);
    }
}
