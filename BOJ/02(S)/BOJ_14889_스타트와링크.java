import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14889. 스타트와 링크
 * 1. N/2명씩 이루어진 스타트팀과 링크팀
 * 2. Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다. 
 * 3. 팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합
 * 4. Sij는 Sji와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.
 * 5. 모든 쌍을 구해야 한다. 
 * => 스타트 팀의 능력치와 링크 팀의 능력치를 최소화 
 * 
 * 문제 풀이
 * 1. N명중에 절반 선택하는 경우의 수
 * 2. N이 20이므로, 20C10의 경우를 구해서 둘 차이의 최소가 되는 값을 구하기
 * 3. 조합한 인덱스를 방문 체크하여 두 팀을 구분하여 차이 계산
 */
public class BOJ_14889_스타트와링크 {
	
	static int n;
	// 팀 능력치 배열
	static int[][] teamStatArr;
	// 조합했을 때 체크하기 위한 배열
	static boolean[] ch; 
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		// 입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		teamStatArr = new int[n][n];
		ch = new boolean[n];
		
		for(int row=0; row<n; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col=0; col<n; col++) {
				teamStatArr[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		recur(0,0);
		System.out.println(answer);
	}
	
	// 조합 재귀
	static void recur(int depth, int start) {
		
		// 팀사이즈 만큼 조합을 구했으면,
		if(depth == n/2) {
			
			// 차이 구하기
			int diff = calculateDiff();
			
			// 차이 구한 다음 최소값 갱신
			answer = Math.min(answer, diff);
		}
		else {
			for(int idx=start; idx<n; idx++) {
				ch[idx] = true; // 해당 인덱스 방문 체크
				recur(depth+1, idx+1);	
				ch[idx] = false;
			}
		}
		
		
	}
	
	static int calculateDiff() {
		
		// 방문한 것은 스타트 팀, 방문안한 것은 링크 팀으로 나누자
		int startSum = 0;
		int linkSum = 0;
		
		for(int row=0; row<n; row++) {
			for(int col=0; col<n; col++) {
				
				if(ch[row] && ch[col]) {
					startSum += teamStatArr[row][col];
				}
				
				if(!ch[row] && !ch[col]) {
					linkSum += teamStatArr[row][col];
				}
			}
		}
		
		return Math.abs(startSum - linkSum);
	}
	
}
