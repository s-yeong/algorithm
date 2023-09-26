import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 10971. 외판원 순회2
 * 1. 1~N번까지 번호가 매겨진 도시들, 그 도시들 사이에 길이 있다. (없을 수도 있음)
 * 2. 외판원이 어느 한 도시에서 출발해 N개의 도시를 `모두` 거쳐 다시 원래의 도시로  돌아오는 순회 여행 경로 계획하기
 * 3. 단, `한 번 갔던 도시`로는 다시 갈 수 없다
 * => 가장 적은 비용을 들이기!
 * 
 * 1. n이 최대가 10이므로 모든 경우의 구하면 n! = 3628800이므로 완전탐색으로 풀 수 있다.
 * 2. 단, `한 번 갔던 도시`로는 다시 갈 수 없다 => 방문 체크 배열
 * 3. 마지막 지점에도 이동가능한지 고려해야한다!
 * 
 */
public class BOJ_10971_외판원순회2 {
	
	static int cityCount;
	static int[][] cityMatrix;
	static boolean[] ch;
	static int answer;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cityCount = Integer.parseInt(br.readLine());
		cityMatrix = new int[cityCount][cityCount];
		ch = new boolean[cityCount];
		
		for(int row=0; row<cityCount; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int col=0; col<cityCount; col++) {
				cityMatrix[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		
		// 시작 정점 = 도착정점
		for(int startCity = 0; startCity<cityCount; startCity++) {
			ch[startCity] = true;
			recur(0, 0, startCity, startCity);
			ch[startCity] = false;
		}
		
		System.out.println(answer);
	}
	
	static void recur(int depth, int sum, int curCity, int startCity) {
		
		// 최소값 넘어가면 반환
		if(sum > answer) return;
		
		// 모든 지점 방문 후
		if(depth == cityCount-1) {
			
			// 다시 시작 지점으로 갈 수 없으면, 리턴
			if(cityMatrix[curCity][startCity] == 0) return;
			
			// 도착점 방문 (=시작 정점)
			sum += cityMatrix[curCity][startCity];
			answer = Math.min(answer, sum);
			return;
		}
		
		for(int nextCity=0; nextCity<cityCount; nextCity++) {
			
			// 갈 수 없으면,
			if(ch[nextCity] || cityMatrix[curCity][nextCity] == 0) continue;
			
			ch[nextCity] = true;
			recur(depth+1, cityMatrix[curCity][nextCity] + sum, nextCity, startCity);
			ch[nextCity] = false;
		}
	}

}
