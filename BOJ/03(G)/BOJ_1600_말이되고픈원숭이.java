import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1600. 말이 되고픈 원숭이
 * => 원숭이의 동작수의 최솟값 구하기
 * 1. 원숭이는 horseCount만큼 말처럼 이동 가능
 * 2. 그 외는 상하좌우 이동만 가능
 * 3. 맨 왼쪽 위에서 시작해서, 맨 오른쪽 아래까지 가야함 (도착지점)
 * 
 * 입출력
 * - 0:평지, 1:장애물
 * - 갈 수 없는 경우 -1 출력
 * 
 * 풀이
 * 1. 현재 위치를 다시 갈 수도 있으니, 방문 체크 배열
 * 2. 이 때 현재 위치가 horseCount의 개수에 따라 다르게 방문할 수 있으므로 3중 방문 체크 배열을 생각 
 * 3. horseCount가 제한되어 있기 떄문에, horseCount를 쓴 경우와 안쓴 경우를 생각해서 이동
 * 4. 최단 거리를 구할 때 큐 객체에 거리를 담아서 이동하는 방법도 있다!
 * 
 */
public class BOJ_1600_말이되고픈원숭이 {
	
	static int horseCount;
	static int rowLen, colLen;
	static int[][] board;
	
	// 상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// 말 이동 (8)
	static int[] hr = {-2,-1,1,2,2,1,-1,-2};
	static int[] hc = {1,2,2,1,-1,-2,-2,-1};
	
	// 방문 배열 - [row][col][말 이동 횟수]
	static boolean[][][] ch;
	
	static class Monkey {
		// 위치
		int row;
		int col;
		// 말 이동 수
		int horseCount;
		// 이동 거리
		int dis;
		
		public Monkey(int row, int col, int horseCount, int dis) {
			this.row = row;
			this.col = col;
			this.horseCount = horseCount;
			this.dis = dis;
		}
	}
	
	public static void main(String[] args) throws IOException {

		// 입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		horseCount = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowLen = Integer.parseInt(st.nextToken());
		colLen = Integer.parseInt(st.nextToken());
		board = new int[rowLen][colLen];
		
		for(int col=0; col<colLen; col++) {
			st = new StringTokenizer(br.readLine());
				for(int row=0; row<rowLen; row++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// bfs
		System.out.println(bfs());
	}
	
	static int bfs() {
		
		ch = new boolean[rowLen][colLen][horseCount+1];
		
		Queue<Monkey> Q = new ArrayDeque<>();
		Q.offer(new Monkey(0,0, horseCount, 0));
		
		while(!Q.isEmpty()) {
			
			Monkey monkey = Q.poll();
			
			// 도착지점 도착시
			if(monkey.row == rowLen-1 && monkey.col == colLen-1) {
				return monkey.dis;
			}
			
			// 말 이동
			// 이동 횟수가 0보다 커야 한다.
			if(monkey.horseCount > 0) {
				
				for(int dir=0; dir<8; dir++) {
					
					int nextRow = monkey.row + hr[dir];
					int nextCol = monkey.col + hc[dir];
					
					// 장애물아니면서, 방문한 적 없을 때,
					if(nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen
							&& board[nextRow][nextCol] == 0 && !ch[nextRow][nextCol][monkey.horseCount-1]) {
						ch[nextRow][nextCol][monkey.horseCount-1] = true;
						Q.offer(new Monkey(nextRow, nextCol, monkey.horseCount-1, monkey.dis+1));
					}
				}
			}
			
			// 일반 이동
			for(int dir=0; dir<4; dir++) {
				int nextRow = monkey.row + dr[dir];
				int nextCol = monkey.col + dc[dir];
				
				// 장애물아니면서, 방문한 적 없을 때,
				if(nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen
						&& board[nextRow][nextCol] == 0 && !ch[nextRow][nextCol][monkey.horseCount]) {
					ch[nextRow][nextCol][monkey.horseCount] = true;
					Q.offer(new Monkey(nextRow, nextCol, monkey.horseCount, monkey.dis+1));
				}
			}
		}
		// 이동 불가능 시, -1 리턴
		return -1;
	}
}
