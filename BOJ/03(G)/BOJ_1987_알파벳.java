import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1987. 알파벳
 * 1. 1행 1열에 말이 놓여 있고, 대문자 알파벳 적혀 있다.
 * 2. 상하좌우로 이동하며, 같은 알파벳이 적힌 칸을 두 번 지나갈 수 없음 
 * => 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하기
 *
 * 풀이
 * 1. 상하좌우로 이동하면서 방문 기준을 알파벳으로 한다.
 * 2. 현재 이동 수를 기록하고 최대값인 경우 갱신한다.
 */
public class BOJ_1987_알파벳 {

	static char[][] board;
	static int R, C;
	static int answer;
	static boolean[] ch; // 알파벳 체크 배열
	
	// 상하좌우 방향
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 및 초기화
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		for(int row=0; row<R; row++) {
			board[row] = br.readLine().toCharArray();
		}
		ch = new boolean['Z' - 'A' + 1];
		answer = 0;
		
		// 첫 알파벳도 체크
		ch[board[0][0] - 'A'] = true;
		dfs(0,0,1);
		System.out.println(answer);
	}
	
	static void dfs(int curR, int curC, int disCount) {
		
		// 최대값인 경우 갱신
		if(disCount > answer) answer = disCount;
		
		for(int dir=0; dir<4; dir++) {
			
			int nextR = curR + dr[dir];
			int nextC = curC + dc[dir];
			
			if(nextR >= 0 && nextC >= 0 && nextR < R && nextC < C &&
					!ch[board[nextR][nextC] - 'A']) {
				
				// 알파벳 체크
				ch[board[nextR][nextC] - 'A'] = true;
				
				dfs(nextR, nextC, disCount+1);
				
				// 알파벳 체크 해제
				ch[board[nextR][nextC] - 'A'] = false;
			}
		}
	}

}
