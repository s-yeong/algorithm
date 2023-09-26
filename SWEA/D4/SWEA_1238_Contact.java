import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1238. [S/W 문제해결 기본] 10일차 - Contact
 * 1. 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구하기
 * 
 * 풀이
 * 1. BFS를 통해 정점을 탐색하며 마지막으로 탐색한 지점에서 최대값 구하기 
 * 2. 마지막으로 탐색한 지점에서 최대값을 찾아야 하기 떄문에, 다음 정점이 있는 경우를 flag를 통해 관리해서
 * 3. 다음 정점이 있는 경우 해당 정점에서 최대값을 구한다.
 */
public class SWEA_1238_Contact {
	
	// bfs 탐색 그래프
	static List<List<Integer>> graph;
	//정답 후보 리스트 (최대값 찾기 위해)
	static List<Integer> answerList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = 10;
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			
			// 그래프 초기화
			graph = new ArrayList<>();
			// 최대 정점 100개
			for(int idx=0; idx<=100; idx++) graph.add(new ArrayList<>());
			
			// 데이터 길이
			int inputLength = Integer.parseInt(st.nextToken()) / 2;
			// 시작점
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			while(inputLength --> 0) {
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				// 단방향
				graph.get(from).add(to);
			}
			
			answerList = new ArrayList<>();
			
			// BFS 탐색
			int answer = bfs(start);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	static int bfs(int start) {
		
		int answer = 0;
		
		Queue<Integer> Q = new ArrayDeque<>();
		// 방문 배열
		boolean[] ch = new boolean[101];
		
		// 시작 지점 체크
		Q.offer(start);
		ch[start] = true;
		
		while(!Q.isEmpty()) {
			
			int size = Q.size();
			// 다음 정점이 있는지,
			boolean flag = false;
			answerList.clear();
			
			while(size --> 0) {
				
				// 현재 정점
				int cur = Q.poll();
				
				// 다음 정점
				for(int next : graph.get(cur)) {
					// 이미 방문했으면 skip
					if(ch[next]) continue;
					
					// 다음 지점이 있으면,
					flag = true;
					
					// 정답 후보에 넣고 방문 체크 및 큐에 넣기
					answerList.add(next);
					ch[next] = true;
					Q.offer(next);
				}
			}
			
			// 다음 탐색 지점이 있었으면,
			// 정답 후보 리스트에서 최대값 찾기
			if(flag) {
				answer = 0;
				for(int num : answerList) {
					answer = Math.max(answer, num);
				}
			}
		}
		
		return answer;
	}

}
