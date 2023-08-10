import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 5215. 햄버거 다이어트 - DP
 * 칼로리 제한을 넘지 않고 최대 점수를 구하기
 * => 냅색 알고리즘
 */
public class SWEA_5215_햄버거다이어트_DP {

	static int ingredientCount;
	static int calorieLimit;	// 칼로리 제한
	static int answer;	// 정답
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			
			ingredientCount =  Integer.parseInt(st.nextToken());
			calorieLimit =  Integer.parseInt(st.nextToken());
			
			// dp 배열
			int[] dp = new int[calorieLimit + 1];
			
			for(int idx=0; idx<ingredientCount; idx++) {
				
				st = new StringTokenizer(br.readLine().trim());
				
				// 맛에 대한 점수
				int score = Integer.parseInt(st.nextToken());
				// 칼로리
				int calorie = Integer.parseInt(st.nextToken());

				// dp[x] : x 칼로리가 주어졌을 때, 얻을 수 있는 최대 점수
				for(int x=calorieLimit; x>=calorie; x--) {
					dp[x] = Math.max(dp[x], dp[x - calorie] + score);
				}
			}
			
			// 주어진 제한 칼로리 이하의 조합중에서 가장 맛에 대한 점수가 높은 햄버거의 점수를 출력 
			answer = dp[calorieLimit];
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
}
