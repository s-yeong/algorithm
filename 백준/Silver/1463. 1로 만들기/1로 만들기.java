import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1463. 1로 만들기
 * 정수 연산 3가지
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 * => 연산을 사용하는 횟수의 최솟값 구하기 
 *
 * 풀이
 * 1. 그래프로 생각하여 최단거리 구하는 문제로 풀 수있다. (비용이 동일)
 * 2. 동일한 수가 반복할 수 있으니 수에 대한 체크 배열이 필요
 * 
 */
public class Main {
	
	static boolean[] ch;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		System.out.println(bfs(num));
	}
	
	static int bfs(int num) {
		
		// 10^6 = 1000000
		ch = new boolean[1000001];
		
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(num);
		ch[num] = true;
		int Level = 0;
		
		
		while(!Q.isEmpty()) {
			
			int len = Q.size();
			while(len --> 0) {
				
				int X = Q.poll();	
				if(X == 1) return Level;
				
				// 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
				if(X % 3 == 0 && !ch[X / 3]) {
					ch[X / 3] = true;
					Q.offer(X / 3);
				}
				
				// 2. X가 2로 나누어 떨어지면, 2로 나눈다.
				if(X % 2 == 0 && !ch[X / 2]) {
					ch[X / 2] = true;
					Q.offer(X / 2);
				}
				
				// 3. 1을 뺀다.
				if(!ch[X-1]) {
					ch[X-1] = true;
					Q.offer(X - 1);
				}
			}
			Level++;
		}
		return -1;
	}
}
