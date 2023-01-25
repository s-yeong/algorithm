
import java.io.*;
import java.util.*;

class Main {
    static int N,M;
    static char[][] board;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        /*
        1. 체스판을 색칠하는 경우 두 가지 -> 맨 왼쪽 위 칸이 흰색/검은색
        2. 8×8 크기의 체스판으로 잘라낸 후에 몇 개의 정사각형을 다시 칠하기
        3. 다시 칠해야 하는 정사각형의 최소 개수 구하기
         */

        // 경우의 수 : 2*(N-7)*(M-7)
        // 시작 점 => 맨 왼쪽 위
        int answer = Integer.MAX_VALUE;
        for (int color = 0; color < 2; color++) {
            for (int y = 0; y < N - 7; y++) {
                for (int x = 0; x < M - 7; x++) {
                    answer = Math.min(answer, solution(color,x,y));
                }
            }
        }
        System.out.println(answer);
    }

    // 상 하 좌 우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int solution(int color, int x, int y) {

        // 배열 복사
        char[][] tmp = new char[N][M];
        for (int i = 0; i < N; i++) tmp[i] = board[i].clone();

        int count = 0;  // 바꾼 횟수

        // color:0 = B, color:1 = W
        if(color == 0) {
            if(tmp[y][x] == 'W') {
                tmp[y][x] = 'B';
                count++;
            }
        }

        else {
            if(tmp[y][x] == 'B') {
                tmp[y][x] = 'W';
                count++;
            }
        }

        boolean[][] ch = new boolean[N][M];
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{x, y});
        ch[y][x] = true;

        while (!Q.isEmpty()) {

            int[] cur = Q.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx>=x && ny>=y && nx<x+8 && ny<y+8 && !ch[ny][nx]) {

                    // 색이 같으면 색칠
                    if(tmp[cur[1]][cur[0]] == tmp[ny][nx]) {
                            count++;
                        tmp[ny][nx] =  (tmp[cur[1]][cur[0]] == 'W')? 'B' : 'W';
                    }
                    // 다르면 OK

                    // 방문처리후 큐에 넣기
                    ch[ny][nx] = true;
                    Q.offer(new int[]{nx, ny});
                }
            }
        }
        return count;
    }

}