import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1149. RGB거리
 * 1. 집을 RGB로 칠해야한다.
 * 2. 1번집의 색은 2번집과 같지 않아야 한다, n번 집의 색은 n-1번 집과 같지 않아야 한다.
 * 3. i번 집은, i-1번, i+1번 집과 색이 같지 않아야 한다.
 * 
 * 풀이
 * 1. 집의 개수 1000개
 * 2. 마지막집 까지 색칠됐을 때 최소값을 출력하는 문제다.
 * 3. N번쨰 집이 선택됐을 때 최소값은 결국 N번쨰 집의 색이 아닌 N-1번째 다른 두가지의 색의 집 중 최소값에 N번째 집의 색을 더하는 것이다.
 * 4. 중복 부분 문제 구조 + 최적화 원칙 -> DP
 *  
 */
public class BOJ_1149_RGB거리 {
	
	static int houseLen;
	static int[][] house;
	static final int RED=0, GREEN=1, BLUE=2;
	static int[][] dp;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		houseLen = Integer.parseInt(br.readLine());
		house = new int[houseLen][3];
		
		for(int idx=0; idx<houseLen; idx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			house[idx][RED] = Integer.parseInt(st.nextToken());
			house[idx][BLUE] = Integer.parseInt(st.nextToken());
			house[idx][GREEN] = Integer.parseInt(st.nextToken());
		}
		
		// N번째 집 `해당 색`까지 왔을 때 최소값
		dp = new int[houseLen][3];
		dp[0][RED] = house[0][RED];
		dp[0][BLUE] = house[0][BLUE];
		dp[0][GREEN] = house[0][GREEN];
		
		for(int idx=1; idx<houseLen; idx++) {
			dp[idx][RED] = Math.min(dp[idx-1][BLUE], dp[idx-1][GREEN]) + house[idx][RED];
			dp[idx][BLUE] = Math.min(dp[idx-1][RED], dp[idx-1][GREEN]) + house[idx][BLUE];
			dp[idx][GREEN] = Math.min(dp[idx-1][RED], dp[idx-1][BLUE]) + house[idx][GREEN];
		}
		
		int answer = Math.min(Math.min(dp[houseLen-1][RED], dp[houseLen-1][BLUE]), dp[houseLen-1][GREEN]);
		System.out.println(answer);
	}

}
