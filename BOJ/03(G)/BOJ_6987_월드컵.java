import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 6987. 월드컵
 * 1. 6개국으로 구성된 각 조별로 동일한 조에 소속된 국가들과 `한 번`씩, 각 국가별로 `총 5번`의 경기를 치른다
 * 2. 조별리그가 끝난 후, 기자가 보내온 각 나라의 승, 무승부, 패의 수가 가능한 결과인지를 판별
 * => 네 가지 결과에 대하여 가능한 결과는 1, 불가능한 결과는 0 출력
 * 
 * 풀이
 * 0. 승, 무, 패에 대해 감소 시키는 방식으로 하면 안맞는다.
 * 1. 결국 모든 경우의 수를 계산해봐야 한다. ex) (A는 B랑 승,무,패 중 뭐할지. C랑 승,무,패 중 뭐할지. D란 승,무,패 중 뭐할지 ...)
 * 2. 각 나라마다 총 5번의 경기를 해야 하므로 그거 보다 많으면 잘못된 방식이다.
 * 3. 경우의 수 = 6*5/2 = 15만큼 경기를 했다는 것은 가능한 결과이다.(상태 공간 트리) => 3^15
 * 4. 15 경기 만큼 하는 도중, countryResult 값이 0이하면 해당 매치 불가능
 * 
 */

public class BOJ_6987_월드컵 {

	// 정답
	static int answer;
	// [각 나라][승, 무, 패]
	static int[][] countryResult;
	// 매칭 정보 배열
	static int[][] match;
	static final int MATCH_COUNT = 15;
	static final int WIN=0, DRAW=1, LOSE=2;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		countryResult = new int[6][3];
		match = new int[15][2];
		
		// 매칭 정보
		int idx = 0;
		for(int countryIdx=0; countryIdx<6; countryIdx++) {
			for(int matchedIdx=countryIdx+1; matchedIdx<6; matchedIdx++) {
				// ex) A의 매치 -> B,C,D,E,F
				match[idx][0] = countryIdx;
				match[idx][1] = matchedIdx;
				idx++;
			}
		}
		
		// 각 줄마다 6개국의 결과가 나라별로 승, 무승부, 패의 순서로 빈칸을 하나 사이에 두고 18개의 숫자로 주어진다
		line: for(int line=1; line<=4; line++) {
			
			flag = false;
			
			st = new StringTokenizer(br.readLine());
			
			for(int countryIdx=0; countryIdx<6; countryIdx++) {

				// 각 나라의 승,무,패 합
				int resultSum = 0;
				
				for(int resultIdx=0; resultIdx<3; resultIdx++) {
					countryResult[countryIdx][resultIdx] = Integer.parseInt(st.nextToken());
					resultSum += countryResult[countryIdx][resultIdx];
				}
				
				// 합이 5가 아니라면, 잘못된 방식
				if(resultSum != 5) {
					sb.append(0).append(" ");
					continue line;
				}
			}
			
			// 해당 예제에 대해 확인
			recur(0);
			
			// 정답
			answer = (flag)? 1 : 0;
			sb.append(answer).append(" ");
		}
		System.out.println(sb);
	}
	
	static void recur(int depth) {
		
		// 15만큼 경기를 했다는 것은 가능한 결과
		if(depth == MATCH_COUNT) {
			flag = true;
		}
		else {
			
			int countryIdx = match[depth][0];
			int matchedIdx = match[depth][1];
			
			// 승
			if(countryResult[countryIdx][WIN] > 0 && countryResult[matchedIdx][LOSE] > 0) {
				
				countryResult[countryIdx][WIN]--;
				countryResult[matchedIdx][LOSE]--;
				
				// 그 다음 매치
				recur(depth+1);
				
				// 원상 복구
				countryResult[countryIdx][WIN]++;
				countryResult[matchedIdx][LOSE]++;
			}
			
			// 무
			if(countryResult[countryIdx][DRAW] > 0 && countryResult[matchedIdx][DRAW] > 0) {
				
				countryResult[countryIdx][DRAW]--;
				countryResult[matchedIdx][DRAW]--;
				
				// 그 다음 매치
				recur(depth+1);
				
				// 원상 복구
				countryResult[countryIdx][DRAW]++;
				countryResult[matchedIdx][DRAW]++;
			}
			
			// 패
			if(countryResult[countryIdx][LOSE] > 0 && countryResult[matchedIdx][WIN] > 0) {
				
				countryResult[countryIdx][LOSE]--;
				countryResult[matchedIdx][WIN]--;
				
				// 그 다음 매치
				recur(depth+1);
				
				// 원상 복구
				countryResult[countryIdx][LOSE]++;
				countryResult[matchedIdx][WIN]++;
			}
		}
	}
}
