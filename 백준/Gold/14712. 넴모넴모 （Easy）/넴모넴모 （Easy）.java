import java.io.*;
import java.util.*;

/**
 * 14712. 넴모넴모 (Easy)
 * 1. 격자판의 비어 있는 칸을 임의로 골라 “넴모”를 하나 올려놓거나
 * 2. “넴모”가 올라간 칸 네 개가 2 × 2 사각형을 이루는 부분을 찾아 그 위에 있는 “넴모”들을 모두 없애는 것
 * => “넴모”를 없애고 싶은데 격자판 위에 없앨 수 있는 “넴모”가 없으면 게임을 그만두기로 했다
 * ex) 2x2 격자판에서 모든 경우의 수는 2^4 = 16
 * - 네 칸 모두 "넴모"가 올라가 있는 경우의 수 제외 15가지
 *
 * [입력]
 * - N*M <= 25
 * - 따라서 2^(25)의 경우 3천만이므로 완전 탐색 가능
 */
public class Main {

    static int rowLen, colLen;
    static int answer;
    static boolean[][] board;
    static List<Pos> list;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        answer = 0;
        board = new boolean[rowLen][colLen];
        list = new ArrayList<>();
        for(int row=0; row<rowLen; row++) {
            for(int col=0; col<colLen; col++) {
                list.add(new Pos(row, col));
            }
        }
        recur(0, null);
        System.out.println(answer);
    }
    static void recur(int depth, Pos prePos) {

        // 넴모가 네 개 이상 올라가 있고, 이전에 넴모를 둔 경우
        if(depth >= 4 && board[prePos.row][prePos.col]) {
            if(canRemove(prePos)) {
                return;
            }
        }
        // 넴모를 2x2 구성하지 않은 경우
        if(depth == rowLen * colLen) {
            answer++;
            return;
        }

        Pos pos = list.get(depth);
        board[pos.row][pos.col] = true;
        recur(depth+1, pos);
        board[pos.row][pos.col] = false;
        recur(depth+1, pos);

    }
    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row=row;
            this.col=col;
        }
    }

    static boolean canRemove(Pos pos) {

        int row = pos.row;
        int col = pos.col;

        // 왼위 검증
        if(row-1 >=0 && col-1 >=0 && board[row-1][col] && board[row][col-1] && board[row-1][col-1]) {
            return true;
        }
        // 오위 검증
        if(row-1 >=0 && col+1 < colLen && board[row-1][col] && board[row][col+1] && board[row-1][col+1]) {
            return true;
        }
        // 왼아래 검증
        if(row+1 < rowLen && col-1 >=0 && board[row+1][col] && board[row][col-1] && board[row+1][col-1]) {
            return true;
        }
        // 오아래 검증
        if(row+1 < rowLen && col+1 < colLen && board[row+1][col] && board[row][col+1] && board[row+1][col+1]) {
            return true;
        }

        return false;
    }
}
