import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1992. 쿼드트리
 * 1. 흰 점을 나타내는 0, 검은 점을 나타내는 1
 * 2. 모두 0으로만 되어 있으면 -> 0, 모두 1로만 되어 있으면 -> 1
 * 3. 0과1이 섞여 있으면 4개로 쪼개면서, 괄호 안에 묶어서 표현
 *
 * 풀이
 * 1. 0과 1이 섞여 있으면 4개로 쪼갠다 -> 분할 정복 문제
 * 2. 처음부터 탐색하면서 0과 1이 섞여 있는지 판단해서, 섞여 있으면 분할해서 재귀 호출하고, 아니면 값을 입력한다.
 * 3. 분할해서 재귀 호출 전과 후를 괄호로 묶는다.
 */
public class BOJ_1922_쿼드트리 {

	static int n;
	static int[][] board;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		sb = new StringBuilder();
		
		for(int row=0; row<n; row++) {
			char[] input = br.readLine().toCharArray();
			for(int col=0; col<n; col++) {
				board[row][col] = input[col] - '0';
			}
		}
		
		recur(0,0,n);
		System.out.println(sb);
	}
	static void recur(int row, int col, int size) {
		
		// size가 1이면 해당 개수 더한다.
		// 기저 조건
		if(size == 1) {
			sb.append(board[row][col]);
		}
		
		// 0인지, 1인지 판단한다. 섞여 있으면 -1 반환
		else {
			
			int num = check(row, col, size);
			
			// 섞여 있으면
			if(num == -1) {
				// 괄호 추가후 재귀 호출
				sb.append("(");
				recur(row, col, size/2);
				recur(row, col+size/2, size/2);
				recur(row+size/2, col, size/2);
				recur(row+size/2, col+size/2, size/2);
				sb.append(")");
			}
			// 섞여 있지 않으면 추가
			else {
				sb.append(board[row][col]);
			}
		}
		
	}
	
	// 0인지, 1인지 판단한다. 섞여 있으면 -1 반환
	static int check(int startRow, int startCol, int size) {
		
		// 초기 숫자
		int num = board[startRow][startCol];
		
		for(int row=startRow; row<startRow+size; row++) {
			for(int col=startCol; col<startCol+size; col++) {
				// 초기 숫자와 다르면 0과 1이 섞여있다 -> -1 리턴
				if(num != board[row][col]) return -1;
			}
		}
		
		return num;
	}

}
