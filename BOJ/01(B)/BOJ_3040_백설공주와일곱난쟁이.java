import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 3040. 백설 공주와 일곱 난쟁이
 * 입력 : 총 아홉개 줄에 1보다 크거나 같고 99보다 작거나 같은 자연수가 주어짐
 * => 일곱 난쟁이를 찾기 (아홉 개의 수 중 합이 100이 되는 일곱 개의 수)
 * 1. 전체 합 구한 다음 9개 중에서 2개 뽑았을 때 전체 - 2개 합이 100이 되면 일곱 난쟁이 찾음
 * 2. 9C2
 */
public class BOJ_3040_백설공주와일곱난쟁이 {
	
	// 조합을 위한 방문처리 배열
	static boolean[] ch;
	static int[] dwarfs;	// 난쟁이 배열
	static int totalSum;	// 전체 합
	
	// 출력
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 초기화 및 입력
		sb = new StringBuilder();
		dwarfs = new int[9];
		ch = new boolean[9];
		totalSum = 0;
		for(int idx=0; idx<9; idx++) {
			dwarfs[idx] = Integer.parseInt(br.readLine());
			totalSum += dwarfs[idx];
		}
		
		recur(0,0,0);
		System.out.print(sb);
	}
	
	// 9C2 조합
	static void recur(int depth, int start, int sum) {
		
		// 조합 완료시,
		if(depth == 2) {
			// 2명을 제외한 7명의 합이 100이 되면
			if(totalSum - sum == 100) {
				// 해당 인덱스 출력
				for(int idx=0; idx<9; idx++) {
					if(ch[idx]) continue;
					sb.append(dwarfs[idx]).append("\n");
				}
			}
		}
		else {
			for(int idx=start; idx<9; idx++) {
				ch[idx] = true;
				recur(depth+1, idx+1, sum + dwarfs[idx]);
				ch[idx] = false;
			}
		}
	}
}
