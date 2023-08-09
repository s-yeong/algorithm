import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 17070. 파이프 옮기기 1
 * 1. 각 행과 열은 1부터 시작하고 '2개'의 연속된 칸을 차지함
 * 2. 파이프 회전 방향 = 가로, 세로, 대각선 (현재 방향)
 * 2. 파이프 미는 방향 = 오, 오아래, 아래
 * 3. 빈칸만 차지해야함. 벽 만나면 X
 * 
 * 4-1. 파이프가 가로인 경우 => 오, 오아래
 * 4-2. 파이프가 세로인 경우 => 아래, 오아래
 * 4-3. 파이프가 대각선인 경우 => 오, 아래, 오아래 
 * => 첫째 줄에 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수 출력, 없으면 0 출력
 * 
 * 문제 해결
 * 1. 한쪽 끝을 (N, N)으로 도달했을 때를 체크 해야하기 때문에 2개 의 연속된 칸 중 한쪽 끝을 기준으로 옮기기
 * 2. 현재 방향에 따라 미는 방법이 다르기 때문에 방향을 저장해야 함  
 * 3. dfs를 통해 '빈칸인 경우'에 파이프 밀기, 종료 조건은 (N, N)에 도달 했을 때
 */
public class BOJ_17070_파이프옮기기1 {

	// 집의 크기
	static int houseSize;
	// 집
	static int[][] house;
	
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;
	static final int DIAGONAL = 2;
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력 및 초기화
		houseSize = Integer.parseInt(br.readLine());
		house = new int[houseSize+1][houseSize+1];
		for(int rowIdx = 1; rowIdx <= houseSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx = 1; colIdx<= houseSize; colIdx++) {
				house[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		dfs(2, 1, HORIZONTAL);
		System.out.println(answer);
	}
	
	static void dfs(int col, int row, int dir) {
		
		// 갈 수 없으면 리턴
		if(row > houseSize || col > houseSize || house[row][col] == 1) {
			return;
		}
		
		if(col == houseSize && row == houseSize) {
			answer++;
		}
		else {
			
			// 4-1. 파이프가 가로인 경우 => 오, 오아래
			if(dir == HORIZONTAL) {
				// 오
				dfs(col+1, row, HORIZONTAL);
			}
			
			// 4-2. 파이프가 세로인 경우 => 아래, 오아래
			else if(dir == VERTICAL) {
				// 아래
				dfs(col, row+1, VERTICAL);
				
			}
			
			// 4-3. 파이프가 대각선인 경우 => 오, 아래, 오아래
			else if(dir == DIAGONAL) {
				// 오
				dfs(col+1, row, HORIZONTAL);
				// 아래
				dfs(col, row+1, VERTICAL);
			}
			
			// 오아래는 모두 공통
			// 오아래의 경우 가는 위치 뿐만 아니라 오른쪽, 아래 까지 빈칸 이어야 함
			if(col+1 > houseSize || row+1 > houseSize || house[row][col+1] == 1 || house[row+1][col] == 1) 
				return;
			dfs(col+1, row+1, DIAGONAL);
		}
	}
	
	
}
