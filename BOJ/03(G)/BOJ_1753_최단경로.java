import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1753. 최단경로
 * 1. 방향그래프가 주어졌을 때, 시작점에서 모든 정점으로 최단경로 구하기
 * 풀이
 * 0. `시작점`에서 `모든 정점`으로 구하면서, 가중치가 자연수기 때문에 다익스트라로 문제를 푼다. 
 * 1. 출발 노드 설정
 * 2. 최단 거리 테이블 초기화 → (모든 노드 무한으로 설정, 자기 자신 노드 0)
 * 3. 방문하지 않은 노드 중 최단 거리가 가장 짧은 노드 선택
 * 4. 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 갱신
 * 
 */
public class BOJ_1753_최단경로 {
	
	static class Node implements Comparable<Node> {
		
		int no;
		int distance;
		
		public Node(int no, int distance) {
			this.no=no;
			this.distance=distance;
		}
		public int compareTo(Node ob) {
			return this.distance - ob.distance;
		}
	}
	static int vertexCount, edgeCount;
	static int startV;
	static List<List<Node>> graph;
	// 시작점에서 모든 정점 간의 거리 배열
	static int[] distance;
	public static void main(String[] args) throws IOException {
		
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for(int idx=0; idx<=vertexCount; idx++) {
			graph.add(new ArrayList<>());
		}
		
		startV = Integer.parseInt(br.readLine());
		
		for(int count=0; count<edgeCount; count++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 단방향
			graph.get(from).add(new Node(to, weight));
		}
		
		// 2. 최단 거리 테이블 초기화 → (모든 노드 무한으로 설정, 자기 자신 노드 0)
		distance = new int[vertexCount+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startV] = 0;
		
		solution();
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int idx=1; idx<vertexCount+1; idx++) {
			if(distance[idx] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(distance[idx]+"\n");
		}
		System.out.print(sb);
	}
	static void solution() {
		
		PriorityQueue<Node> pQ = new PriorityQueue<>();
		
		// 시작점에서 갈 수 있는 정점 넣기
		pQ.offer(new Node(startV, 0));
		
		while(!pQ.isEmpty()) {

			//3. 방문하지 않은 노드 중 최단 거리가 가장 짧은 노드 선택 -> 우선순위큐
			Node cur = pQ.poll();
			if(distance[cur.no] < cur.distance) continue;
			
			//4. 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 테이블 갱신
			for(Node next : graph.get(cur.no)) {
				if(distance[next.no] > distance[cur.no] + next.distance) {
					distance[next.no] = distance[cur.no] + next.distance;
					pQ.offer(new Node(next.no, distance[next.no]));
				}
			}
		}
	}

}
