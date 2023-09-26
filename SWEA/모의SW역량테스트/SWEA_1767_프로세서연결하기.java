import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1767. [SW Test 샘플문제] 프로세서 연결하기
 * 1. 1개의 cell에는 1개의 core 혹은 1개의 전선이 올 수 있다.
 * 2. `가장 자리`에는 전원이 흐른다.
 * 3. core와 전원을 연결하는 전선은 `직선`으로만 설치 가능. 전선끼리 `교차 불가능`
 * 4. `가장 자리`에 위치한 코어는 `이미 전원 연결`된 걸로 간주
 * => `최대한 많은 코어`에 전원을 연결했을 경우 전선 길이의 합 구하기 
 * => 여러 방법이 있는 경우 그 중 `최소값` 구하기
 * 
 * 조건
 * 0. 코어 최소 1개 이상 최대 12개
 * 0. 전원이 연결되지 않은 코어가 존재할 수 있다 => 최대한 많은 코어에 전원을 연결해야 한다.
 * 
 * 풀이
 * 1. 가장 자리의 코어는 무시(가장자리인지 체크) => 코어 리스트에 포함시키지 않는다. 전선을 연결할 때만 체크하면 된다.
 * 2. 코어의 위치를 저장한 다음, 코어 위치 기반으로 전선을 연결하기
 * 3. 4방향으로 전선 연결 => 교차 되면 안된다 => 전선 설치시 그 지점에 전선을 만나는 경우 해당 방향으로 전선 연결 불가능
 * 4. 전원이 연결된 코어 체크하고 최대 코어 갱신시 합 구하기, 같은 코어 수면 최소값 구하기
 * 5. 전선은 `2`로 표시
 * 
 */
public class SWEA_1767_프로세서연결하기 {

	static int boardLen;
	static int[][] board;
	// 코어 리스트
	static List<Core> coreList;
	// 코어 개수
	static int coreSize;
	
	// 코어 클래스
	static class Core {
		int row; int col;
		int dir;
		public Core(int row, int col) {
			this.row=row;
			this.col=col;
		}
	}
	
	// 상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// 최대 전원 수
	static int maxPowerCount;
	// 만약 전원수가 같은경우 최소 전선 개수
	static int minWireCount;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			boardLen = Integer.parseInt(br.readLine().trim());
			board = new int[boardLen][boardLen];
			coreList = new ArrayList<>();
			
			for(int row=0; row<boardLen; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col=0; col<boardLen; col++) {
					board[row][col] = Integer.parseInt(st.nextToken());
					
					// 코어이면서, 가장자리가 아닌 경우 리스트에 추가
					if(board[row][col] == 1 && !(row == 0 || row == boardLen-1 || col == 0 || col == boardLen-1)) {
						coreList.add(new Core(row, col));
					}
				}
			}
			
			coreSize = coreList.size();
			maxPowerCount = 0;
			minWireCount = 0;
			recur(0, 0, 0);
			
			sb.append("#" + testCase + " " + minWireCount + "\n");
		}
		System.out.print(sb);
	}
	
	static void recur(int depth, int powerCount, int wireCount) {
	
		// 코어 전선 배치 완료
		if(depth == coreSize) {
			
			//`최대한 많은 코어`에 전원을 연결했을 경우 전선 길이의 합 구하기 
			if(powerCount > maxPowerCount) {
				maxPowerCount = powerCount;
				minWireCount = wireCount;
			}
			
			// 여러 방법이 있는 경우 그 중 `최소값` 구하기
			else if(powerCount == maxPowerCount) {
				minWireCount = Math.min(minWireCount, wireCount);
			}
			
			return;
		}
			
		Core curCore = coreList.get(depth);
		
		for(int dir=0; dir<4; dir++) {
			
			int nextRow = curCore.row + dr[dir];
			int nextCol = curCore.col + dc[dir];
			// 전선 설치 가능 여부
			boolean flag = true;
			// 전선 설치 개수
			int count = 0;
			
			// 전선 설치
			while(nextRow >= 0 && nextCol >= 0 && nextRow < boardLen && nextCol < boardLen) {
				
				// 전선을 설치하다가, 코어를 만나거나 전선이 있으면, 그 방향으로는 전선을 연결 못한다.
				if(board[nextRow][nextCol] != 0) {
					flag = false;
					break;
				}
				
				// 전선 설치
				board[nextRow][nextCol] = 2;
				
				nextRow += dr[dir];
				nextCol += dc[dir];
				count++;
			}
			
			// 전선 연결 못하면 원래대로
			if(!flag) {
				
				nextRow -= dr[dir];
				nextCol -= dc[dir];
				while(!(nextRow == curCore.row && nextCol == curCore.col)) {
					board[nextRow][nextCol] = 0;
					nextRow -= dr[dir];
					nextCol -= dc[dir];
				}
				
				// 다음 코어로
				recur(depth+1, powerCount, wireCount);
			}
			// 연결 가능하면 다음 코어 확인
			else {
				
				recur(depth+1, powerCount+1, wireCount + count);
				
				// 되돌리기
				nextRow -= dr[dir];
				nextCol -= dc[dir];
				while(!(nextRow == curCore.row && nextCol == curCore.col)) {
					while(!(nextRow == curCore.row && nextCol == curCore.col)) {
						board[nextRow][nextCol] = 0;
						nextRow -= dr[dir];
						nextCol -= dc[dir];
					}
				}
			}
		}
	}
}
