import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 17471. 게리맨더링
 * 1. 구역은 1 ~ N번까지 번호가 매겨져 있다.
 * 2. 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다.
 * 2. 선거구는 구역을 `적어도 하나` 포함해야 하고, 한 선거구에 포함되어 있는 구역은 `모두 연결`되어 있어야 한다.
 * 3. 연결 : 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때
 * => 두 선거구의 인구 차이의 최소값 구하기
 * 
 * 풀이
 * 0. N은 최대 10, 구역 인구 수는 최대 100
 * 1. 두 구역으로 나눠야 하기 때문에 부분집합을 통해 두 나눈다. -> O(2^10)
 * 2. 이 떄 각각의 부분집합이 `모두 연결`되어있는지 확인 해야한다. 
 * 3. 연결되어 있다는 것은 한 임의의 정점에서 모든 구역을 탐색할 수 있다는 이야기다. -> DFS를 통해 두 부분집합이 각각 해당 구역을 방문하는지 체크한다. 
 *
 */
public class BOJ_17471_게리맨더링 {

	// 지역 수
	static int regionLen;
	// 지역 인구 수
	static int[] regionPopulation;
	// 그래프
	static List<List<Integer>> graph;
	// 부분집합 배열(true, false로 두 부분집합 구분)
	static boolean[] subset;
	// dfs 방문 배열
	static boolean[] ch;
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		regionLen = Integer.parseInt(br.readLine());
		regionPopulation = new int[regionLen+1];
		
		// 구역의 `인구`가 1번 구역부터 N번 구역까지 순서대로 주어진다.
		st = new StringTokenizer(br.readLine());
		for(int idx=1; idx<=regionLen; idx++) {
			regionPopulation[idx] = Integer.parseInt(st.nextToken());
		}
		
		// DFS 탐색을 위한 그래프
		graph = new ArrayList<>();
		for(int idx=0; idx<=regionLen; idx++) {
			graph.add(new ArrayList<>());
		}
		
		for(int idx=1; idx<=regionLen; idx++) {
			
			st = new StringTokenizer(br.readLine());
			
			// 각 정보의 첫 번째 정수는 그 구역과 인접한 구역의 수
			int adjCount = Integer.parseInt(st.nextToken());
			
			while(adjCount --> 0) {
				
				// 인접한 구역의 번호
				int adjRegion = Integer.parseInt(st.nextToken());
				
				// 양방향
				graph.get(idx).add(adjRegion);
				graph.get(adjRegion).add(idx);
			}
		}
		
		// 초기화
		answer = Integer.MAX_VALUE;
		subset = new boolean[regionLen+1];
		
		recur(1);
		
		// 정답이 없는 경우 갱신이 안됏으므로, MAX_VALUE 값이다 -> -1 출력
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	
	// 부분집합 재귀
	static void recur(int depth) {
		
		// 부분집합 완료시,
		if(depth == regionLen+1) {
			
			// 적어도 하나 포함하고 있는지 계산
			// 모두 연결되어 있는지 계산
			int diff = calculate();
			
			// 해당 조건을 만족하지 못했을 때 -1을 반환했으므로, -1이 아니여야 최소값 갱신
			if(diff != -1) {
				answer = Math.min(answer, diff);
			}
		}
		else {
			subset[depth] = true;
			recur(depth+1);
			subset[depth] = false;
			recur(depth+1);
		}
	}
	
	static int calculate() {
		
		// 두 부분 집합으로 구분
		List<Integer> setList1 = new ArrayList<>();
		List<Integer> setList2 = new ArrayList<>();
			
		// 각 부분 집합의 인구수 합
		int populationSum1 = 0;
		int populationSum2 = 0;
		
		// true -> setList1, false -> setList2
		for(int idx=1; idx<=regionLen; idx++) {
			// true는 setList1으로 포함
			if(subset[idx]) {
				setList1.add(idx);
				populationSum1 += regionPopulation[idx];
			}
			// false는 setList2로 포함
			else {
				setList2.add(idx);
				populationSum2 += regionPopulation[idx];
			}
		}
		
		// 선거구는 구역을 `적어도 하나` 포함 해야 하기 떄문에 -1 리턴
		if(setList1.size() == 0 || setList2.size() == 0) return -1;
		
		// 각각의 부분집합이 `모두 연결`되어있는지 확인 -> dfs
		ch = new boolean[regionLen+1];
		dfs(setList1.get(0), setList1, true);
		// 방문했던 점들은 모두 리스트에서 제거했으므로 사이즈가 0이 아니면 -1 리턴
		if(setList1.size() > 0) return -1;
		
		// 각각의 부분집합이 `모두 연결`되어있는지 확인 -> dfs
		ch = new boolean[regionLen+1];
		dfs(setList2.get(0), setList2, false);
		// 방문했던 점들은 모두 리스트에서 제거했으므로 사이즈가 0이 아니면 -1 리턴
		if(setList2.size() > 0) return -1;
		
		
		// 둘다 연결이 된 상태면, 인구 수 합 차이 반환
		return Math.abs(populationSum1 - populationSum2);
	}
	
	
	static void dfs(int cur, List<Integer> setList, boolean flag) {
		
		// 방문 체크
		ch[cur]= true;
		setList.remove(new Integer(cur));
		
		for(int next : graph.get(cur)) {
			
			// true는 setList1으로 포함했으므로 해당 조건이 만족해야 함
			if(flag && subset[next] && !ch[next]) {
				dfs(next, setList, flag);
			}
			// false는 setList2으로 포함했으므로 해당 조건이 만족해야 함
			else if(!flag && !subset[next] && !ch[next]) {
				dfs(next, setList, flag);
			}
		}
	}
	
	
}
