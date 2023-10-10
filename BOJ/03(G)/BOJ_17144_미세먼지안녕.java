import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 17144. 미세먼지 안녕!
 * => T초가 지난 후 미세먼지 양 구하기
 * 
 * 1초 동안 일어나는 일
 * 1. 미세먼지 확산
 * 1-1. 인접한 네 방향 확산
 * 1-2. 공기 청정기가 있거나(-1), 칸 벗어난 경우 확산 X
 * 1-3. 확산 되는 양 : 현재 값 / 5
 * 1-4. 확산 후 남은 현재 양 = 현재 값 - 확산 되는 양 * 확산 개수  
 * 2. 공기청정기 작동
 * 2-1. 위쪽 : 반시계 방향, 아럐쪽 : 시계 방향
 * 2-2. 모두 한 칸씩 이동
 * 2-3. 공기 청정기로 들어오면 미세먼지 삼킴
 * 
 */
public class BOJ_17144_미세먼지안녕 {
	
	static int rowLen, colLen, time;
	static int[][] board;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	// 공기청정기 위치(위쪽)
	static int airCleanerUp, airCleanerDown;
	static Queue<int[]> Q;
	
	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		rowLen = Integer.parseInt(st.nextToken());
		colLen = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		board = new int[rowLen][colLen];
		for(int row=0; row<rowLen; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<colLen; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				
				// 공기 청정기 위치(위쪽)면,
				if(board[row][col] == -1 && airCleanerUp == 0) {
					airCleanerUp = row;
					airCleanerDown = row+1;
				}
			}
		}
		
		System.out.println(solve());
	}
	
	static int solve() {
		
		Q = new ArrayDeque<>();
		
		//1초 동안 일어나는 일
		while(time --> 0) {
			
			//1. 미세먼지 확산
			diffuse();
			//2. 공기청정기 작동
			operate();
		}
		
		//남아있는 미세먼지의 양 계산
		int sum = 0;
		for(int row=0; row<rowLen; row++) {
			for(int col=0; col<colLen; col++) {
				sum += board[row][col];
			}
		}
		
		//공기청정기 크기 만큼 더해주기
		return sum+2;
	}
	
	
	//1. 미세먼지 확산
	static void diffuse() {
		
		// 미세먼지 큐에 넣기
		for(int row=0; row<rowLen; row++) {
			for(int col=0; col<colLen; col++) {
				if(board[row][col] > 0) Q.offer(new int[] {row, col, board[row][col]});
			}
		}
		
		//1-1. 인접한 네 방향 확산
		while(!Q.isEmpty()) {
			
			int[] cur = Q.poll();
			int curRow = cur[0];
			int curCol = cur[1];
			int curQuantity = cur[2];
			
			// 확산 개수
			int diffuseCount = 0;
			
			//1-1. 인접한 네 방향 확산
			for(int dir=0; dir<4; dir++) {
				
				int nextRow = curRow + dr[dir];
				int nextCol = curCol + dc[dir];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= rowLen || nextCol >= colLen) continue;
				
				// 공기청정기면, 확산 불가능
				if(board[nextRow][nextCol] == -1) continue;
				
				//1-3. 확산 되는 양 : 현재 값 / 5
				diffuseCount++;
				board[nextRow][nextCol] += curQuantity / 5;
			}
			
			//1-4. 확산 후 남은 현재 양 = 현재 값 - 확산 되는 양 * 확산 개수
			board[curRow][curCol] -= (curQuantity / 5) * diffuseCount;
		}
	}
	
	//2. 공기청정기 작동
	static void operate() {
		
		//2-1. 위쪽 : 반시계 방향, 아럐쪽 : 시계 방향
		claenUp();
		cleanDown();
	}
	
	static void claenUp() {
		
		// 반시계 방향 -> 시계 방향으로 시작
		
		//1.위
		for(int row=airCleanerUp-1; row>=0; row--) {
			board[row+1][0] = board[row][0];
		}
		
		//2.오
		for(int col=1; col<colLen; col++) {
			board[0][col-1] = board[0][col];
		}
		//3.아래
		for(int row=1; row<=airCleanerUp; row++) {
			board[row-1][colLen-1] = board[row][colLen-1];
		}
		
		//4.왼
		for(int col=colLen-2; col>=0; col--) {
			board[airCleanerUp][col+1] = board[airCleanerUp][col];
		}
		
		// 처음에 나가는 방향은 0으로
		board[airCleanerUp][1] = 0;
		// 공기청정기는 다시 -1로 두기
		board[airCleanerUp][0] = -1;
	}
	
	static void cleanDown() {
		
		//1.아래
		for(int row=airCleanerDown+1; row<rowLen; row++) {
			board[row-1][0] = board[row][0];
		}
		//2.오
		for(int col=1; col<colLen; col++) {
			board[rowLen-1][col-1] = board[rowLen-1][col];
		}
		//3.위
		for(int row=rowLen-2; row>=airCleanerDown-1; row--) {
			board[row+1][colLen-1] = board[row][colLen-1];
		}
		//4.왼
		for(int col=colLen-2; col>=0; col--) {
			board[airCleanerDown][col+1] = board[airCleanerDown][col];
		}
		
		// 처음에 나가는 방향은 0으로
		board[airCleanerDown][1] = 0;
		// 공기청정기는 다시 -1로 두기
		board[airCleanerDown][0] = -1;
	}
}
