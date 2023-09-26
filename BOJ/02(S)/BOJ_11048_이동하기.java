import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11048. 이동하기
 * 1. (1,1)에서 (R,C)로 이동
 * 2. 하, 우, 우하 방향으로 이동 가능
 * 3. 그 때 놓여있는 사탕 모두 가져갈 수 있음
 * => 최대값 구하기
 * 
 * 풀이
 * 1. 중복 부분 문제 구조 + 최적화 원칙 -> DP
 * 2. 현재 지점으로 오는 것은 왼쪽에서, 대각선에서, 위에서 왔을 떄 그 중 최대값 선택
 */
public class BOJ_11048_이동하기 {
	
	static int rowLen, colLen;
	static int[][] board;
	
	// (1,1)에서 (R, C)로 왔을 때 최대값
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowLen = Integer.parseInt(st.nextToken());
		colLen = Integer.parseInt(st.nextToken());
		board = new int[rowLen+1][colLen+1];
		dp = new int[rowLen+1][colLen+1];
		
		for(int row=1; row<=rowLen; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=1; col<=colLen; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기값
		dp[1][1] = board[1][1];
		
		for(int row=1; row<=rowLen; row++) {
			for(int col=1; col<=colLen; col++) {
				// (1,1)에서 (row, col)로 왔을 때 최대값
				// 현재 지점으로 오는 것은 왼쪽에서, 대각선에서, 위에서 왔을 떄 중 최대값
				dp[row][col] = Math.max(Math.max(dp[row-1][col], dp[row][col-1]), dp[row-1][col-1]) + board[row][col]; 
			}
		}
		
		System.out.println(dp[rowLen][colLen]);
	}

}
