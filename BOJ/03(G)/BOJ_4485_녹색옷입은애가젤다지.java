import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 4485. 녹색 옷 입은 애가 젤다지?
 * 1. (0.0)칸에서 (N-1, N-1)까지 최소 비용으로 이동하기
 * 
 * 풀이
 * 1. 간선의 비용이 양의 정수이기 때문에 최소 비용으로 이동하기 위해서는 다익스트라 이용
 * 
 */
public class Main {
	
	// 상하좌우
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static boolean[][] ch;
	static int[][] board, dis;
	static int boardLen, answer;
	static final int END = 0;
	
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int testCase = 1;
		while(true) {
			
			boardLen = Integer.parseInt(br.readLine());
			// 종료
			if(boardLen == END) break;
			
			board = new int[boardLen][boardLen];
			for(int row=0; row<boardLen; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col=0; col<boardLen; col++) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = solve();
			
			sb.append("Problem " + testCase++ + ": " + answer + "\n");
		}
		
		System.out.print(sb);
	}
	
	static int solve() {
		
		ch = new boolean[boardLen][boardLen];
		
		// 다익스트라 거리 배열 초기화
		dis = new int[boardLen][boardLen];
		for(int row=0; row<boardLen; row++) {
			Arrays.fill(dis[row], Integer.MAX_VALUE);
		}
		
		
		// 우선순위 큐 활용하여 최소 비용 계산
		PriorityQueue<int[]> pQ = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(dis[o1[0]][o1[1]], dis[o2[0]][o2[1]]);
			}
		});
		
		// 시작점 초기화 및 넣기
		dis[0][0] = board[0][0];
		pQ.offer(new int[] {0,0});
		 
		while(!pQ.isEmpty()) {
			
			int[] cur = pQ.poll();
			int curRow = cur[0];
			int curCol = cur[1];
			ch[curRow][curCol] = true;
			
			if(curRow == boardLen-1 && curCol == boardLen-1) return dis[boardLen-1][boardLen-1];
			
			for(int dir=0; dir<4; dir++) {
				
				int nextRow = curRow + dr[dir];
				int nextCol = curCol + dc[dir];
				
				if(nextRow >= 0 && nextCol >= 0 && nextRow < boardLen && nextCol < boardLen && !ch[nextRow][nextCol]) {
					
					// 최소 거리 갱신이 필요하면,
					if(dis[nextRow][nextCol] > dis[curRow][curCol] + board[nextRow][nextCol]) {
						// 갱신 후 넣기
						dis[nextRow][nextCol] = dis[curRow][curCol] + board[nextRow][nextCol];
						pQ.offer(new int[] {nextRow, nextCol});
					}
				}
			}
		}
		
		return dis[boardLen-1][boardLen-1];
	}
	
}
