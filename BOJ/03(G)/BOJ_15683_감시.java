import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 15683. 감시
 * 1. 사무실 n x m
 * 2. CCTV 5가지 종류
 * 3-1. CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다.
 * 3-2. CCTV는 `벽`을 통과할 수 없지만, CCTV를 통과할 수 있다.
 * 3-3. CCTV는 90도 방향으로 회전할 수 있고, 감시하려고 하는 방향이 가로 또는 세로여야 한다.
 * 4. 0: 빈칸, 1~5: CCTV, 6: 벽
 * 
 * => CCTV의 `방향`을 적절히 정해서 사각 지대의 최소 크기 구하기
 * 
 * 풀이
 * 0. CCTV의 최대 개수 8개 => 4^8 = 2^16 경우의 수
 * 1. 사각 지대는 0이 되는 영역
 * 2. 2^16가지 경우의 수를 고려하여 CCTV 방향을 정한 다음 최소값 갱신
 * 
 */
public class BOJ_15683_감시 {
	
	static int[][] board;
	static int rowLen, colLen;
	static List<Cctv> cctvList;
	static int cctvLen;
	
	static class Cctv {
		
		int row; int col;
		// cctv 종류
		int type;
		// 감시 방향
		int dir;
		public Cctv(int row, int col, int type, int dir) {
			this.row = row;
			this.col = col;
			this.type = type;
			this.dir = dir;
		}
	}
	
	// 시계방향으로 (상,우,하,좌)
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		// 입출력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowLen = Integer.parseInt(st.nextToken());
		colLen = Integer.parseInt(st.nextToken());
		board = new int[rowLen][colLen];
		cctvList = new ArrayList<>();
		
		for(int row=0; row<rowLen; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<colLen; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				
				// cctv면, 리스트에 넣기
				if(board[row][col] >= 1 && board[row][col] <=5) {
					cctvList.add(new Cctv(row, col, board[row][col], 0));
				}
			}
		}
		answer = Integer.MAX_VALUE;
		cctvLen = cctvList.size();
		recur(0);
		System.out.println(answer);
	}
	
	static void recur(int depth) {
		
		if(depth == cctvLen) {
			// cctv 방향 결정되면,
			// 사각 지대 계산하기
			answer = Math.min(answer, calculate());
		}
		else {
			for(int dir=0; dir<4; dir++) {
				cctvList.get(depth).dir = dir;
				recur(depth+1);
			}
		}
	}
	
	static int calculate() {
		
		int[][] tempBoard = copyArr();

		// 9는 cctv 감시 대상
		for(Cctv cctv : cctvList) {
			
			monitor(cctv, tempBoard);
		}
		
		// 계산 후 빈칸 계산
		int blankCount = 0;
		for(int row=0; row<rowLen; row++) {
			for(int col=0; col<colLen; col++) {
				if(tempBoard[row][col] == 0) blankCount++;
			}
		}
		return blankCount;
	}
	
	
	static void monitor(Cctv cctv, int[][] tempBoard) {
		
		// 9는 cctv 감시 대상
			
		// cctv.type에 따라 방향만큼 더하는게 추가된다.
		int dirCountLen = 1;
		if(cctv.type == 3) dirCountLen = 2;
		else if(cctv.type == 4) dirCountLen = 3;
		else if(cctv.type == 5) dirCountLen = 4;
		
		// 3번은 직각 방향이어야 함 => 무조건 시계방향 90도로 계산하자
		for(int dirCount=0; dirCount<dirCountLen; dirCount++) {
			
			int nextDir = (cctv.dir + dirCount) % 4; 
			int nextRow = cctv.row + dr[nextDir];
			int nextCol = cctv.col + dc[nextDir];
			while(nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen && tempBoard[nextRow][nextCol] != 6) {
				
				// CCTV도 9로 바꿔도 상관은 없다. 어차피 cctvList에 위치를 저장해뒀기 때문에
				tempBoard[nextRow][nextCol] = 9;
				
				nextRow += dr[nextDir];
				nextCol += dc[nextDir];
			}
		}
		
		if(cctv.type == 2) {
			// 반대 방향 계산
			int nextRow = cctv.row - dr[cctv.dir];
			int nextCol = cctv.col - dc[cctv.dir];
			
			while(nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen && tempBoard[nextRow][nextCol] != 6) {
				
				// CCTV도 9로 바꿔도 상관은 없다. 어차피 cctvList에 위치를 저장해뒀기 때문에
				tempBoard[nextRow][nextCol] = 9;
				
				nextRow -= dr[cctv.dir];
				nextCol -= dc[cctv.dir];
			}
		}
	}
	
	static int[][] copyArr() {
		
		int[][] tempBoard = new int[rowLen][colLen];
		
		for(int row=0; row<rowLen; row++) {
			for(int col=0; col<colLen; col++) {
				tempBoard[row][col] = board[row][col];
			}
		}
		return tempBoard;
	}
	

}
