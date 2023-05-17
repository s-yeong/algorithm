import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int answer;
    static int answer_bg;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        /**
         * 적록색약이 아닌 사람이 봤을 때의 구역의 수
         * 적록색약인 사람이 봤을 때 구역의 수 구하기
         * 구역의 수 => DFS, BFS 문제
         * 1. 적록색약-> R,G 동일하게 봄 -> G를 모두 R로 바꾸기
         * 2. 적록색약X -> 동일하게X
         *
         * => 방문한 경우 'X'로 처리
         */

        char[][] board = new char[n][n];
        char[][] board_rg = new char[n][n];

        for(int i=0; i<n; i++) {
            board[i] = br.readLine().toCharArray();
            board_rg[i] = board[i].clone();
            for(int j=0; j<n; j++) {
                // board_rg의 G를 모두 R로 바꾸기
                if(board_rg[i][j] == 'G') board_rg[i][j] = 'R';
            }
        }

        for(int i=0; i<n; i++) {

            for(int j=0; j<n; j++) {

                if(board[i][j] != 'X') {

                    dfs(j, i, board[i][j], board);
                    answer++;
                }
                if(board_rg[i][j] != 'X') {

                    dfs(j, i, board_rg[i][j], board_rg);
                    answer_bg++;
                }
            }
        }
        System.out.println(answer + " " + answer_bg);
    }

    // 상 하 좌 우
    static int[] dx ={0,0,-1,1};
    static int[] dy ={-1,1,0,0};

    static void dfs(int x, int y, char color, char[][] board) {

        board[y][x] = 'X';

        for(int i=0; i<4; i++) {

            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx>=0 && ny>=0 && nx<n && ny<n && board[ny][nx] == color) {

                dfs(nx, ny, color, board);
            }
        }
    }


}