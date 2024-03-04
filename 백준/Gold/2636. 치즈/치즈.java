import java.io.*;
import java.util.*;

/**
 * 2636. 치즈
 * 1. 치즈가 놓인 부분에서 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
 * 2. 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게 된다.
 * => 바깥 가장자리 부분은 녹게된다.
 */
public class Main {
    static int rowLen, colLen;
    static int[][] board;
    static int totalCheeseCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new int[rowLen][colLen];
        totalCheeseCount = 0;
        for(int row=0; row<rowLen; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col=0; col<colLen; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
                if(board[row][col] == 1) totalCheeseCount++;
            }
        }
        int[] answer = bfs();
        System.out.println(answer[0] + "\n" + answer[1]);
    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[] bfs() {

        int time = 0;
        int cheeseCount = 0;
        Queue<int[]> Q = new ArrayDeque<>();

        while(true) {
            if(totalCheeseCount == 0) {
                return new int[]{time, cheeseCount};
            };

            // 초기화
            cheeseCount = 0;
            boolean[][] ch = new boolean[rowLen][colLen];
            Q.offer(new int[]{0, 0});
            ch[0][0] = true;

            while (!Q.isEmpty()) {

                int[] cur = Q.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nextRow = cur[0] + dr[dir];
                    int nextCol = cur[1] + dc[dir];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen || ch[nextRow][nextCol])
                        continue;

                    ch[nextRow][nextCol] = true;

                    // 치즈인 경우 (0 -> 1)
                    if (board[nextRow][nextCol] == 1) {
                        board[nextRow][nextCol] = 0;
                        cheeseCount++;
                        totalCheeseCount--;
                    }
                    else Q.offer(new int[]{nextRow, nextCol});
                }
            }   // while q end
            time++;
        }
    }
}
