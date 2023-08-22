import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 13023. ABCDE
 * 1. A, B, C, D, E 친구 관계가 맞는지 확인
 * 1-1. A <-> B <-> C <-> D <-> E 관계
 * 
 * 풀이
 * 1. 정점 리스트로 그래프를 나타내어 DFS를 통해 친구 관계가 되는지 찾는다.
 * 2. 친구 관계는 양방향 관계이다.
 * 3. 친구 관계는 결국 깊이를 4만큼 갈 수 있는지 확인하는 거다.
 */
public class BOJ_13023_ABCDE {

	static int personCount, frinedCount;
	static List<List<Integer>> graph;
	static boolean[] ch;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		personCount = Integer.parseInt(st.nextToken());
		frinedCount = Integer.parseInt(st.nextToken());
		
		// friend 수 만큼 List 생성 (연결 리스트)
		graph = new ArrayList<>();
		for(int count=0; count<personCount; count++) {
			graph.add(new ArrayList<>());
		}
		
		for(int count=0; count<frinedCount; count++) {
			
			st= new StringTokenizer(br.readLine());
			int friend1 = Integer.parseInt(st.nextToken());
			int friend2 = Integer.parseInt(st.nextToken());
			
			// frend1, frend2 양방향 연결
			graph.get(friend1).add(friend2);
			graph.get(friend2).add(friend1);
		}
		
		// 초기화
		ch = new boolean[personCount];
		answer = 0;
		
		for(int idx=0; idx<personCount; idx++) {
			// 해당 idx에서 갈 수 있는지,
			dfs(idx, 0);
			if(answer == 1) break;
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int cur, int cnt) {
		
		// 친구 관계 확인 됐으면 반환
		if(answer == 1) return;
		
		// 방문 체크
		ch[cur] = true;
		
		// 친구 관계가 맞으면,
		if(cnt == 4) {
			answer = 1;
			return;
		}
		
		for(int next : graph.get(cur)) {
			if(!ch[next]) {
				dfs(next, cnt+1);
			}
		}
		
		// 방문 채크 해제
		ch[cur] = false;
	}
}
