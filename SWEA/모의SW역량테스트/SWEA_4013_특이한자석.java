import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4013. [모의 SW 역량테스트] 특이한 자석 
 * => 4개의 자석의 자성 정보와 자석을 1칸씩 K번 회전시키려고 할 때, K번 자석을 회전시킨 후 획득하는 점수의 총 합
 * 1. 각 4개의 자석은 8개의 날 가지고, N극 또는 S극 가짐
 * 2. 하나의 자석이 1칸 회전할 때, 서로 붙어 있는 날의 자성이 다를 경우메나 1칸 회전함
 * 
 * 입력
 * 1. 시계방향 : 1, 반시계방향 : -1 
 * 2. N극이 0, S극이 1
 * 
 * 풀이
 * 1. 서로 붙어있는 날은 2, 6이다
 * 2. 해당 자석이 돌아갈 떄 양옆의 자석(N극 <-> S극)을 확인한다.
 * 
 */
public class SWEA_4013_특이한자석 {

	static int rotationCount;
	// 자석 배열
	static int[][] magnets;
	static final int RIGHT=2, LEFT=6, CLOKWISE=1, COUNTERCLOKWISE=-1;
	// 방문배열
	static boolean[] ch;
	public static void main(String[] args) throws IOException {

		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			
			rotationCount = Integer.parseInt(br.readLine());
			magnets = new int[4][8];
			
			for(int magnetIdx=0; magnetIdx<4; magnetIdx++) {
				st = new StringTokenizer(br.readLine());
				for(int idx=0; idx<8; idx++) {
					magnets[magnetIdx][idx] = Integer.parseInt(st.nextToken());
				}
			}
			
			while(rotationCount --> 0) {
				
				//자석의 회전 정보는 회전시키려는 자석의 번호, 회전방향
				st = new StringTokenizer(br.readLine());
				int magnetIdx = Integer.parseInt(st.nextToken());
				int rotation = Integer.parseInt(st.nextToken());
				
				ch = new boolean[4];
				recur(magnetIdx-1, rotation);
			}
			
			// 점수 계산
			int answer = calculateScore();
			sb.append("#" + testCase + " " + answer + "\n");
		}
		System.out.print(sb);
	}
	
	static void recur(int magnetIdx, int rotation) {
		
		// 방문 체크
		ch[magnetIdx] = true;
		
		// magnetIdx 기준 왼쪽 으로 회전해야 하는 경우,
		if(magnetIdx-1 >= 0 && magnets[magnetIdx][LEFT] != magnets[magnetIdx-1][RIGHT] && !ch[magnetIdx-1]) {
			if(rotation == CLOKWISE) {
				recur(magnetIdx-1, COUNTERCLOKWISE);
			} else {
				recur(magnetIdx-1, CLOKWISE);
			}
		}
		// magnetIdx 기준 오른쪽 으로 회전해야 하는 경우,
		if(magnetIdx+1 < 4 && magnets[magnetIdx][RIGHT] != magnets[magnetIdx+1][LEFT] && !ch[magnetIdx+1]) {
			if(rotation == CLOKWISE) {
				recur(magnetIdx+1, COUNTERCLOKWISE);
			} else {
				recur(magnetIdx+1, CLOKWISE);
			}
		}
		
		
		// 시계방향 회전
		if(rotation == CLOKWISE) {
			int temp = magnets[magnetIdx][7];
			for(int idx=6; idx>=0; idx--) {
				magnets[magnetIdx][idx+1] = magnets[magnetIdx][idx];	
			}
			magnets[magnetIdx][0] = temp;
		}
		// 반시계방향 회전
		else {
			int temp = magnets[magnetIdx][0];
			for(int idx=1; idx<8; idx++) {
				magnets[magnetIdx][idx-1] = magnets[magnetIdx][idx];	
			}
			magnets[magnetIdx][7] = temp;
		}
	}
	
	static int calculateScore() {
		
		int score = 0;
		
		//2. N극이 0, S극이 1
		for(int magnetIdx=0; magnetIdx<4; magnetIdx++) {
			
			if(magnets[magnetIdx][0] == 1) {
				score += Math.pow(2, magnetIdx);
			}
		}
		
		return score;
	}
}
