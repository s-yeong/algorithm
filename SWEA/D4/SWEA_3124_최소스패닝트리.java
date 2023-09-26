import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 3124. 최소 스패닝 트리
 * 1. 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리
 * 2. 입력으로 주어지는 그래프는 하나의 연결 그래프임이 보장
 *	
 * 풀이
 * 1. MST를 크루스칼 알고리즘을 통해 구현한다. -> Union-Find 활용
 * 2. 크루스칼 알고리즘은 간선을 하나씩 선택해서 MST를 찾는 알고리
 * 3. 최초 모든 간선을 가중치에 따라 오름차순으로 정렬
 * 4. 가중치가 가장 낮은 간선부터 선택하면서 트리를 증가시킴면서 정점-1 개의 간선이 선택될때 까지 반복 
 * 5. 비용의 최대값이 100만이기 때문에 100만*10만 = int 범위를 넘어간다. -> long으로 선언 
 */
public class SWEA_3124_최소스패닝트리 {
	
	static int[] parents, ranks;
	static int vertexCount, edgeCount;
	static List<Edge> edgeList;
	
	// 간선 정보
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		public int compareTo(Edge ob) {
			return Integer.compare(this.cost, ob.cost);
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			vertexCount = Integer.parseInt(st.nextToken());
			edgeCount = Integer.parseInt(st.nextToken());
			edgeList = new ArrayList<>();
			
			for(int count=0; count<edgeCount; count++) {
				
				st = new StringTokenizer(br.readLine());
				// A번 정점과 B번 정점이 가중치 C인 간선으로 연결
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				edgeList.add(new Edge(from, to, cost));
			}
			
			// 오름차순 정렬
			Collections.sort(edgeList);
			
			// MST 구하기
			// 정점의 개수 - 1 만큼 간선을 찾으면 된다.
			make();
			long answer = getMST();
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	static long getMST() {
		
		// 비용함
		long costSum = 0;
		
		// MST를 완성하기 위한 간선의 개수
		int mstCount = 0;
		
		for(Edge edge : edgeList) {
			
			// 사이클이 존재하지 않고 합칠 수 있으면,
			if(union(edge.from, edge.to)) {
				// 간선을 선택후 개수 증가시킨다.
				mstCount++;
				// 최소 비용 누적
				costSum += edge.cost;
			}
			// MST 완성시
			if(mstCount == vertexCount - 1) return costSum;
		}
		return costSum;
	}
	
	static void make() {
		
		parents = new int[vertexCount+1];
		ranks = new int[vertexCount+1];
		
		// self-loop
		for(int element=1; element<=vertexCount; element++) {
			parents[element] = element;
		}
	}
	
	static int find(int element) {
		
		// 대표자를 찾았으면,
		if(parents[element] == element) return element;
		return parents[element] = find(parents[element]);	// 경로 단축
	}
	
	// element1, element2가 속한 집합 합치기
	static boolean union(int element1, int element2) {
		
		int e1Parent = find(element1);
		int e2Parent = find(element2);
		
		// 대표자가 같으면 -> 사이클 발생
		if(e1Parent == e2Parent) return false;
		
		// 랭킹 비교
		// e1이 더 높으면, e1에 붙이기
		if(ranks[e1Parent] > ranks[e2Parent]) {
			parents[e2Parent] = e1Parent;
		}
		else parents[e1Parent] = e2Parent;
		
		// 만약 랭크가 같은 경우 e2의 랭크 하나 높여서 e1 들어가게 하기
		if(ranks[e1Parent] == ranks[e2Parent]) {
			ranks[e2Parent]++;
		}
		return true;
	}
}
