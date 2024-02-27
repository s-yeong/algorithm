    import java.io.*;
    import java.util.*;

    /**
     * 17836. 공주님을 구해라!
     * 1. 벽을 피해 n,m 위치에 도달하기
     * 2. 용사 상하좌우 움직일 수 있음
     * 3. '그람'을 구하면 벽이 있는 칸이여도 부술 수 있음
     * => n,m 위치에 최단 거리에 도달하기
     *
     * [풀이]
     * 1. BFS를 통해 이동
     * 2. 이동 중 그람을 만나면 벽을 무시하고 가기
     * 3. 그람 여부를 가지고 있기
     */
    public class Main {

        static int limitTime, rowLen, colLen;
        static int[][] board;

        static int[] dr = {-1, 1,0,0};
        static int[] dc = {0,0,-1,1};

        public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            rowLen = Integer.parseInt(st.nextToken());
            colLen = Integer.parseInt(st.nextToken());
            limitTime = Integer.parseInt(st.nextToken());
            board = new int[rowLen][colLen];
            for(int row=0; row<rowLen; row++) {
                st = new StringTokenizer(br.readLine());
                for(int col=0; col<colLen; col++) {
                    board[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            int answer = bfs();
            if (answer == -1) {
                System.out.println("Fail");
            } else {
                System.out.println(answer);
            }
        }
        static int bfs() {

            boolean[][][] ch = new boolean[rowLen][colLen][2];
            int time = 0;
            int gramTime = 0;

            // 0,0 시작
            Queue<Pos> Q = new ArrayDeque<>();
            Q.offer(new Pos(0, 0, 0));
            ch[0][0][0] = true;
            while(!Q.isEmpty()) {

                int qLen = Q.size();

                while(qLen --> 0) {
                    Pos cur = Q.poll();

                    for (int dir = 0; dir < 4; dir++) {

                        int nextRow = cur.row + dr[dir];
                        int nextCol = cur.col + dc[dir];

                        if(nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen) continue;
                        if(ch[nextRow][nextCol][cur.hasGram]) continue;

                        // 도착하면,
                        if(nextRow == rowLen-1 && nextCol == colLen-1) {
                            return time+1;
                        }

                        if (cur.hasGram == 1) {
                            Q.offer(new Pos(nextRow, nextCol, cur.hasGram));
                            ch[nextRow][nextCol][cur.hasGram] = true;
                            continue;
                        }

                        // 벽이 아니면
                        if (board[nextRow][nextCol] != 1) {
                            // 그람이면,
                            if (board[nextRow][nextCol] == 2) {
                                Q.offer(new Pos(nextRow, nextCol, 1));
                                ch[nextRow][nextCol][1] = true;
                                ch[nextRow][nextCol][0] = true;
                            }
                            else {
                                Q.offer(new Pos(nextRow, nextCol, cur.hasGram));
                                ch[nextRow][nextCol][cur.hasGram] = true;
                            }
                        }
                    }
                } //qLen end
                time++;

                // 그람을 통한 최단 거리를 넘어서면,
                // 제한 시간 지나면,
                if(time == limitTime) {
                    return -1;
                }
            }
            return -1;
        }

        static class Pos {
            int row;
            int col;
            int hasGram;

            Pos (int row, int col, int hasGram) {
                this.row=row;
                this.col=col;
                this.hasGram=hasGram;
            }
        }
    }
