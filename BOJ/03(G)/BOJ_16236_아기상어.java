import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 16236. 아기상어
 * 1. 처음 아기 상어 크기=2, 1초에 `상하좌우` 한칸 이동
 * 3. 아기 상어 지나가는 조건
 * 3-1. 크기가 같은 물고기는 먹을 수 X, 지나갈 수 O
 * 3-2. 크기가 작은 물고기는 먹을 수 O
 * 3-3. 크기가 큰 물고기는 먹을 수 X
 * 4. 아기상어 이동 방식
 * 4-1. 더이상 먹을 수 있는 물고기X -> 종료
 * 4-2. 먹을 수 있는 물고기 하나 -> 먹으러 감
 * 4-3. 1마리 이상 -> 거리가 가장 가까운 물고기 (가장 위 -> 가장 왼쪽 순)
 * 5. 자신의 `크기와 같은 수`의 물고기 먹을 때 마다 크기 증가
 * => 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지
 * 
 * 풀이
 * 1. 현재 위치에서 가장 가까운 물고기를 먹으러 가기 때문에 bfs로 푼다.
 * 2. bfs로 크기가 작은 물고기를 찾고, 먹으면 카운트한다. 이후 물고기는 빈칸으로
 * 3. 먹은 물고기 수와 같아지면, 상어 사이즈 키운다.
 *  
 */
public class BOJ_16236_아기상어 {

	static int boardLen;
	static int[][] board;
	// 아기 상어 크기
	static int sharkSize;
	// 아기 상어가 먹은 물고기 수
	static int fishCount;
	// 아기 상어 위치
	static int curR, curC;
	
	// 상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		boardLen = Integer.parseInt(br.readLine());
		board = new int[boardLen][boardLen];
		
		for(int row=0; row<boardLen; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<boardLen; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				// 아기상어 위치 저장
				if(board[row][col] == 9) {
					curR = row;
					curC = col;
					
					// 상어 위치를 나중에 이동해야 할 수 있으니 0으로 바꾸기
					board[row][col] = 0;
				}
			}
		}
		
		// 1. 처음 아기 상어 크기=2
		sharkSize = 2;
		fishCount = 0;
		System.out.println(play());
	}
	
	
	static int play() {
		
		// 최소 시간
		int answer = 0;
		
		while(true) {
			
			int[] fish = findFish();
			
			// 더이상 먹을 수 있는 물고기가 없으면,
			if(fish == null) {
				return answer;
			}
			
			// 해당 지점 물고기 먹기
			board[fish[0]][fish[1]] = 0;
			fishCount++;
			
			// 상어 위치 변경
			curR = fish[0];
			curC = fish[1];
			
			// 5. 자신의 `크기와 같은 수`의 물고기 먹을 때 마다 크기 증가
			if(sharkSize == fishCount) {
				sharkSize++;
				fishCount = 0;
			}
			
			// 시간 증가
			answer += fish[2];
		}
	}
	
	
	static int[] findFish() {
		
		// 가장 거리가 가까운 지점 찾는다.
		
		// 방문 체크 배열
		boolean[][] ch = new boolean[boardLen][boardLen];
		Queue<int[]> Q = new ArrayDeque<>();
		
		// 상어 위치 큐에 넣고 방문 체크
		Q.offer(new int[] {curR, curC});
		ch[curR][curC] = true;
		
		// 시간
		int time = 0;
		
		int[] fish = null;
		
		while(!Q.isEmpty()) {
			
			int len = Q.size();
			
			// 해당 거리 내에 물고기 먹을 수 있으면, 반환
			if(fish != null) return fish;
			
			while(len --> 0) {
				
				int[] cur = Q.poll();
				
				// 거리가 가장 가까운 물고기 (가장 위 -> 가장 왼쪽 순)
				for(int dir=0; dir<4; dir++) {
					
					int nextRow = cur[0] + dr[dir];
					int nextCol = cur[1] + dc[dir];
					
					// 해당 지점을 방문 했는지, 상어보다 물고기가 작거나 같은지(지나갈 수 있는 조건)
					if(nextRow >= 0 && nextCol >= 0 && nextRow < boardLen && nextCol < boardLen &&
							!ch[nextRow][nextCol] && sharkSize >= board[nextRow][nextCol]) {
						
						// 현재 지점을 상어가 먹을 수 있는지
						if(board[nextRow][nextCol] != 0 && sharkSize > board[nextRow][nextCol]) {
							
							// 처음 상어를 먹으면,
							if(fish == null) {
								fish = new int[] {nextRow, nextCol, time+1};
							}
							else {
								// 거리가 가장 가까운 물고기 (가장 위 -> 가장 왼쪽 순)
								if(nextRow < fish[0]) {
									fish = new int[] {nextRow, nextCol, time+1};
								}
								else if(nextRow == fish[0] && nextCol < fish[1]) {
									fish = new int[] {nextRow, nextCol, time+1};
								}
							}
						}
						
						// 지나만 가면 큐에 넣기
						ch[nextRow][nextCol] = true;
						Q.offer(new int[] {nextRow, nextCol});
					}
				}
			}
			// 시간 증가
			time++;
		}
		
		return fish;
	}
	

}
