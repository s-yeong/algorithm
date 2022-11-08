import java.util.*;
import java.io.*;

class Main {

    static int[][] board;

    static boolean gridCh() {

        // 3x3 => 9개 비교
        int[] dx = {0, 3, 6, 0, 3, 6, 0, 3, 6};
        int[] dy = {0, 0, 0, 3, 3, 3, 6, 6, 6};

        for(int k=0; k<9; k++) {
            int[] ch = new int[10];
            int x=dx[k];
            int y=dy[k];
            for(int i=y; i<y+3; i++) {
                for(int j=x; j<x+3; j++) {
                    int val = board[i][j];
                    if(ch[val] == 0) ch[val] = 1;
                    else return false;
                }
            }
        }

        return true;
    }

    static boolean xyCh() {

        for(int i=0; i<9; i++) {

            // 행체크
            int[] ch = new int[10];
            for(int j=0; j<9; j++) {
                int val = board[i][j];
                if(ch[val] == 0) ch[val] = 1;
                else return false;
            }

            // 열체크
            ch = new int[10];
            for(int j=0; j<9; j++) {
                int val = board[j][i];
                if(ch[val] == 0) ch[val] = 1;
                else return false;
            }

        }

        return true;
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for(int test_case=0; test_case<T; test_case++) {

            board =new int[9][9];

            for(int i=0; i<9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<9; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("#").append(test_case+1).append(" ");
            // 가로, 세로 검증, 3x3 검증
            if(xyCh() && gridCh()) sb.append(1);
            else sb.append(0);

            sb.append("\n");

        }

        System.out.println(sb);


    }
}