import java.util.*;
public class Main {

    static int[][] board;

    // 빙고인지 판단
    static int isBG() {
        int cnt = 0;

        // 가로 빙고
        for(int i=0; i<5; i++) {
            int cnt1 = 0;
            for(int j=0; j<5; j++) {
                if(board[i][j] == 0) cnt1++;
            }
            if(cnt1 == 5) cnt++;
        }

        // 세로 빙고
        for(int i=0; i<5; i++) {
            int cnt2 = 0;
            for(int j=0; j<5; j++) {
                if (board[j][i] == 0) cnt2++;
            }
            if(cnt2 == 5) cnt++;
        }

        // 대각선 빙고1
            int cnt3 = 0;
            for (int i = 0; i < 5; i++) {
                if (board[i][i] == 0) cnt3++;
            }
            if (cnt3 == 5) {
                cnt++;
            }

            // 대각선 빙고2
            int cnt4 = 0;
            for (int i = 0; i < 5; i++) {
                if (board[i][4 - i] == 0) cnt4++;
            }
            if (cnt4 == 5) {
                cnt++;
            }
        return cnt;
    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        board = new int[5][5];

        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int cnt = 0;
        int answer = 0;

        // 사회자가 부르는 수
        for(int a=0; a<25; a++) {
            int num = sc.nextInt();

            cnt++; // 부르는 수 카운트

            Loop1 :
            for(int j=0; j<5; j++) {
                for(int k=0; k<5; k++) {
                    if(board[j][k] == num) {
                        board[j][k] = 0;    // 0으로 체크
                        break Loop1;
                    }
                }
            }

            if(isBG() >= 3) {
                answer = cnt;
                break;
            }

        }

        System.out.println(answer);
    }
}