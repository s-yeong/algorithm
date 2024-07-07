import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] board;
    static int s,x,y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        PriorityQueue<Virus> pQ = new PriorityQueue<>();
        for(int row=0; row<n; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col=0; col<n; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
                if(board[row][col] != 0) {
                    pQ.add(new Virus(row, col, board[row][col], 0));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;
        //S초가 지난 후에 (X,Y)에 존재하는 바이러스의 종류
        System.out.println(bfs(pQ));
    }
    static int bfs(PriorityQueue<Virus> pQ) {

        while(!pQ.isEmpty()) {

            Virus cur = pQ.poll();
            if (cur.time == s) {
                break;
            }

            for(int dir=0; dir<4; dir++) {

                int nextRow = cur.row + dr[dir];
                int nextCol = cur.col + dc[dir];

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n) continue;
                if(board[nextRow][nextCol] != 0) continue;

                board[nextRow][nextCol] = cur.level;
                pQ.offer(new Virus(nextRow, nextCol, cur.level, cur.time + 1));
            }
        }
        return board[x][y];
    }
    static class Virus implements Comparable<Virus>{
        int row;
        int col;
        int level;
        int time;
        public Virus(int row, int col, int level, int time) {
            this.row=row;
            this.col=col;
            this.level=level;
            this.time=time;
        }
        public int compareTo(Virus ob) {
            if(this.time != ob.time) return this.time - ob.time;
            else return this.level - ob.level;
        }
    }
}