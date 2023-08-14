import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1074. Z
 * 1. Z방향으로 탐색
 * => r행 c열을 몇 번쨰로 방문했는지 출력
 * 
 * 풀이
 * 0. 분할 정복 문제
 * 1. 재귀를 통해 Z방향으로 탐색
 * 2. 종료시점 : size가 1이 되었을 때
 * 3. 1사분면인지 2사분면인지 3사분면인지 4사분면인지 현재 위치를 파악한 다음 그 개수만큼 더해준다.
 */
public class BOJ_1074_Z {
	static int n;
	static int targetRow;
	static int targetCol;
	static int count;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		targetRow = Integer.parseInt(st.nextToken());
		targetCol = Integer.parseInt(st.nextToken());
		count = 0;
		
		// 사이즈
		int size = (int)Math.pow(2, n);
		// r행 c열을 몇 번쨰로 방문했는지 출력
		recur(targetRow, targetCol, size);
		System.out.println(count);
	}
	
	static void recur(int row, int col, int size) {
		
		if(size == 1) return;
		
		// 1사분면
        if(row < size/2 && col < size/2) {
            recur(row, col, size/2);
        }
        // 2사분면
        else if(row < size/2 && col >= size/2) {
            count += (size*size)/4;
            recur(row, col - size / 2, size / 2);
        }
        // 3사분면
        else if(row >= size/2 && col < size/2) {
            count += ((size*size)/4) * 2;
            recur(row - size / 2, col, size / 2);
        }
        // 4사분면
        else {
            count += ((size*size)/4) * 3;
            recur(row - size / 2, col - size / 2, size / 2);
        }
		
	}
}
