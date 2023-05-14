import java.io.*;
import java.util.*;
public class Main {

    static int[][] board;
    static int answer_white = 0;
    static int answer_blue = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(0,0, n);
        System.out.println(answer_white);
        System.out.println(answer_blue);
    }

    static boolean isSame(int xs, int ys, int xe, int ye) {

        int k = board[ys][xs];
        for(int y=ys; y<=ye; y++) {
            for (int x = xs; x <= xe; x++) {
                if(board[y][x] != k) {
                    return false;
                }
            }
        }
        return true;
    }

    static void recursion(int x, int y, int len) {

        // 모두 같으면, 개수 더하기
        if(isSame(x,y, x+len-1, y+len-1)) {
            if(board[y][x] == 0) answer_white++;
            else answer_blue++;
        }
        else {
            // 4개로 쪼개기
            recursion(x, y, len/2);
            recursion(x+len/2, y, len/2);
            recursion(x, y+len/2, len/2);
            recursion(x+len/2, y+len/2, len/2);
        }

    }
}