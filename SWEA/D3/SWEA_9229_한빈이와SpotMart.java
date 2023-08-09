import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 9229. 한빈이와 Spot Mart
 * 1. 스팟마트에는 N개의 과자 봉지가 있고, 각 과자 봉지는 a그램의 무게를 가진다.
 * 2. 최대한 많은 양을 담으면서, 두 봉지의 무게가 M그램을 초과하면 X
 * 3. 들고갈 방법 없는 경우 -1 출력
 * => "두 봉지"만 사야함, 최대 무게합 출력
 * 
 * n개의 과자 봉지중 2개를 선택 => 조합 nC2
 * 조합 후 M그램을 초과하지 않았을 때 최대값 갱신
 * 최대 1000C2
 */
public class SWEA_9229_한빈이와SpotMart {

	// 과자 봉지 개수
	static int snackArrLength;
	// m그램
	static int maxWeight;
	// 정답
	static int answer;
	// 과자 배열
	static int[] snackArr;
	// 조합 배열
	static int[] combi;
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=tc; test_case++) {
			
			// 초기화
			st = new StringTokenizer(br.readLine());
			snackArrLength = Integer.parseInt(st.nextToken());
			snackArr = new int[snackArrLength];
			combi = new int[2];
			maxWeight = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int idx = 0 ; idx < snackArrLength ; idx++) {
				snackArr[idx] = Integer.parseInt(st.nextToken());
			}
			
			answer = -1;
			recursion(0, 0);
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	// 조합
	static void recursion(int depth, int start) {
		
		// 2개를 뽑았을 때
		if(depth == 2) {
			
			int sum = combi[0] + combi[1];
			// 최대 무게 초과 하지 않으면,
			if(sum <= maxWeight) answer = Math.max(answer, sum);
		}
		else {
			for(int idx=start; idx<snackArrLength; idx++) {
				combi[depth] = snackArr[idx];
				recursion(depth+1, idx+1);
			}
		}
		
	}

}
