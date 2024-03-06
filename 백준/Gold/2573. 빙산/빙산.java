import java.io.*;
import java.util.*;


/**
 * 2573. 빙산
 * 1. 한 덩어리의 빙산이 주어질 때,
 * 2. 동서남북 방향으로 붙어있는 칸이 있으면 서로 연결되어있다.
 * 3. 이 빙산이 두 덩어리 이상으로 분리되는 최소의 시간
 * 4. 전부 다 녹을 때 까지 분리되지 않음면 0 출력
 *
 * [풀이]
 * 1. bfs + dfs
 * 2. 한 번의 time에서 빙산이 녹은 다음 덩어리 계산
 */
public class Main {
    static int rowLen, colLen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        int[][] board = new int[rowLen][colLen];
        Queue<int[]> Q = new ArrayDeque<>();
        for(int row=0; row<rowLen; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col=0; col<colLen; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
                if(board[row][col] > 0) Q.offer(new int[]{row,col});
            }
        }
        int answer = bfs(Q, board);
        System.out.println(answer);
    }
    static int[] dr =  {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int bfs(Queue<int[]> Q, int[][] board) {

        int time = 0;

        while(!Q.isEmpty()) {

            int[][] tempBoard = new int[rowLen][colLen];
            copyBoard(board, tempBoard);

            int len = Q.size();
            for(int count=0; count<len; count++) {

                int[] cur = Q.poll();
                int row = cur[0];
                int col = cur[1];

                for(int dir=0; dir<4; dir++) {

                    int nextRow = row + dr[dir];
                    int nextCol = col + dc[dir];

                    if(nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen) continue;

                    // 네 방향 중 0이면,
                    if(board[nextRow][nextCol] == 0) {
                        // 빙산 줄어든다.
                        tempBoard[row][col]--;
                    }
                }

                // 현재 위치가 0보다 커서, 빙산이면
                if(tempBoard[row][col] > 0) {
                    // 다시 넣기
                    Q.offer(new int[]{row, col});
                }
            }

            // board 업데이트
            copyBoard(tempBoard, board);

            // 2개 이상 분리되었는지 확인
            int count = 0;
            ch = new boolean[rowLen][colLen];
            for(int row=0; row<rowLen; row++) {
                for(int col=0; col<colLen; col++) {
                    if(board[row][col] > 0 && !ch[row][col]) {
                        count++;
                        dfs(row, col, board);
                    }
                }
            }
            time++;
            if(count >= 2) return time;
        }
        return 0;
    }
    static boolean[][] ch;
    static void dfs(int row, int col, int[][] board) {
        for(int dir=0; dir<4; dir++) {
            int nextRow = row + dr[dir];
            int nextCol = col + dc[dir];
            if(nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen || ch[nextRow][nextCol]) continue;
            if(board[nextRow][nextCol] == 0) continue;
            ch[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, board);
        }
    }
    static void copyBoard(int[][] from, int[][] to) {
        for(int row=0; row<rowLen; row++) {
            for(int col=0; col<colLen; col++) {
                to[row][col] = from[row][col];
                if(to[row][col] < 0) to[row][col] = 0;
            }
        }
    }
}
