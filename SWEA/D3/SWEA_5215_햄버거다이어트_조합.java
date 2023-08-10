import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 5215. 햄버거 다이어트 - 조합
 * 
 * 칼로리 제한을 넘지 않고 최대 점수를 구하기
 * 1. 재료의 요소를 기준으로 1개 조합, 2개 조합, 3개 조합... 해서 최대값 구하기
 *  
 */
public class SWEA_5215_햄버거다이어트_조합 {

	static int ingredientCount;
	static int calorieLimit;	// 칼로리 제한
	static int[] calorieArr;	// 칼로리 배열
	static int[] scoreArr;	// 맛에 대한 점수 배열
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
			
			calorieArr = new int[ingredientCount];
			scoreArr = new int[ingredientCount];
			
			for(int idx=0; idx<ingredientCount; idx++) {
				
				st = new StringTokenizer(br.readLine().trim());
				// 맛에 대한 점수
				scoreArr[idx] = Integer.parseInt(st.nextToken());
				// 칼로리
				calorieArr[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 주어진 제한 칼로리 이하의 조합중에서 가장 맛에 대한 점수가 높은 햄버거의 점수를 출력
			answer = 0;
			// 1개부터 요소 개수 만큼 조합하면서 최대값 갱신
			for(int combiCount=1; combiCount<=ingredientCount; combiCount++) {
				recur(0,0,combiCount,0,0);
			}
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);

	}
	
	static void recur(int depth, int start, int combiCount, int scoreSum, int calorieSum) {
		
		// 현재 칼로리 합이 제한을 넘으면
		if(calorieSum > calorieLimit) return;
		
		// 조합 완료
		if(depth == combiCount) {
			// 최대값 갱신
			answer = Math.max(answer, scoreSum);
		}
		else {
			for(int idx=start; idx<ingredientCount; idx++) {
				recur(depth+1, idx+1, combiCount, scoreSum + scoreArr[idx], calorieSum + calorieArr[idx]);
			}
		}
	}
}
