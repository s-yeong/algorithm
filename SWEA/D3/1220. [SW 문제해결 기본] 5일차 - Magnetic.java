import java.io.*;
import java.util.*;

public class Main {

    static int[][] board;
    public static void main(String[] args) throws IOException {

        /**
         * N=1, S=2
         * 테이블의 윗부분에 N극, 아랫부분에 S극 위치
         * 자기장을 걸고, 자성체들이 충돌한 후 테이블 위에 남아있는 교착 상태의 개수 구하기
         * N극이 S극으로 갈 때 사이에 S극이 있으면, 교착 상태 발생
         *
         * N극 -> S극 확인하자
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        StringBuilder sb = new StringBuilder();
        int length = 100;

        for(int tc=1; tc<=T; tc++) {
            sb.append("#").append(tc).append(" ");
            // 정사각형 테이블의 한 변의 길이 = 항상 100
            br.readLine();
            
            // 100 x 100 크기의 테이블의 초기 모습
            board = new int[length][length];
            for(int i=0; i<length; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<length; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;

            for(int j=0; j<length; j++) {
                int tmp = 0;

                for(int i=0; i<length; i++) {

                    // N(1)에서 S(2)으로 바뀔 때 Count
                    if(board[i][j] == 1) {
                        tmp = 1;
                    }

                    else if(board[i][j] == 2 && tmp == 1) {
                        count++;
                        tmp = 2;
                    }
                }
            }


            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }
}