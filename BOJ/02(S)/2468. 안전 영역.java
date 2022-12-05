import java.util.*;
import java.io.*;

class Main {

    static int[][] board, ch;
    static int N;
    static int answer = 0;
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static void DFS(int x, int y, int h) {

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<N && board[ny][nx] > h && ch[ny][nx] == 0) {
                ch[ny][nx] = 1;
                DFS(nx, ny, h);
            }
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        int maxH = 0;
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxH = Math.max(board[i][j], maxH);
            }
        }

        // 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수

        // 높이 0부터 최대 높이까지 계산
        // 아무 지역도 물에 잠기지 않을 수도 있다. => 높이 0
        for(int h=0; h<=maxH; h++) {
            int cnt = 0;
            ch = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(board[i][j] > h && ch[i][j] == 0) {
                        ch[i][j] = 1;
                        DFS(j, i, h);
                        cnt++;
                    }
                }
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
