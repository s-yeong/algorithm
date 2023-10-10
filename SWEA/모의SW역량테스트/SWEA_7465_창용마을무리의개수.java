import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 7465. 창용 마을 무리의 개수
 * => 창용 마을에 몇 개의 무리가 존재하는지 계산
 * 1. 무리 = 서로 아는 관계거나 몇 사람을 거쳐서 아는 관계
 * 
 * 풀이
 * 1. 그래프로 추상화하여 dfs를 통해 몇 번 방문하는지 개수를 센다.
 * 2. 사람 수 가 정점, 무방향 그래프
 *
 */
public class SWEA_7465_창용마을무리의개수 {
	
	static int peopleCount, relationCount;
	static boolean[] ch;
	static List<List<Integer>> graph;
	
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			peopleCount = Integer.parseInt(st.nextToken());
			relationCount = Integer.parseInt(st.nextToken());
			
			// 그래프 초기화
			graph = new ArrayList<>();
			for(int count=0; count<=peopleCount; count++) {
				graph.add(new ArrayList<>());
			}
			
			for(int count=0; count<relationCount; count++) {
				st = new StringTokenizer(br.readLine());
				
				// 서로 알고 있는 두사람의 번호
				int numberA = Integer.parseInt(st.nextToken());
				int numberB = Integer.parseInt(st.nextToken());
				
				// 무방향
				graph.get(numberA).add(numberB);
				graph.get(numberB).add(numberA);
			}
			
			// 방문 배열 초기화
			ch = new boolean[peopleCount+1];
			
			int answer = 0;
			
			// dfs를 통해 몇 번 방문하는지 개수를 센다.
			for(int num=1; num<=peopleCount; num++) {
				if(!ch[num]) {
					answer++;
					dfs(num);
				}
			}
			
			sb.append("#" + testCase + " " + answer + "\n");
		}
		System.out.print(sb);
		
		
	}
	
	static void dfs(int cur) {
		// 방문 체크
		ch[cur] = true;
		
		// 현재 노드에서 갈 수 있는 노드 찾기
		for(int next : graph.get(cur)) {
			if(ch[next]) continue;
			dfs(next);
		}
	}
}
