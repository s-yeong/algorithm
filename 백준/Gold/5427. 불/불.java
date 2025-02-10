import java.io.*;
import java.util.*;

public class Main {
    static int colLen, rowLen;
    static char[][] board;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    /**
     * 1. 불 동서남북 퍼짐
     * 2. 벽은 불에 안붙음
     * 3. 상근이는 동서남북 인접칸 이동 가능. 1초 소요. 벽 이동 불가. 불 있는 칸, 불 예정인 칸 이동 불가.
     * 4. 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸 이동 가능
     * => 출구는 벗어나면 됨
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T  = Integer.parseInt(br.readLine());
        StringBuilder sb =  new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            colLen = Integer.parseInt(st.nextToken());
            rowLen = Integer.parseInt(st.nextToken());
            board = new char[rowLen][colLen];
            int startRow = 0, startCol = 0;
            Queue<int[]> fireQ = new ArrayDeque<>(0);
            for(int row = 0; row< rowLen; row++) {
                board[row] = br.readLine().toCharArray();
                for(int col = 0; col< colLen; col++) {
                    if(board[row][col] == '@') {
                        startRow = row;
                        startCol = col;
                        board[row][col] = '.';
                    }
                    else if(board[row][col] == '*') {
                        fireQ.add(new int[]{row, col});
                    }
                }
            }
            int answer = bfs(startRow, startCol, fireQ);
            if(answer != -1) {
                sb.append(answer + "\n");
            } else {
                sb.append("IMPOSSIBLE\n");
            }
        }
        System.out.print(sb);
    }

    static int bfs(int startRow, int startCol, Queue<int[]> fireQ) {
        int answer = 0;
        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{startRow, startCol});

        while(!Q.isEmpty()) {

            //1. fire
            if(!fireQ.isEmpty()) {
                int fireLen = fireQ.size();
                while(fireLen-- > 0) {
                    int[] fire = fireQ.poll();

                    for(int dir=0; dir<4; dir++) {
                        int nextRow = fire[0] + dr[dir];
                        int nextCol = fire[1] + dc[dir];

                        if(nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen) {
                            continue;
                        }
                        if(board[nextRow][nextCol] == '.') {
                            board[nextRow][nextCol] = '*';
                            fireQ.add(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
            //2. 상근
            int len = Q.size();
            while(len-- > 0) {

                int[] cur = Q.poll();

                for(int dir=0; dir<4; dir++) {
                    int nextRow = cur[0] + dr[dir];
                    int nextCol = cur[1] + dc[dir];

                    if(nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen) {
                        return answer+1;
                    }
                    if(board[nextRow][nextCol] == '.') {
                        board[nextRow][nextCol] = '*';
                        Q.add(new int[]{nextRow, nextCol});
                    }
                }
            }
         
            answer++;
        }
        return -1;
    }


}