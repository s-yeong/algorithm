import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1260. DFS와 BFS
 * 1. 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성
 * 2. 방문할 수 있는 정점이 여러 개인 경우에는 `정점 번호가 작은 것`을 먼저 방문
 * 3. 더 이상 방문할 수 있는 점이 없는 경우 종료
 *	
 * 풀이
 * 1. 정점 번호가 작은 것 부터 먼저 방문해야 하기 때문에 인접 행렬로 작은 것 부터 탐색한다. 
 * 2. dfs와 bfs를 각각 구현하여 출력한다.
 */
public class BOJ_1260_DFS와BFS {
	
	static int vertexCount;	// 정점 수
	static int edgeCount;	// 간선 수
	// 출력
	static StringBuilder sb;
	
	// 그래프(해당 정점과 연결된 간선들)
	static int[][] graph;
	
	// 방문배열
	static boolean[] ch;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		graph = new int[vertexCount+1][vertexCount+1];
		ch = new boolean[vertexCount+1];
		
		// 탐색을 시작할 정점의 번호
		int startV = Integer.parseInt(st.nextToken());
		
		// 간선이 연결하는 두 정점의 번호
		for(int count=0; count<edgeCount; count++) {
			
			st = new StringTokenizer(br.readLine());
			
			// 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다.
			// 간선은 양방향이다.
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			graph[v1][v2] = 1;
			graph[v2][v1] = 1;
		}
		
		dfs(startV);
		sb.append("\n");	// dfs, bfs 출력 구분
		bfs(startV);
		System.out.print(sb);
	}
	
	static void dfs(int v) {
		
		ch[v] = true;
		sb.append(v).append(" ");
		
		for(int nextV=1; nextV<=vertexCount; nextV++) {
			
			// 해당 정점과 연결 되어 있고, 그 정점을 방문 안했으면,
			if(graph[v][nextV] == 1 && !ch[nextV]) {
				dfs(nextV);
			}
		}
	}
	
	static void bfs(int startV) {
		
		Queue<Integer> Q = new ArrayDeque<>();
		
		// 체크 배열 초기화
		ch = new boolean[vertexCount+1];
		
		// 시작 정점 넣고 체크
		Q.offer(startV);
		ch[startV] = true;
		
		while(!Q.isEmpty()) {
			
			int curV = Q.poll();
			sb.append(curV).append(" ");
			
			for(int nextV=1; nextV<=vertexCount; nextV++) {
				
				// 해당 정점과 연결 되어 있고, 그 정점을 방문 안했으면,
				if(graph[curV][nextV] == 1 && !ch[nextV]) {
					// 체크 후 큐에 넣기
					ch[nextV] = true;
					Q.offer(nextV);
				}
			}
		}
	}
	

}
