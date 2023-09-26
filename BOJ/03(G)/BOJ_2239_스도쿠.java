import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 2239. 스도쿠 
 * 1. 빈칸에 해당하는 부분만 해당하는 숫자를 1부터 9까지 대입하면서 스도쿠가 되는지 확인한다. 
 * 2. 이를 위해 빈칸인 경우, 리스트에 담는다. 
 * 3. 재귀함수를 통해 빈칸의 개수 만큼 채웠을 때, 출력 후 바로 종료한다.
 */
public class Main {

	static int[][] board;
	static final int boardLen = 9;
	static List<int[]> blankList;
	static int blankCount = 0;
	static StringBuilder sb;
	static boolean flag;

	public static void main(String[] args) throws IOException {

		// 입출력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		board = new int[boardLen][boardLen];
		blankList = new ArrayList<>();
		for (int row = 0; row < boardLen; row++) {
			String[] input = br.readLine().trim().split("");
			for (int col = 0; col < boardLen; col++) {
				board[row][col] = Integer.parseInt(input[col]);
				// 빈칸인 경우,
				if (board[row][col] == 0) {
					blankList.add(new int[] { row, col });
				}
			}
		}

		flag = false;
		blankCount = blankList.size();
		
		// 재귀함수 호출
		recur(0);
	}

	static void recur(int depth) {

		// 모두 구했으면, 출력 후 시스템 종료
		if (depth == blankCount) {
			for (int row = 0; row < boardLen; row++) {
				for (int col = 0; col < boardLen; col++) {
					sb.append(board[row][col]);
				}
				sb.append("\n");
			}
			System.out.print(sb);
			flag = true;
			return;
		}

		int[] pos = blankList.get(depth);

		int row = pos[0];
		int col = pos[1];

		// 해당 칸이 0인 경우만 숫자를 둔다.
		for (int num = 1; num <= 9; num++) {

			// 둘 수 없으면 skip
			if (!isPos(row, col, num))
				continue;

			// 두기
			board[row][col] = num;

			recur(depth + 1);
			if(flag) return;
			
			// 되돌리기
			board[row][col] = 0;
		}
	}


	// 현재 위치에 `num`을 둘 수 있는지 확인하는 메서드
	static boolean isPos(int curRow, int curCol, int num) {

		// 해당 위치 기준으로

		// 행 중복 확인
		// 열 중복 확인
		for (int cur = 0; cur < 9; cur++) {
			// `num`이 이미 존재하는 경우 둘 수 없다.
			if (board[curRow][cur] == num || board[cur][curCol] == num)
				return false;
		}

		// 3x3 사각형 중복 확인
		int startRow = (curRow / 3) * 3;
		int startCol = (curCol / 3) * 3;

		for (int row = startRow; row < startRow + 3; row++) {
			for (int col = startCol; col < startCol + 3; col++) {
				// `num`이 이미 존재하는 경우 둘 수 없다.
				if (board[row][col] == num)
					return false;
			}
		}

		// 둘 수 있으면 -> true
		return true;
	}

}
