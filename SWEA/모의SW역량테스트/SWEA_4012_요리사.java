import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4012. [모의 SW 역량테스트] 요리사
 * 1. A음식과 B음식의 맛의 차이가 최소가 되도록 재료 배분하기
 * 2. 14889. 스타트와 링크 문제와 조건 동일
 *
 * 문제 풀이
 * 1. nCn/2 조합
 * 2. 조합한 인덱스를 방문 체크하여 두 음식을 구분하여 차이 계산
 */
public class SWEA_4012_요리사 {
	
	static int n;
	// 시너지 배열
	static int[][] synergyArr;
	// 조합했을 때 체크하기 위한 배열
	static boolean[] ch;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int testCase=1; testCase<=T; testCase++) {
		
			// 입력 및 초기화
			n = Integer.parseInt(br.readLine());
			synergyArr = new int[n][n];
			answer = Integer.MAX_VALUE;
			ch = new boolean[n];
			
			for(int row=0; row<n; row++) {
				st= new StringTokenizer(br.readLine());
				for(int col=0; col<n; col++) {
					synergyArr[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			recur(0,0);
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	static void recur(int depth, int start) {
		
		// 조합 완료
		if(depth == n/2) {
			
			// 차이 계산
			int diff = calculateDiff();
			
			// 최소값 갱신
			answer = Math.min(answer, diff);
		}
		else {
			
			for(int idx=start; idx<n; idx++) {
				ch[idx] = true;
				recur(depth+1, idx+1);
				ch[idx] = false;
			}
		}
	}
	
	static int calculateDiff() {
		
		// 방문한 것은 A음식
		// 방문안한 것은 B음식
		int sumA = 0;
		int sumB = 0;
		
		for(int row=0; row<n/2; row++) {
			for(int col=0; col<n/2; col++) {
				
				if(ch[row] && ch[col]) {
					sumA += synergyArr[row][col];
				}
				if(!ch[row] && !ch[col]) {
					sumB += synergyArr[row][col];
				}
			}
		}
		
		return Math.abs(sumA - sumB);
	}
}
