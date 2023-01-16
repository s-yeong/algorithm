import java.io.*;
import java.util.*;

class Main {
    static int N, L;
    static int[][] board;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 길이 지나가려면
        // 1. 모든 칸의 높이가 같다
        // 2. 경사로를 놓아서 지나가는 길을 만든다.

        // 경사로 조건
        // 1. 낮은칸에 놓으며 L개의 연속된 칸에 "경사로 바닥"이 모두 접해야 한다.
        // 2. 낮은칸 높은칸 차이는 1이어야 한다.
        // 3. 경사로를 놓을 L개의 연속된 낮은 칸의 높이는 모두 같아야 한다.
        // 4. 경사로 놓은 곳에 또 경사로 못놓는다.


        // 2N개의 경우의 수 탐색 -> 가로, 세로
        int answer = 0;

        // 경사로는 무조건 "낮은"곳에! -> 경사로 놓은 곳 체크하기
        // 올라가는 경사, 내려가는 경사 구분


        // 가로인 경우
        for(int i=0; i<N; i++) {

            boolean flag = true;

            int tmp = board[i][0];

            // 경사로 체크
            boolean[] ch = new boolean[N];

            loop:
            for(int j=0; j<N; j++) {

                // 다른 지점 발생
                if(tmp != board[i][j]) {

                    // 높이 차이가 2이상 나면 X
                    if(Math.abs(tmp-board[i][j]) >= 2) {
                        flag = false;
                        break;
                    }

                    // 높은 경우 - 올라가는 경사
                    if(tmp < board[i][j]) {

                        // 경사로 놓을 수 있는지 확인
                        for (int k = j - 1; k > j - 1 - L; k--) {
                            // 경사로 놓을 칸이 L개가 안되는 경우
                            // (=다음 값으로 넘어간 경우, 이미 경사로 둔 경우, 범위 벗어난 경우)
                            if (k < 0 || ch[k] || board[i][j-1] != board[i][k]) {
                                flag = false;
                                break loop;
                            }
                            // 그 자리 경사로 체크
                            ch[k] = true;
                        }
                        // 현재값
                        tmp = board[i][j];
                    }

                    // 낮은 경우 - 내려가는 경사
                    else {
                        // 경사로 놓을 수 있는지 확인
                        for (int k = j; k < j + L; k++) {
                            // 경사로 놓을 칸이 L개가 안되는 경우
                            // (=다음 값으로 넘어간 경우, 이미 경사로 둔 경우, 범위 벗어난 경우)
                            if(k>=N || ch[k] || board[i][j] != board[i][k]) {
                                flag = false;
                                break loop;
                            }
                            // 그 자리 경사로 체크
                            ch[k] = true;
                        }
                        // 현재값
                        tmp = board[i][j];
                    }
                }

                // 같은 높이
                // continue
            }
            // 지나갈 수 있는 경우
            if(flag) {
                answer++;
            }
        }


        // 세로인 경우
        for(int i=0; i<N; i++) {

            boolean flag = true;

            int tmp = board[0][i];

            // 경사로 체크
            boolean[] ch = new boolean[N];

            loop:
            for(int j=0; j<N; j++) {

                // 다른 지점 발생
                if(tmp != board[j][i]) {

                    // 높이 차이가 2이상 나면 X
                    if(Math.abs(tmp-board[j][i]) >= 2) {
                        flag = false;
                        break;
                    }

                    // 높은 경우 - 올라가는 경사
                    if(tmp < board[j][i]) {

                        // 경사로 놓을 수 있는지 확인
                        for (int k = j - 1; k > j - 1 - L; k--) {
                            // 경사로 놓을 칸이 L개가 안되는 경우
                            // (=다음 값으로 넘어간 경우, 이미 경사로 둔 경우, 범위 벗어난 경우)
                            if (k < 0 || ch[k] || board[j-1][i] != board[k][i]) {
                                flag = false;
                                break loop;
                            }
                            // 그 자리 경사로 체크
                            ch[k] = true;
                        }
                        // 현재값
                        tmp = board[j][i];
                    }

                    // 낮은 경우 - 내려가는 경사
                    else {
                        // 경사로 놓을 수 있는지 확인
                        for (int k = j; k < j + L; k++) {
                            // 경사로 놓을 칸이 L개가 안되는 경우
                            // (=다음 값으로 넘어간 경우, 이미 경사로 둔 경우, 범위 벗어난 경우)
                            if(k>=N || ch[k] || board[j][i] != board[k][i]) {
                                flag = false;
                                break loop;
                            }
                            // 그 자리 경사로 체크
                            ch[k] = true;
                        }
                        // 현재값
                        tmp = board[j][i];
                    }
                }

                // 같은 높이
                // continue
            }
            // 지나갈 수 있는 경우
            if(flag) {
                answer++;
            }
        }


        System.out.println(answer);
    }
}