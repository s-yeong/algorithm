import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 9663. N-Queen
 * 1. n x n 체스판에 퀸 n개를 놓는 문제
 * 2. 퀸을 놓는 방법의 수 구하기
 * 
 * 풀이
 * 1. 퀸을 놓으면, 해당 가로, 세로, 대각선에 다른 퀸을 놓을 수 없다.
 * 2. row 기준으로 퀸을 놓으면서 가로, 세로, 대각선 체크 배열에 체크를 하고
 * 3. 퀸을 놓을 수 있는 경우에만 다음 row로 넘어간다.
 * 4. row의 끝까지 왔다는 것은 n개의 퀸을 놓았다는 것이기 때문에 count 해준다. 
 *
 */
public class BOJ_9663_NQueen {

	static int answer;
	static int n;
	static boolean[] chCol;
	static boolean[] chPlus;	// '/' 형태의 대각선 => row+col이 동일
	static boolean[] chMinus;	// '\' 형태의 대각선 => row-col이 동일
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		chCol = new boolean[n+1];
		chPlus = new boolean[2*n+1]; // 최대 n+n
		chMinus = new boolean[2*n];	// 최대 n-1 + n(음수 방지)
		answer = 0;
		recur(1);
		System.out.println(answer);
	}
	
	static void recur(int row) {
		
		// n개의 퀸을 놓았을 때
		if(row == n+1) {
			answer++;
		}
		else {
			
			// 해당 row에 놓을 수 있는 col을 찾는다.
			for(int col=1; col<=n; col++) {
				// 놓을 수 있으면
				if(!chCol[col] && !chPlus[row+col] && !chMinus[row-col+n]) {
					
					// 해당 지점을 체크 해준다.
					chCol[col] = true;
					chPlus[row+col] = true;
					chMinus[row-col+n] = true;
					
					recur(row+1);
					
					// 해당 지점 체크 해제
					chCol[col] = false;
					chPlus[row+col] = false;
					chMinus[row-col+n] = false;
				}
			}
		}
	}

}
