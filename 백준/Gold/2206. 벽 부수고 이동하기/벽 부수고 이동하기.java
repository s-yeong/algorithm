import java.io.*;
import java.util.*;


/**
 * 2206. 벽 부수고 이동하기
 * 1. (0,0)에서 (N-1,M-1) 까지 갈 수 있는 최단 거리
 * 2. 벽은 `한 개`까지 부수고 이동 가능
 * [풀이]
 * 1. BFS
 * 2. 벽을 부섰는지 구분하는 필드 추가
 * 3. 방문했을 때, 벽을 부순 상태애서 방문할 수도 있고, 벽을 안 부순 상태에서 방문할 수도 있다.
 */
public class Main {

    static int[][] board;
    static int rowLen, colLen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new int[rowLen][colLen];
        for(int row=0; row<rowLen; row++) {
            String[] input = br.readLine().split("");
            for(int col=0; col<colLen; col++) {
                board[row][col] = Integer.parseInt(input[col]);
            }
        }
        System.out.println(bfs());
    }
    //상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int bfs() {

        Queue<Pos> Q = new ArrayDeque<>();
        boolean[][][] ch = new boolean[rowLen][colLen][2];
        // 처음 위치 넣기
        Q.offer(new Pos(0,0,0, 1));
        ch[0][0][0] = true;

        while(!Q.isEmpty()) {

            Pos cur = Q.poll();

            // 도달했을 때,
            if(cur.row == rowLen-1 && cur.col == colLen-1) {
                return cur.dis;
            }

            for(int dir=0; dir<4; dir++) {

                int nextRow = cur.row + dr[dir];
                int nextCol = cur.col + dc[dir];

                if(nextRow >=0 && nextCol>=0 && nextRow<rowLen && nextCol<colLen) {
                    // 벽인 경우, 부술 수 있으면,
                    if(board[nextRow][nextCol] == 1 && cur.isBreak == 0 && !ch[nextRow][nextCol][cur.isBreak]) {
                        Q.offer(new Pos(nextRow, nextCol, 1, cur.dis+1));
                        ch[nextRow][nextCol][1] = true;
                        continue;
                    }
                    //  벽이 아니면,
                    if(board[nextRow][nextCol] == 0 && !ch[nextRow][nextCol][cur.isBreak]) {
                        Q.offer(new Pos(nextRow, nextCol, cur.isBreak, cur.dis+1));
                        ch[nextRow][nextCol][cur.isBreak] = true;
                    }
                }
            }
        }
        return -1;
    }
    static class Pos {
        int row;
        int col;
        int isBreak;
        int dis;
        public Pos(int row, int col, int isBreak, int dis) {
            this.row=row;
            this.col=col;
            this.isBreak=isBreak;
            this.dis=dis;
        }
    }

}