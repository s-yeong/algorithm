import java.io.*;
import java.util.*;

public class Main {
    static int rowLen, colLen;
    static int[][] board;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int h, w, sr, sc, fr, fc;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new int[rowLen][colLen];
        for(int row=0; row<rowLen; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col=0; col<colLen; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());

        //직사각형의 크기 H, W, 시작 좌표 Sr, Sc, 도착 좌표 Fr, Fc
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        fr = Integer.parseInt(st.nextToken()) - 1;
        fc = Integer.parseInt(st.nextToken()) - 1;

        //직사각형의 가장 왼쪽 위칸은 (Sr, Sc)에 있을 때,
        // 이 직사각형의 가장 왼쪽 위칸을 (Fr, Fc)로 이동시키기 위한 최소 이동 횟수
        System.out.println(bfs());
    }
    static int bfs() {

        Queue<int[]> Q = new ArrayDeque<>();
        boolean[][] ch = new boolean[rowLen][colLen];
        // 현재 위치
        Q.offer(new int[] {sr, sc});
        ch[sr][sc] = true;

        int time = 0;
        while(!Q.isEmpty()) {

            int size = Q.size();

            while(size --> 0) {
                int[] cur = Q.poll();
                for(int dir=0; dir<4; dir++) {

                    int nextRow = cur[0] + dr[dir];
                    int nextCol = cur[1] + dc[dir];

                    // 검증
                    if(check(nextRow, nextCol) && !ch[nextRow][nextCol]) {
                        ch[nextRow][nextCol] = true;
                        if(nextRow==fr && nextCol == fc) {
                            return time+1;
                        }
                        Q.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
            time++;
        }
        return -1;
    }
    static boolean check(int nextRow, int nextCol) {

        // 격자판 벗어났는지
        if(nextRow < 0 || nextRow + h-1 >= rowLen || nextCol < 0 || nextCol + w-1 >= colLen) return false;

        // 모서리부분 벽 체크
        for(int col=nextCol; col<=nextCol+w-1; col++) {
            if(board[nextRow][col] == 1 || board[nextRow+h-1][col] == 1) return false;
        }
        for(int row=nextRow; row<=nextRow+h-1; row++) {
            if(board[row][nextCol] == 1 || board[row][nextCol+w-1] == 1) return false;
        }
        return true;
    }
}