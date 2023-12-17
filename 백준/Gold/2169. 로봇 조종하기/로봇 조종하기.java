import java.io.*;
import java.util.*;

/**
 * 2169. 로봇 조종하기
 * 1. 한 번 탐사한 지역 탐사 X
 * 2. 위로 이동 불가능
 * 3. 탐사 '가치'가 존재
 * => (1,1)에서 (N,M)으로 이동하면서 탐사한 가치의 합이 최대가 되도록 하기
 * [입력]
 * 1. 배열의 각 값은 '정수' (음수, 0, 양수)
 * 2. N,M 1000 이하
 * [풀이]
 * 0. 완전 탐색 시 O(3^N)
 * 1. 위로 이동 불가능하기 때문에 '해당 위치'는 아래에서 오는 경우 제외하고 최대값으로 계산하기
 * 2. 첫 row는 왼쪽에서 오는 경우만 고려하면 됨. 오른쪽에서 오는 경우 방문한 지점을 못가니까, N,M에 도달 못함
 * 3. 왼쪽에서 최대값을 계산해서 오거나, 오른쪽에서 최대값을 계산해서 와야 하기 때문에
 * 3-1.
 */
public class Main {
    static int rowLen, colLen;
    static int[][] board;
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

        int[][] dp = new int[rowLen][colLen];
        //(0,0) 출발
        dp[0][0] = board[0][0];
        for(int col=1; col<colLen; col++) {
            dp[0][col] = dp[0][col-1] + board[0][col];
        }

        int[][] temp = new int[2][colLen];

        for(int row=1; row<rowLen; row++) {

            // 왼쪽에서 오는 경우
            temp[0][0] = dp[row-1][0] + board[row][0];
            for (int targetCol = 1; targetCol < colLen; targetCol++) {
                temp[0][targetCol] = Math.max(dp[row-1][targetCol], temp[0][targetCol-1]) + board[row][targetCol];
            }

            // 오른쪽에서 오는 경우
            temp[1][colLen-1] = dp[row-1][colLen-1] + board[row][colLen-1];
            for (int targetCol = colLen-2; targetCol >= 0; targetCol--) {
                temp[1][targetCol] = Math.max(dp[row-1][targetCol], temp[1][targetCol+1]) + board[row][targetCol];
            }
            
            // 갱신
            for(int col=0; col<colLen; col++) {
                dp[row][col] = Math.max(temp[0][col], temp[1][col]);
            }
        }
        
        System.out.println(dp[rowLen-1][colLen-1]);
    }
}