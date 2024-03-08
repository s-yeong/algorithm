import java.io.*;

/**
 * 4396. 지뢰 찾기
 * 1. x:열린 칸, .:온점, *:지뢰
 * 2. 지뢰가 없는 지점을 건드리면 상하좌우 혹은 대각선으로 인접한 8개의 칸에 지뢰가 몇 개있는지 알려주는 숫자가 나타남
 * 3. 지뢰가 있는 칸이 열리면, 지뢰가 있는 모든 칸이 * 표시
 */
public class Main {
    static int n;
    static char[][] mine;
    static char[][] board;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        mine = new char[n][n];
        board = new char[n][n];
        for(int row=0; row<n; row++) {
            mine[row] = br.readLine().toCharArray();
        }
        for(int row=0; row<n; row++) {
            board[row] = br.readLine().toCharArray();
        }

        boolean isMine = false;
        for(int row=0; row<n; row++) {
            for(int col=0; col<n; col++) {
                // 열린 칸이면,
                if(board[row][col] == 'x') {
                    // 지뢰 개수 탐색
                    int count = solve(row, col);
                    board[row][col] = String.valueOf(count).charAt(0);
                    // 그 지점이 지뢰면,
                    if(mine[row][col] == '*') {
                        isMine = true;
                    }
                }
            }
        }

        // 지뢰 밟았으면,
        if(isMine) {
            for(int row=0; row<n; row++) {
                for(int col=0; col<n; col++) {
                    if(mine[row][col] == '*') {
                        board[row][col] = mine[row][col];
                    }
                }
            }
        }

        // 답
        for(int row=0; row<n; row++) {
            for(int col=0; col<n; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
    // 상하좌우좌상좌하우상우하
    static int[] dr = {-1,1,0,0,-1,1,-1,1};
    static int[] dc = {0,0,-1,1,-1,-1,1,1};
    static int solve(int row, int col) {

        int count = 0;

        for(int dir=0; dir<8; dir++) {
            int nextRow = row + dr[dir];
            int nextCol = col + dc[dir];

            if(nextRow < 0 || nextCol <0 || nextRow >= n || nextCol >= n) continue;

            // 지뢰이면,
            if(mine[nextRow][nextCol] == '*') count++;
        }
        return count;
    }
}
