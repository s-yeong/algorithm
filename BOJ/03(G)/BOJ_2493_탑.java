import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 2493. 탑
 * 1. N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향대로 차례대로 세운다.
 * 2. 각 탑의 꼭대기에 레이저 송신기를 설치 + 레이저 신호 수신하는 장치 설치
 * 3. 모든 탑의 레이저 송신기(꼭대기)는 레이저 신호를 지표면과 평행하게 "수평 직선의 왼쪽 방향"으로 발사 
 * 4. 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신 가능
 * => 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지 구하기
 * 
 * 조건
 * 1. N 최대 50만, 탑들의 높이 최대 1억
 * 2. 수신하는 탑 존재안하면 '0' 출력
 * 
 * 문제 풀이
 * 각각의 탑은 자신의 왼쪽에 자기 보다 큰 숫자가 탑을 수신한다.
 * n이 50만이기 때문에 이중 for문으로 풀 수없다. O(nlon), O(n)으로 풀 방법을 생각하자.
 * 
 * "왼쪽"에 있는 데이터를 보기 때문에 데이터를 넣는 과정에서 바로 직전의 데이터를 확인하고, 직직전의 데이터를 확인하고.. => LIFO 
 * => 해당하는 값과 인덱스 번호를 가지고 있는 배열을 만들어서, 스택에서 자신보다 큰 높이를 계속 찾아 나간다.
 * 이 때 스택에서 자신 보다 높이가 가 더 크다면 해당하는 인덱스를 저장한다.
 * 
 */
public class BOJ_2493_탑 {

	public static void main(String[] args) throws IOException {
		// 입력.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		
		// 정답이 들어갈 배열
		int[] answerArr = new int[n+1];
		
		// 탑의 높이 : 0, 탑의 인덱스 : 1 
		Stack<int[]> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for(int idx=1; idx<=n; idx++) {
			
			// 현재 타워 높이
			int curTower = Integer.parseInt(st.nextToken());
			
			// 비어있는게 아니라면,
			while(!stack.isEmpty()) {
				
				int[] temp = stack.peek();
				
				// 현재 타워 보다 크거나 같은지 확인
				if(temp[0] >= curTower) {
					// 크다면, 해당 타워가 수신
					answerArr[idx] = temp[1];
					break;
				}
				// 크지 않다면, 그다음 타워를 확인 해야 하기
				else {
					stack.pop();
				}
			}
			
			// 현재 탑을 스택에 푸쉬한다.
			stack.push(new int[] {curTower, idx});
		}
		
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int idx=1; idx<=n; idx++) {
			sb.append(answerArr[idx]).append(" ");
		}
		System.out.print(sb);
		
	}

}
