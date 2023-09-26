import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1010. 다리 놓기
 * 1. 서쪽에 있는 다리 N과 동쪽에 있는 다리 M을 연결
 * 2. 다리를 최대한 많이 지으려고 하기 때문에 서쪽 다리 만큼 지으려고 함
 * 3. 다리는 겹칠 수 없음
 * => 다리를 지을 수 있는 경우의 수 구하기
 * 
 * 0. 최대 30, 겹치지 않고 다리 연결해야 함
 * => 상태 공간 트리로 표현했을 떄, 중복 부분 문제 구조를 가짐 => DP
 */
public class BOJ_1010_다리놓기 {

	// esatCount 중에서 westCount만큼 뽑기
	static int[][] dp = new int[30][30];
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int westCount, eastCount;
		while(T --> 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			westCount = Integer.parseInt(st.nextToken());
			eastCount = Integer.parseInt(st.nextToken());
			
			int count = combi(eastCount, westCount);
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}
	
	static int combi(int eastCount, int westCount) {
		
		// 0이 아닌 경우, 이미 해당 값을 구해둠
		if(dp[eastCount][westCount] != 0) return dp[eastCount][westCount];
		if(eastCount == westCount) return 1;
		if(westCount == 0) return 1;
		
		// 메모이제이션
		return dp[eastCount][westCount] = combi(eastCount-1, westCount-1) + combi(eastCount-1, westCount);
	}
}
 