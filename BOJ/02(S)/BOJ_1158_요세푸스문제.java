import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1158. 요세푸스 문제
 * 1. 1부터 n번까지 n명의 사람이 원을 이루면서 앉아 있다.
 * 2. 순서대로 k번째 사람을 제거한다.
 * 3. n명의 사람이 모두 제거될 때 까지 계속하고 제거되는 순서대로 출력한다.
 *	
 * "순서대로" k번째 사람을 제거한다 => 큐
 * 큐에서 넣고 빼기를 반복하면서 k번째일 때 안넣고 출력한다.
 */
public class BOJ_1158_요세푸스문제 {

	
	public static void main(String[] args) throws IOException{
		
		// 입력.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 출력.
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		Queue<Integer> Q = new ArrayDeque<>();
		
		for(int num=1; num<=n; num++) Q.offer(num);
		int count = 0;
		
		while(!Q.isEmpty()) {
			
			// 큐에서 넣고 빼기 카운트
			count++;
			
			if(count != k) Q.offer(Q.poll());
			else {
				// k번쨰 카운트 한 경우
				sb.append(Q.poll());
				
				// 사이즈 0인 경우 마지막에 ">' 출력
				if(Q.size() == 0) {
					sb.append(">");
				} else sb.append(", ");
				
				// 다시 처음부터 카운트
				count = 0;
			}
		}
		System.out.print(sb);
	}

}
