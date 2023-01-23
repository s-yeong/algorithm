
import java.io.*;
import java.util.*;

class Main {
    static int R,C,N;
    static char[][] board;
    // 상 하 좌 우
    static int[] dx ={0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        // 폭탄 리스트
        Queue<int[]> bombList = new LinkedList<>();

        for(int i=0; i<R; i++) {
            board[i]= br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if(board[i][j] == 'O') bombList.add(new int[]{j,i});
            }
        }

        /*
        1. 처음에 봄버맨은 일부 칸에 폭탄을 설치
        ----
        2. 1초 동안 봄버맨은 아무것도 하지 않는다.
        3. 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치
        4. 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발
         */
        // 큐에서 꺼냈을 때 폭발물이면 터트리기

        // R개의 줄에 N초가 지난 후의 격자판 상태를 출력
        for(int i=1; i<=N; i++) {

            // 2. 1초 동안 봄버맨은 아무것도 하지 않는다.
            if(i==1) continue;

            // 3. 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치 => "짝수"초
            if(i%2==0) {
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        if (board[j][k] == '.') {
                            board[j][k] = 'O';
                        }
                    }
                }
            }

            // 4. 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발 => "홀수"초
            if(i%2==1) {
                while(!bombList.isEmpty()) {

                    int[] cur = bombList.poll();

                    // 폭발!
                    board[cur[1]][cur[0]] = '.';
                    for(int j=0; j<4; j++) {
                        int nx = cur[0] + dx[j];
                        int ny = cur[1] + dy[j];
                        if(nx>=0 && ny>=0 && nx<C && ny<R) board[ny][nx] = '.';
                    }
                }

                // (3.)에서 설치한 폭탄 넣기
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        if(board[j][k] == 'O') {
                            bombList.add(new int[]{k, j});
                        }
                    }
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}