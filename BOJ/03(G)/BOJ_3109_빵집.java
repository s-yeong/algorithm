import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3109. 빵집
 * 1. 빵집 R*C로 표현
 * 2. 첫째 열은 근처 빵집의 가스관, 마지막 열은 원웅이의 빵집
 * 3. 가스관과 빵집을 연결하는 파이프 설치
 * 3-1. 사이에 건물 있으면 놓을 수 없음
 * 3-2. 첫쨰 열에서 시작해야 하고, 마지막 열에서 끝나야 한다.
 * 3-3. 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결 가능
 * 4. 파이프라인을 '여러 개' 설치
 * 4-1. 각 칸을 지나는 파이프는 하나여야함! => 그 칸을 방문하면 다른 파이프가 방문 못함
 * => 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수
 * 
 * 풀이
 * 0. 최대 개수는 컬럼의 개수 만큼 가능하다.
 * 1. 첫쨰 열에서 컬럼에 따라 파이프 설치를 시작한다.
 * 2. 최대 개수를 구하려면 `처음 행`부터 최대한 오른쪽 위 대각선 부터 탐색해서 마지막 열에 도착하면 된다.
 * 3. 따라서 탐색 방향을 `오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선` 순으로 해서 마지막 열에 도착 했을 때 flag 한 후 모든 재귀를 return 한다.
 * 4. 이 떄 각 칸을 지니는 파이프는 하나여야 하기 때문에 방문 체크 해준다.
 * 
 */
public class BOJ_3109_빵집 {

	static int answer;
	static int R,C;
	static char[][] board;
	static boolean[][] ch;
	
	// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
	static int[] dr = {-1,0,1};	// 행
	static int[] dc = {1,1,1};	// 열
	
	// 마지막 열 도착시 return 하기 위한 flag
	static boolean flag;
	
	public static void main(String[] args) throws IOException{

		// 입출력.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// '.' 빈칸 'x' 건물
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		ch = new boolean[R][C];
		board = new char[R][C];
		answer = 0;
		
		for(int row=0; row<R; row++) {
			board[row] = br.readLine().toCharArray();
		}
		
		for(int row=0; row<R; row++) {
			flag = false;
			recur(row, 0);
		}
		System.out.println(answer);
	}
	
	static void recur(int row ,int col) {
		
		// 마지막 열 도착한 경우
		if(col == C-1) {
			flag = true;
			answer++;
		}
		else {
			// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
			for(int dir=0; dir<3; dir++) {
				
				int nextRow = row + dr[dir];
				int nextCol = col + dc[dir];
				
				// 범위 체크, 빈 칸 체크, 방문 체크
				if(nextRow >= 0 && nextRow<R && nextCol < C && !ch[nextRow][nextCol] && board[nextRow][nextCol] == '.') {
					
					ch[nextRow][nextCol] = true;
					
					recur(nextRow, nextCol);
					
					// 마지막 열에서 도착 한 경우 return
					if(flag) return;
				}
			}
		}
	}

}
