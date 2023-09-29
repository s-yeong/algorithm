
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 9205. 맥주 마시면서 걸어가기
 * 1. 집에서 페스티벌까지 갈 수있는지 구하기
 * 2. 맥주 20병 들고 시작, 50미터 마다 한 병씩 마심 (=> 50미터 가려면 한 병 마셔야함)
 * 2.1 최대 20*50 = 1000 미터 갈 수 있음 
 * 3. 편의점 들리면 맥주 살 수 있음
 * 4. 편의점에서 또 이동시 맥주 한 병 마셔야함
 * 
 * 풀이
 * 1. 현재 지점에서 갈 수 있는 위치(편의점)을 구하기
 * 2. 이전 지점(편의점)으로 다시 돌아갈 수 있으니 방문 체크
 * 3. 도착지점 도착시 happy, 못가면 sad
 */
public class BOJ_9205_맥주마시면서걸어가기 {
	
	static int testCase;
	static int storeCount;
	//상근이네 집, 펜타포트 락 페스티벌 좌표
	static int[] house, dest;
	// 편의접 리스트
	static List<int[]> storeList;
	static boolean flag;
	static boolean[] ch;
	// 맥주 한 박스 개수
	static final int BEER_COUNT = 20;
	// 맥주 하나당 최대 거리
	static final int BEER_MAX_DIS = 50;
	
	public static void main(String[] args) throws IOException {
		
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine());
		
		while(testCase-->0) {
			
			flag = false;
			
			storeCount = Integer.parseInt(br.readLine());
			
			//상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표
			
			// 상근이네 집 (x, y) 좌표
			st = new StringTokenizer(br.readLine());
			house = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			// 편의점 (x, y) 좌표
			storeList = new ArrayList<>();
			for(int count=0; count<storeCount; count++) {
				st = new StringTokenizer(br.readLine());
				storeList.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			// 도착지 (x, y) 좌표
			st = new StringTokenizer(br.readLine());
			dest = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			ch = new boolean[storeCount];
			
			recur(house[0], house[1]);
			
			// 도착했었으면,
			if(flag) { 
				sb.append("happy").append("\n");
			} else sb.append("sad").append("\n");
		}
		System.out.println(sb);
	}
	
	static void recur(int curX, int curY) {
		
		// 갈수 있는 편의점 지점 찾기
		for(int storeIdx=0; storeIdx<storeCount; storeIdx++) {
			
			// 방문한 편의점이면, skip
			if(ch[storeIdx]) continue;
			
			int[] store = storeList.get(storeIdx);
			
			// 두 좌표 거리
			int dis = Math.abs(store[0] - curX) + Math.abs(store[1] - curY);
			
			// 현재 편의점으로 이동 가능하면, 
			if(BEER_COUNT * BEER_MAX_DIS >= dis) {
				
				// 방문 체크
				ch[storeIdx] = true;
				
				recur(store[0], store[1]);
				
				// 도차직점 갔었으면, 리턴
				if(flag) return;
			}
		}
		
		// 도착지점 갈 수 있으면, 
		int dis = Math.abs(dest[0] - curX) + Math.abs(dest[1] - curY);
		if(BEER_COUNT * BEER_MAX_DIS >= dis) {
			flag = true;
		}
	}
}
