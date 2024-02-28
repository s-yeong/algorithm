import java.io.*;
import java.util.*;

/**
 * 18430. 무기 공학
 * 0. 나무 재료를 잘라서 부메랑 여러 개 만들기
 * 1. 부메랑 모양 4가지
 * 2. 부메랑의 중심이 되는 칸은 강도 2배
 * => 길동이가 만들 수 있는 부메랑들의 강도 합의 최대값 구하기
 * [풀이]
 * - 부메랑을 조합하고 최대값 구하기
 *
 */
public class Main {
    static int rowLen, colLen;
    static int[][] board;
    static boolean[][] ch;
    static int answer;
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
        ch = new boolean[rowLen][colLen];
        answer = 0;
        recur(0, 0);
        System.out.println(answer);
    }

    static void recur(int depth, int sum) {

        if(depth == rowLen*colLen) {
            answer = Math.max(answer, sum);
            return;
        }

        int row = depth / colLen;
        int col = depth % colLen;

        if(!ch[row][col]) {
            // ㄱ 모양
            if (row + 1 < rowLen && col - 1 >= 0 && !ch[row + 1][col] && !ch[row][col - 1]) {
                int total = board[row][col] * 2 + board[row + 1][col] + board[row][col - 1];
                ch[row][col] = true;
                ch[row + 1][col] = true;
                ch[row][col - 1] = true;
                recur(depth + 1, sum + total);
                ch[row][col] = false;
                ch[row + 1][col] = false;
                ch[row][col - 1] = false;
            }

            // ㄴ 반대 모양
            if (row - 1 >= 0 && col - 1 >= 0 && !ch[row - 1][col] && !ch[row][col - 1]) {
                int total = board[row][col] * 2 + board[row - 1][col] + board[row][col - 1];
                ch[row][col] = true;
                ch[row - 1][col] = true;
                ch[row][col - 1] = true;
                recur(depth + 1, sum + total);
                ch[row][col] = false;
                ch[row - 1][col] = false;
                ch[row][col - 1] = false;
            }

            // ㄴ 모양
            if (row - 1 >= 0 && col + 1 < colLen && !ch[row - 1][col] && !ch[row][col + 1]) {
                int total = board[row][col] * 2 + board[row - 1][col] + board[row][col + 1];
                ch[row][col] = true;
                ch[row - 1][col] = true;
                ch[row][col + 1] = true;
                recur(depth + 1, sum + total);
                ch[row][col] = false;
                ch[row - 1][col] = false;
                ch[row][col + 1] = false;
            }

            // ㄱ 반대 모양
            if (row + 1 < rowLen && col + 1 < colLen && !ch[row][col] && !ch[row + 1][col] && !ch[row][col + 1]) {
                int total = board[row][col] * 2 + board[row + 1][col] + board[row][col + 1];
                ch[row][col] = true;
                ch[row + 1][col] = true;
                ch[row][col + 1] = true;
                recur(depth + 1, sum + total);
                ch[row][col] = false;
                ch[row + 1][col] = false;
                ch[row][col + 1] = false;
            }
        }

        recur(depth+1, sum);
    }
}
