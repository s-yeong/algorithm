import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2839. 설탕 배달
 * 1. 설탕을 정확하게 N킬로그램 배달해야함
 * 2. 봉지 무게는 3킬로그램, 5킬로그램 
 * 3. 최대한 적은 봉지를 들고 가야함
 * 4. 만들 수 없으면 -1 출력
 * => N킬로그램 배달시 봉지 몇 개를 가져가면 되는지 구하기
 * 
 * 풀이
 * 1. N 최대 5000
 * 2. 5킬로 그램 -> 3킬로 그램
 * 3. 5*x + 3*y라고 했을 때, x부터 고려해서 N이 되는 값 구하기
 */
public class BOJ_2839_설탕배달 {
	
	// 5000/5 = 1000
	static final int fiveCountMax = 1000;
	// 5000/3 = 1666.x
	static final int threeCountMax = 1666;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		for(int threeCount=0; threeCount<=threeCountMax; threeCount++) {
			for(int fiveCount=0; fiveCount<=fiveCountMax; fiveCount++) {
				
				// 5*x + 3*y = N을 만족 했을 때,
				if(fiveCount * 5 + threeCount * 3 == N) {
					System.out.println(fiveCount + threeCount);
					// 바로 종료
					System.exit(0);
				}
			}
		}
        
		// N 무게를 만들 수 없는 경우 -1 출력  
		System.out.println(-1);
	}
}
