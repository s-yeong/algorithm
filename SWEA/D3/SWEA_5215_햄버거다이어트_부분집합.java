import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 5215. 햄버거 다이어트 - 부분집합
 * 칼로리 제한을 넘지 않고 최대 점수를 구하기
 * 1. 하나의 요소를 쓰나 안쓰냐로 구분
 * 2. 이 때 칼로리 넘어가면 반환
 */
public class SWEA_5215_햄버거다이어트_부분집합 {

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
			recur(0, 0, 0);
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);

	}
	
	static void recur(int depth, int scoreSum, int calorieSum) {
		
		// 현재 칼로리 합이 제한을 넘으면
		if(calorieSum > calorieLimit) return;
		
		if(depth == ingredientCount) {
			// 최대값 갱신
			answer = Math.max(answer, scoreSum);
		}
		else {
			// 재료를 넣냐
			recur(depth+1, scoreSum + scoreArr[depth], calorieSum + calorieArr[depth]);
			// 재료를 안넣냐
			recur(depth+1, scoreSum, calorieSum);
		}
	}

}
