import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1233. 사칙연산 유효성 검사
 * 사칙연산을 이진 트리로 표현
 * 1. 사칙연산 : +, -, *, /
 * 2. 양의 정수로만 구성된 임의의 이진 트리
 * => 이 식의 유효성을 검사하기 (계산O : 1, 계산X : 0)
 * 3. 0으로 나누는 경우 고려X
 * 
 * 왼족 서브 트리 (연산) 오른쪽 서브 트리
 * 총 노드의 개수는 200개가 넘지 않고, 루트 정점 번호는 반드시 1 
 * 제대로된 연산을 하려면 말단 노드가 숫자여야함 => 연산자인 경우 체크 
 */
public class SWEA_1223_사칙연산유효성검사 {

	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int test_case=1; test_case<=10; test_case++) {
			
			// 초기화
			int n = Integer.parseInt(br.readLine()); // 각 케이스의 트리가 갖는 정점의 총 수 n
			int answer = 1;
			
			for(int nodeCount = 0; nodeCount<n; nodeCount++) {
				// 각 정점의 정보
				// 알파벳, 왼쪽 자식, 오른쪽 자식의 정점 번호가 차례대로
				
				st = new StringTokenizer(br.readLine());
				int number = Integer.parseInt(st.nextToken());
				
				// 입력을 받았을 때 말단 노드인 경우 연산자가 포함되면 잘못됨
				char op = st.nextToken().charAt(0);
				
				// 더이상 입력이 없으면 말단 노드
				if(st.countTokens() == 0) {
					// 연사자인 경우 잘못됨
					if(op == '*' || op == '/' || op == '+' || op == '-') {
						answer = 0;
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
}
