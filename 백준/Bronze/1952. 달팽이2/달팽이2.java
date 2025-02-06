import java.io.*;
import java.util.*;

public class Main {

    static int m,n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        count = 0;
        ch = new boolean[m][n];
        solve(0, 0, 0);
        System.out.println(count);
    }
    static int count;
    static boolean[][] ch;

    //우하좌상
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static void solve(int row, int col, int dir) {

        ch[row][col] = true;
        int nextRow = row + dr[dir];
        int nextCol = col + dc[dir];
        if(nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || ch[nextRow][nextCol]) {
            dir = (dir + 1) % 4;
            nextRow = row + dr[dir];
            nextCol = col + dc[dir];
            if(ch[nextRow][nextCol]) {
                return;
            }
            count++;
        }
        solve(nextRow, nextCol, dir);
    }
}