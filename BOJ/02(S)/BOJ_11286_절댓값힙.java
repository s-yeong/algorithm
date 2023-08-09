import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 11286. 절댓값 힙
 * 1. 배열에 정수 x를 넣는다 (x!=0)
 * 2. 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
 * 3. 절댓값이 가장 작은 값이 여러 개일 때는, 가장 작은 수를 출력, 그 값을 배열에서 제거
 * 
 * 입력
 * 1. x가 0이 아니다 => 배열에 x라는 값을 넣기
 * 2. x가 0이다 => 배열에서 절대값이 가장 작은 값 출력
 * 3. 비어있는 경우 0 출력 
 * 
 * => 우선순위 큐를 이용 + compare 메서드 재정의 하기
 * 
 */
public class BOJ_11286_절댓값힙 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 출력
		StringBuilder sb = new StringBuilder();
		
		// 최소 힙 우선순위 큐
		PriorityQueue<Integer> pQ = new PriorityQueue<>((num1, num2) -> {
			
			// 절대값
			int absNum1 = Math.abs(num1);
			int absNum2 = Math.abs(num2);
			
			// 같으면, 작은 값
			if(absNum1 == absNum2) {
				return Integer.compare(num1, num2);
			}
			else {
				// 다르면 절대값 기준으로 작은 값
				return Integer.compare(absNum1, absNum2);
			}
		});
		
		// 연산의 개수
		int n = Integer.parseInt(br.readLine());
		
		while(n --> 0) {
			
			int num = Integer.parseInt(br.readLine());
			
			if(num != 0) {
				pQ.offer(num);
			}
			// 0인 경우 출력
			else {
				// 비어있으면 0 출력
				if(pQ.isEmpty()) sb.append("0").append("\n");
				else sb.append(pQ.poll()).append("\n");
			}
		}
		System.out.print(sb);
	}
}
