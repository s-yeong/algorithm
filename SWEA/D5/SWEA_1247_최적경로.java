import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1247. 최적 경로
 * 1. 회사 -> n명 고객 방문 -> 집
 * 2. 회사에서 출발하여 N명의 고객을 `모두` 방문하고 집으로 돌아오는 경로 중 가장 짧은 것
 * 3. 짧은 경로를 효율적으로 찾는게 아닌 이동거리만 구하기
 *
 * 풀이
 * 1. 각 지점 에서 갈 수 있는 거리 모두 계산
 * 2. 모든 경로 경우의 수 계산하면서 최소값 업데이트
 * 3. 최저 지점 넘어가면 반환
 */
public class SWEA_1247_최적경로 {
	
	static int answer;
	static int customerCount;
	// 회사, 집 위치
	static int[] company, house;
	// 고객 위치
	static int[][] customer;
	// 각 지점 거리 배열
	static int[][] distanceArr;
	// 방문 체크 배열
	static boolean[] ch;
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			
			customerCount = Integer.parseInt(br.readLine());
			customer = new int[customerCount+1][2];
			
			// 회사의 좌표,집의 좌표, N명의 고객의 좌표가 차례로 나열
			st = new StringTokenizer(br.readLine());
			
			company = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			house = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			for(int count=1; count<=customerCount; count++) {
				customer[count] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			ch = new boolean[customerCount+1];
			distanceArr = new int[customerCount+2][customerCount+2];
			// 각각의 지점 거리 계산
			calculateDistance();
			
			answer = Integer.MAX_VALUE;
			recur(0, 0, 0);
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	// 거리 구하기
	static int getDistance(int col1, int row1, int col2, int row2) {
		return Math.abs(row1-row2) + Math.abs(col1-col2);
	}
	
	// 각각의 지점 거리 계산
	static void calculateDistance() {
		
		// 0: compnay
		// 1~customerCount : customer
		// customerCount+1 : house
		
		// company, house -> customer
		for(int idx=1; idx<=customerCount; idx++) {
			distanceArr[0][idx] = getDistance(company[0], company[1], customer[idx][0], customer[idx][1]);
			distanceArr[idx][customerCount+1] = getDistance(house[0], house[1], customer[idx][0], customer[idx][1]);
		}
		
		// customer 지점 끼리
		for(int startIdx=1; startIdx<=customerCount; startIdx++) {
			for(int endIdx=1; endIdx<=customerCount; endIdx++) {
				distanceArr[startIdx][endIdx] = getDistance(customer[startIdx][0], customer[startIdx][1], customer[endIdx][0], customer[endIdx][1]);
			}
		}
	}
	
	static void recur(int depth, int cur, int dis) {
		
		// 최저 지점 넘어가면 반환
		if(dis > answer) return;
		
		// 모든 경로 이동후 house 선택해야 하는 경우
		if(depth == customerCount) {
			
			// hosue 거리 더한 후
			dis += distanceArr[cur][customerCount+1];
			
			// 최소값 갱신
			answer = Math.min(answer, dis);
			
			return;
		}
		
		for(int idx=1; idx<=customerCount; idx++) {
			
			if(ch[idx]) continue;
			
			ch[idx] = true;
			recur(depth+1, idx, dis + distanceArr[cur][idx]);
			ch[idx] = false;
		}
	}
}
