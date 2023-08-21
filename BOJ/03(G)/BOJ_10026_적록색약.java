
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10026. 적록색약
 * 1. R, G, B 중 하나를 색칠한 그림
 * 2. 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성
 *
 * 풀이
 * 1. 적록색약인 사람과 일반 사람의 배열 따로 만들어서 계산
 * 2. 적록색약 배열은 초록색을 입력받았을 때 빨강색으로 바꿔서 대입
 * 3. 방문한 경우 'X' 처리
 */
public class BOJ_10026_적록색약 {
	
	static int boardLen;
	
	// 일반 그리드, 색약이 본 그리드
	static char[][] board, boardRG;
	
	// 상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// 정답
	static int answer;
	static int answerRG;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boardLen = Integer.parseInt(br.readLine());
		board = new char[boardLen][boardLen];
		boardRG = new char[boardLen][boardLen];;
		answer = 0;
		answerRG = 0;
		
		
		for(int row=0; row<boardLen; row++) {
			board[row] = br.readLine().toCharArray();

			for(int col=0; col<boardLen; col++) {
				//2. 적록색약 배열은 초록색을 입력받았을 때 빨강색으로 바꿔서 대입
				boardRG[row][col] = (board[row][col] == 'G')? 'R' : board[row][col];
			}
		}
		
		for(int row=0; row<boardLen; row++) {
			for(int col=0; col<boardLen; col++) {
				// 방문 안했으면, 방문
				if(board[row][col] != 'X') {
					dfs(row, col, board[row][col], board);
					answer++;
				}
				if(boardRG[row][col] != 'X') {
					dfs(row, col, boardRG[row][col], boardRG);
					answerRG++;
				}
			}
		}
		System.out.println(answer + " " + answerRG);
	}
	
	
	static void dfs(int row, int col, char color, char[][] board) {
		
		for(int dir=0; dir<4; dir++) {
			
			int nextRow = row + dr[dir];
			int nextCol = col + dc[dir];
			
			// 같은 색깔인 경우 이동
			if(nextRow >= 0 && nextCol >= 0 && nextRow < boardLen && nextCol < boardLen &&
					board[nextRow][nextCol] == color) {
				
				// 방문 표시
				board[nextRow][nextCol] = 'X';
				
				dfs(nextRow, nextCol, color, board);
			}
		}
		
	}



}
