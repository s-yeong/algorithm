import java.io.*;
import java.util.*;

class Main {
    static int N,M,H;
    static int[][] board;
    static boolean flag = false;

    static boolean sol(){
        for(int i=1; i<=N; i++) {
            int tmp = i;
            int idx = 1;

            while (idx <= H) {

                // 왼쪽 사다리 있는지 확인
                if (tmp - 1 >= 1 && board[idx][tmp - 1] == 1) {
                    tmp--;
                }
                // 오른쪽 사다리 있는지 확인
                else if(tmp <= N && board[idx][tmp] == 1) {
                    tmp++;
                }
                idx++;
            }
            if(i != tmp) return false;
        }
        return true;
    }


    static void DFS(int S, int cnt) {

        if(flag) return;
        if(cnt == 0) {
            if(sol()) flag = true;
        }
        else {
            // 사다리 놓기
            for(int i=S; i<=H; i++) {
                for(int j=1; j<=N-1; j++) {
                    if(board[i][j] == 0) {
                        board[i][j] = 1;
                        DFS(i,cnt - 1);
                        board[i][j] = 0;
                    }
                }
            }
        }
    }


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H + 1][N + 1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // b번과 b+1번 세로선을 a번 점선 위치에 연결
            board[a][b] = 1;
        }

        // 두 가로선이 연속하거나 서로 접하면 안된다
        // i번 세로선의 결과가 i번이 나와야함
        // 그렇게 하기 위해서 추가해야 하는 가로선 개수의 최소값

        int answer = -1;
        for(int i=0; i<=3; i++) {
            DFS(1,i);
            if(flag) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

}
