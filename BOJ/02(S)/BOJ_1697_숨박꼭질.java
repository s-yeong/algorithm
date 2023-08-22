import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1697.숨박꼭질
 * 1. 수빈이가 동생을 찾기 위한 최단 시간 구하기
 * 
 * 풀이
 * 1. 최단 거리를 구하면서, 가중치가 동일하기 때문에 bfs로 푼다.
 * 2. 해당 위치를 또 방문할 수 있기 때문에 방문체크 배열을 이용한다.
 * 3. next에서 end 지점을 확인하기 때문에, 첫 지점이 end 지점인지 확인해야 한다.
 */
public class BOJ_1697_숨박꼭질 {

	static int[] dx = {-1, 1};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 시작점과 끝점이 같으면,
		if(start == end) System.out.println(0);
		else System.out.println(bfs(start, end));
	}
	
	
	static int bfs(int start, int end) {
		
		
		Queue<Integer> Q = new ArrayDeque<>();
		// 수빈이가 이동하는 지점은 최대 100,000
		boolean[] ch = new boolean[100001];
		
		// 시작점 넣고 체크
		Q.offer(start);
		ch[start] = true;
		int time = 0;
		
		while(!Q.isEmpty()) {
			
			int size = Q.size();
			
			while(size --> 0) {
				
				int cur = Q.poll();
				int next;
				
				for(int dir=0; dir<3; dir++) {
					
					// 수빈이가 순간이동 한다면,
					if(dir == 2) next = cur*2;
					// 수빈이가 걷는다면,
					else next = cur + dx[dir];
					
					// 범위 및 방문 체크
					if(next >= 0 && next <= 100000 && !ch[next]) {
						
						// 도착지점이면,
						if(next == end) return time+1;
						
						// 방문 및 큐에 넣기
						ch[next] = true;
						Q.offer(next);
					}
				}
			}
			time++;
		}
		
		return time;
	}
}
