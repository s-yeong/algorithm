

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2252. 줄 세우기
 * 1. 두 학생의 키를 비교결과를 통해 줄 세우기
 * 
 * 풀이
 * 1. 진입 차수가 0인 노드(시작점)를 큐에 모두 넣는다.
 * 2. 큐에서 진입 차수가 0인 노드를 꺼내어 출력 후 자신과 인접한 노드의 간선을 제거한다.
 * 3. 간선 제거 후 진입 차수가 0이 된 노드를 큐에 넣는다. 
 */
public class BOJ_2252_줄세우기 {
	
	static StringBuilder sb;
	static int studentCount;
	static List<List<Integer>> graph;
	// 진입 차수
	static int[] degree;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		studentCount = Integer.parseInt(st.nextToken());
		int inputCount = Integer.parseInt(st.nextToken());
		
		// 노드의 개수 만큼 List 생성
		graph = new ArrayList<>();
		for(int num=0; num<=studentCount; num++) {
			graph.add(new ArrayList<>());
		}
		degree = new int[studentCount+1];
		
		while(inputCount --> 0) {
			
			st = new StringTokenizer(br.readLine());
			
			// 학생 A가 학생 B의 앞에 서야 한다.
			int studentA = Integer.parseInt(st.nextToken());
			int studentB = Integer.parseInt(st.nextToken());
			
			// 진입 차수 증가
			degree[studentB]++;
			// A와 B 연결
			graph.get(studentA).add(studentB);
		}
		
		solution();
		System.out.print(sb);
	}
	
	static void solution() {
		
		Queue<Integer> Q = new ArrayDeque<>();
		
		// 1. 진입차수 0인 노드를 큐에 모두 넣기
		for(int num=1; num<=studentCount; num++) {
			if(degree[num] == 0) {
				Q.offer(num);
			}
		}
		
		while(!Q.isEmpty()) {
			
			int cur = Q.poll();
			sb.append(cur).append(" ");
			
			// 자신과 인접한 노드의 간선을 제거
			for(int next : graph.get(cur)) {
				
				degree[next]--;
				
				// 진입차수 0이 되면 넣기
				if(degree[next] == 0) Q.offer(next);
			}
		}
	}

}
