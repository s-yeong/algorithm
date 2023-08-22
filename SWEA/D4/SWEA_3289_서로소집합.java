import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3289. 서로소 집합
 * 1. {1} ~ {n} : n개의 집합
 * 2. 합집합 연산과 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산 수행
 * => union-find 구현
 *
 */
public class SWEA_3289_서로소집합 {

	// 부모 배열
	static int[] parentArr;
	static int[] rankArr;
	static int n, inputCount;
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			sb.append("#").append(testCase).append(" ");
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			inputCount = Integer.parseInt(st.nextToken());
			
			make();
			
			while(inputCount --> 0) {
				
				st= new StringTokenizer(br.readLine());
				
				int type = Integer.parseInt(st.nextToken());
				int element1 = Integer.parseInt(st.nextToken());
				int element2 = Integer.parseInt(st.nextToken());
				
				// 0: 합집합
				if(type == 0) {
					union(element1, element2);
				}
				// 1: 두 원소가 같은 집합에 포함되어 있는지
				else {
					int e1Parent = find(element1);
					int e2Parent = find(element2);
					if(e1Parent == e2Parent) sb.append("1");
					else sb.append("0");
				}
				
			}
			
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	// 최초 self-loop 만들기
	static void make() {
		rankArr = new int[n+1];
		parentArr = new int[n+1];
		for(int num=1; num<=n; num++) {
			parentArr[num] = num;
		}
	}
	
	// element가 속하는 집합의 대표값(루트 노드) 반환
	static int find(int element) {
		
		// 현재 element가 부모라면, 
		if(element == parentArr[element]) return element;
		else return parentArr[element] = find(parentArr[element]);	// 경로 압축
	}
	
	// element1 속한 집합과 element2 속한 집합 합친다
	static void union(int element1, int element2) {
		
		// 각 원소의 대표값 찾기
		int e1Parent = find(element1);
		int e2Parent = find(element2);
		
		// 같으면 동일한 집합
		if(e1Parent == e2Parent) return;
		
		// 랭킹 비교
		// e1의 부모가 랭킹이 높으면, e2 속한 집합이 그 밑에 배치
		if(rankArr[e1Parent] > rankArr[e2Parent]) {
			parentArr[e2Parent] = e1Parent;
		}
		else parentArr[e1Parent] = e2Parent;
		
		// 랭크가 같은 경우, e2의 랭크 하나 높여서 e1이 들어가게 하기
		if(rankArr[e1Parent] == rankArr[e2Parent]) {
			rankArr[e2Parent]++;
		}
	}

}
