import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
    
        for(int i=0; i<n; i++) {
            String[] split = br.readLine().split("");
            for(int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        /**
         * 0과 1이 섞여 있으면 상,하,좌,우 4개의 영상으로 압축하여
         * 결과를 차례대로 괄호 안에 묶어서 표현함
         * 0과 1이 섞여 있다 -> 4개로 분할 -> 분할 정복문제
         * N은 언제나 2의 제곱수로 주어진다!
         */
        recursion(0, 0, n);
        System.out.print(sb);
    }

    static void recursion(int xs, int ys, int size) {

        if(isSame(xs,ys,size)) {
            sb.append(board[ys][xs]);
        }
        // 다른 경우, 4개로 분할
        else {
            sb.append("(");
            recursion(xs, ys, size/2);
            recursion(xs + size / 2, ys, size / 2);
            recursion(xs, ys + size / 2, size / 2);
            recursion(xs + size / 2, ys + size / 2, size / 2);
            sb.append(")");
        }
    }

    static boolean isSame(int xs, int ys, int size) {

        int tmp = board[ys][xs];
        for(int y=ys; y<ys+size; y++) {
            for(int x=xs; x<xs+size; x++) {
                if(board[y][x] != tmp) {
                    return false;
                }
            }
        }
        return true;
    }
}