import java.io.*;
import java.util.*;


/**
 * 4179. 불!
 * 1. 불 : 네 방향 확산
 * 2. 벽 : 불과 지훈이 모두 통과 X
 * 3. '미로의 가장자리'에 접한 공간에서 탈출함
 * => 지훈이 최소 시간으로 미로 탈출하기
 *
 * [풀이]
 * - BFS로 지훈이가 갈 수 잇는 지점을 가면서
 * - 지훈이의 위치에 불이 오면 그 지점으로 못가고,
 * - 가장자리에 도달했을 시 => +1 해서 답 구하기
 * - 불과 지훈이의 큐 분리
 * 
 * [입력]
 * -  R, C가 1000 이하
 * [출력]
 * - 가장 빠른 탈출시간 출력
 * - 불가능한 경우 "IMPOSSIBLE" 출력
 */
public class Main {
    static int rowLen, colLen;
    static char[][] board;
    static int[] start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new char[rowLen][colLen];
        start = new int[2];

        Queue<int[]> fireQ = new ArrayDeque<>();

        for(int row=0; row<rowLen; row++) {
            board[row] = br.readLine().toCharArray();
            for(int col=0; col<colLen; col++) {
                if(board[row][col] == 'J') {
                    start[0] = row;
                    start[1] = col;
                }
                else if(board[row][col] == 'F') {
                    fireQ.offer(new int[] {row, col});
                }
            }
        }

        int min = bfs(fireQ);
        System.out.println((min == -1)? "IMPOSSIBLE" : min);
    }

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static int bfs(Queue<int[]> fireQ) {

        Queue<int[]> Q = new ArrayDeque<>();
        Q.offer(start);
        int time = 0;
        
        // 지훈이 큐
        while(!Q.isEmpty()) {

            int len = Q.size();

            while(len --> 0) {

                int[] cur = Q.poll();

                // 지훈이의 위치에 불이 오면 그 지점에서는 못감
                if (board[cur[0]][cur[1]] == 'F')
                    continue;

                // 가장자리인 경우, 답
                if (cur[0] == 0 || cur[1] == 0 || cur[0] == rowLen - 1 || cur[1] == colLen - 1) {
                    return time + 1;
                }

                for (int dir = 0; dir < 4; dir++) {

                    int nextRow = cur[0] + dr[dir];
                    int nextCol = cur[1] + dc[dir];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen)
                        continue;

                    if (board[nextRow][nextCol] == '.') {
                        board[nextRow][nextCol] = 'J';
                        Q.offer(new int[]{nextRow, nextCol});
                    }
                }
            } // Q end
            
            // 불 큐
            if(!fireQ.isEmpty()) {

                len = fireQ.size();

                while(len --> 0) {

                    int[] cur = fireQ.poll();
                    for(int dir=0; dir<4; dir++) {

                        int nextRow = cur[0] + dr[dir];
                        int nextCol = cur[1] + dc[dir];

                        if (nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen)
                            continue;
                        if (board[nextRow][nextCol] == '.' || board[nextRow][nextCol] == 'J') {
                            board[nextRow][nextCol] = 'F';
                            fireQ.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }   // fireQ end

            time++;
        }
        return -1;
    }


}