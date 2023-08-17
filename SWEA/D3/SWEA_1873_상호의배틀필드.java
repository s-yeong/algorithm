import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1873. 상호의 배틀필드
 * 1. 전차는 평지에 들어갈 수 있다.
 * 2. 전차가 바라보는 방향을 바꾸고 그 방향 다음 칸이 평지면 이동
 * 2-1. U, D, L, R
 * 3. S : 현재 바라보고 있는 방향으로 포탄 발사
 * 3-1. 벽에 충돌하거나 맵 밖으로 나갈 때 까지 직진
 * 3-2. 벽을 만나면 포탄 멈춤. `벽돌 벽`이면 벽 파괴 후 평지
 * 3-3. `강철 벽`은 그대로
 * 4. 전차가 이동하면, 원래 위치는 다시 평지로 바꾸기
 * 
 * 풀이
 * 0. 전차 위치 기억하기
 * 1. 바라보는 방향 기준으로 쏘는데, 물은 통과한다!
 * 2. 벽을 만났을 때 벽돌이면 빈칸으로 바꾸기
 */
public class SWEA_1873_상호의배틀필드 {
	
	static int rowLen, colLen;	// 격자판 길이
	static char[][] board;	// 격자판
	static int inputNum;
	static char[] command;
	// 전차 현재 위치
	static int curR, curC;
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			// 초기화
			st = new StringTokenizer(br.readLine());
			rowLen = Integer.parseInt(st.nextToken());
			colLen = Integer.parseInt(st.nextToken());
			board = new char[rowLen][colLen];
			
			
			for(int row=0; row<rowLen; row++) {
				char[] temp = br.readLine().toCharArray();
				for(int col=0; col<colLen; col++) {
					board[row][col] = temp[col];
					
					// 전차의 위치 저장
					if(board[row][col] == '^' || board[row][col] == 'v' || board[row][col] == '<' || board[row][col] == '>') {
						curR = row;
						curC = col;
					}
				}
			}
			
			inputNum = Integer.parseInt(br.readLine());
			command = br.readLine().toCharArray();
			
			play();
			
			// 출력
			sb.append("#").append(testCase).append(" ");
			for(int row=0; row<rowLen; row++) {
				for(int col=0; col<colLen; col++) {
					sb.append(board[row][col]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	static void play() {
		
		for(char com : command) {
			
			// 3. S : 현재 바라보고 있는 방향으로 포탄 발사
			if(com == 'S') {
				shoot();
			}
			else {
				
				int nextR, nextC;
				
				// 전차가 바라보는 방향을 바꾸고 그 방향 다음 칸이 평지면 이동
				if(com == 'U') {
					
					nextR = curR - 1;
					if(nextR >= 0 && board[nextR][curC] == '.') {
						
						// 4. 전차가 이동하면, 원래 위치는 다시 평지로 바꾸기
						board[curR][curC] = '.';
						curR = nextR;
					}
					board[curR][curC] = '^';
				}
				else if(com =='D') {
					
					nextR = curR + 1;
					
					if(nextR < rowLen && board[nextR][curC] == '.') {
						
						// 4. 전차가 이동하면, 원래 위치는 다시 평지로 바꾸기
						board[curR][curC] = '.';
						curR = nextR;
					}
					board[curR][curC] = 'v';
				}
				else if(com == 'L') {
					
					// 전차가 바라보는 방향을 바꾸고 그 방향 다음 칸이 평지면 이동
					nextC = curC - 1;
					
					if(nextC >= 0 && board[curR][nextC] == '.') {
						
						// 4. 전차가 이동하면, 원래 위치는 다시 평지로 바꾸기
						board[curR][curC] = '.';
						curC = nextC;
					}
					board[curR][curC] = '<';
				}
				else if(com == 'R') {
					
					// 전차가 바라보는 방향을 바꾸고 그 방향 다음 칸이 평지면 이동
					nextC = curC + 1;
					
					if(nextC < colLen && board[curR][nextC] == '.') {
						
						// 4. 전차가 이동하면, 원래 위치는 다시 평지로 바꾸기
						board[curR][curC] = '.';
						curC = nextC;
					}
					board[curR][curC] = '>';
				}
			}
		}
		
	}
	static void shoot() {
		
		// 바라보는 방향
		char dir = board[curR][curC];
		
		// 바라보는 방향 기준으로 쏘기
		// 3-1. 벽에 충돌하거나 맵 밖으로 나갈 때 까지 직진
		// 물을 만나면 통과한다!
		int nextR = curR;
		int nextC = curC;
		
		if(dir == '^') {
			nextR--;
			while(nextR >= 0 && (board[nextR][curC] == '.' || board[nextR][curC] == '-')) {
				nextR--;
			}
		}
		else if(dir == 'v') {
			nextR++;
			while(nextR < rowLen && (board[nextR][curC] == '.' || board[nextR][curC] == '-')) {
				nextR++;
			}
		}
		else if(dir == '<') {
			nextC--;
			while(nextC >= 0 && (board[curR][nextC] == '.' || board[curR][nextC] == '-')) {
				nextC--;
			}
		}
		else {
			nextC++;
			while(nextC < colLen && (board[curR][nextC] == '.' || board[curR][nextC] == '-')) {
				nextC++;
			}
		}
		
		// 만났던 지점이 벽돌 벽이면 빈칸으로 바꿔야 함
		if(nextR >= 0 && nextC >= 0 && nextR < rowLen && nextC < colLen && board[nextR][nextC] == '*') {
			board[nextR][nextC] = '.';
		}
	}
	
}
