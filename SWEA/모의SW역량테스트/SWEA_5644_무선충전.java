import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 5644. [모의 SW 역량테스트] 무선 충전
 * 무선 충전시 최적의 BC를 선택하는 알고리즘
 * 1. AP의 충전 범위(=거리)이하에 접속 가능
 * 2. userA 는 (1,1) 지점, userB는 (10,10) 지점에서 출발
 * 3. 초기 위치(0초) 부터 충전 가능
 * 4. user 동시에 같은 위치 이동 가능
 * 5. 한 AP에 두 명의 사용자가 접속한 경우, 접속한 사용자의 수 만큼 충전 양 균등 분배
 * => 모든 사용자가 충전한 양의 합의 최대값 구하기
 * 
 * 풀이
 * 1. A, B이동시킬 때 마다 AP의 범위에 들어오는지 확인 (초기위치 부터) -> 해당하는 Ap 리스트에 담기
 * 2. 최대값 구할 때, 같은 AP인지 체크 -> performance 반으로 쪼개기
 * 3. 합 더하기
 */
public class SWEA_5644_무선충전 {
	
	static int answer;
	
	static int moveTime, apCount;
	// userA, userB의 매초마다 이동 방향
	static int[] userADir, userBDir;
	// 이동X, 상, 우, 하, 좌
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
	
	// userA, userB에서 가능한 ApList
	static List<Ap> userAApList, userBApList;
	
	static List<Ap> apList;
	static class Ap {
		
		// 위치
		int row;	int col;
		// 충전 범위
		int coverage;
		// 성능
		int performance;
		
		public Ap(int row, int col, int coverage, int performance) {
			this.row = row;
			this.col = col;
			this.coverage = coverage;
			this.performance = performance;
		}
	}
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			moveTime = Integer.parseInt(st.nextToken());
			apCount = Integer.parseInt(st.nextToken());
			userADir = new int[moveTime];
			userBDir = new int[moveTime];
			
			// userA 방향 정보
			st = new StringTokenizer(br.readLine());
			for(int time = 0; time<moveTime; time++) {
				userADir[time] = Integer.parseInt(st.nextToken());
			}
			// useB 방향 정보
			st = new StringTokenizer(br.readLine());
			for(int time = 0; time<moveTime; time++) {
				userBDir[time] = Integer.parseInt(st.nextToken());
			}
			
			// AP 정보
			apList = new ArrayList<>();
			for(int count=0; count<apCount; count++) {
				
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				int charge = Integer.parseInt(st.nextToken());
				int performance = Integer.parseInt(st.nextToken());
				
				apList.add(new Ap(row, col, charge, performance));
			}
			
			userAApList = new ArrayList<>();
			userBApList = new ArrayList<>();
			answer = 0;
			
			recur(0, 1, 1, 10, 10);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);

	}
	
	// 2. userA 는 (1,1) 지점, userB는 (10,10) 지점에서 출발
	static void recur(int time, int rowA, int colA, int rowB, int colB) {
		
		// userA, userB 가능한 AP 찾기
		findAp(rowA, colA, rowB, colB);
		
		// 최대값 구하기
		int max = getMax();
		answer += max;
		
		// user ApList 비우기
		userAApList.clear();
		userBApList.clear();
		
		// 마지막인 경우 이동X
		if(time == moveTime) return;
		
		// 이동하기
		recur(time+1, rowA + dr[userADir[time]], colA + dc[userADir[time]], rowB + dr[userBDir[time]], colB + dc[userBDir[time]]);
	}
	
	// 가능한 AP 찾기
	static void findAp(int rowA, int colA, int rowB, int colB) {
		
		for(Ap ap : apList) {
			
			// userA가 ap 범위에 들어오는 경우
			if(ap.coverage >= getDistance(rowA, colA, ap.row, ap.col)) {
				userAApList.add(ap);
			}
			
			// userB가 ap 범위에 들어오는 경우
			if(ap.coverage >= getDistance(rowB, colB, ap.row, ap.col)) {
				userBApList.add(ap);
			}
		}
	}
	
	// 거리 구하기
	static int getDistance(int row1, int col1, int row2, int col2) {
		return Math.abs(row1-row2) + Math.abs(col1-col2);
	}
	
	// 최대값 구하기
	static int getMax() {
		
		int max = 0;
		if(userAApList.size() > 0 && userBApList.size() > 0) {
			for(Ap apA : userAApList) {
				for(Ap apB : userBApList) {
					
					// 2. 같은 AP인지 체크 -> performance 반으로 쪼개기
					if(apA == apB) {
						max = Math.max(max, apA.performance/2 + apB.performance/2);
					}
					else {
						max = Math.max(max, apA.performance + apB.performance);
					}
				}
			}
		}
		else if(userAApList.size() > 0) {
			for(Ap apA : userAApList) {
				max = Math.max(max, apA.performance);
			}
		}
		else if(userBApList.size() > 0) {
			for(Ap apB : userBApList) {
				max = Math.max(max, apB.performance);
			}
		}
		
		return max;
	}
}
