import java.io.*;
import java.util.*;

/**
 * 1. 빈 칸 또는 벽
 * 2. 왼쪽 아래칸에서 -> 오른쪽 윗칸 이동
 * 3. 1초마다 모든 벽이 아래에 있는 행으로 한 칸씩 내려감
 * 4. 이동 또는 설 수 있음
 * 5. 캐릭터 이동 -> 벽 이동
 * 6. 벽이 캐릭터가 있는 칸으로 이동하면 더이상 캐릭터 이동 불가능
 */
public class Main {
    static char[][] board = new char[8][8];
    // 상하좌우 좌상우상좌하우하 제자리
    static int[] dr = {-1,1,0,0,-1,-1,1,1,0};
    static int[] dc = {0,0,-1,1,-1,1,-1,1,0};
    static Queue<int[]> wallQ;
    static char WALL = '#';
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        wallQ = new ArrayDeque<>();
        for(int row=0; row<8; row++) {
            board[row] = br.readLine().toCharArray();
        }
        // 큐에 넣기
        for(int row=7; row>=0; row--) {
            for(int col=0; col<8; col++) {
                if(board[row][col] == WALL) {
                    wallQ.offer(new int[]{row, col});
                }
            }
        }

        System.out.println(bfs());
    }
    static int bfs() {

        Queue<int[]> Q = new ArrayDeque<>();
        boolean[][] ch;

        //start
        Q.offer(new int[]{7,0});
        // end
        int endRow = 0;
        int endCol = 7;

        while(!Q.isEmpty()) {

            int size = Q.size();

            ch = new boolean[8][8];
            while(size --> 0) {

                int[] cur = Q.poll();
//                System.out.println(cur[0] + " " + cur[1]);

                // 현재 위치로 벽이 이동했으면, 이동 불가
                if(board[cur[0]][cur[1]] == WALL) continue;
                
                for (int dir = 0; dir < 9; dir++) {

                    int nextRow = cur[0] + dr[dir];
                    int nextCol = cur[1] + dc[dir];
//                    System.out.println("next : " + nextRow + " " + nextCol);
                    if (nextRow < 0 || nextCol < 0 || nextRow > 7 || nextCol > 7)
                        continue;
                    if (ch[nextRow][nextCol] || board[nextRow][nextCol] == WALL)
                        continue;
//                    System.out.println(">>next : " + nextRow + " " + nextCol);

                    // 목적지 도착
                    if (nextRow == endRow && nextCol == endCol) {
                        return 1;
                    }
                    // 가만히 있는 경우, 방문 처리X
                    if(dir!=8) ch[nextRow][nextCol] = true;
                    Q.offer(new int[]{nextRow, nextCol});
                }
            }

            // 벽 이동
            if(!wallQ.isEmpty()) {
                int wsize = wallQ.size();
                while(wsize --> 0) {

                    // 한 칸 씩 이동
                    int[] wcur = wallQ.poll();
                    board[wcur[0]][wcur[1]] = '.';

                    int nextRow = wcur[0] + 1;
                    int nextCol = wcur[1];

//                    System.out.println("wall" + nextRow + " " + nextCol);

                    // 벗어난거 아니면,
                    if(nextRow != 8) {
                        wallQ.offer(new int[]{nextRow, nextCol});
                        board[nextRow][nextCol] = WALL;
                    }
                }
            }



        }

        return 0;
    }
}