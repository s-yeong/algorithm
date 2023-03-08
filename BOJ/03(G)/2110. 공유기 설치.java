import java.io.*;
import java.util.*;

import javax.xml.transform.Source;

public class Main {
	static int n,c;
	static int[] house;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n : 집의 개수, c : 공유기의 개수
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		house = new int[n];
		
		for(int i=0; i<n; i++) {
			// 집의 좌표
			house[i] = Integer.parseInt(br.readLine());
		}
		
		// 가장 인접한 두 공유기 사이의 최대 거리
		
		/*
		 * 최대한 많은 집에서 와이파이 사용, 한 집에는 공유기를 하나만 설치 가능
		 * 가장 인접한 두 공유기 사이의 거리를 최대로 하여 설치
		 * => 가장 인접한 두 공유기 사이의 거리를 최대로 해서, c개의 공유기를 n개의 집에 설치
		 * ex) 1,4,8인 경우 인접한 거리는 3이다
		 */
		
		/*
		 * 풀이)
		 * 1. 인접한 거리의 정답은 1~(배열 최대값 - 최소값) 사이에 있다, 그 중 최대값 구하기 => 파라메트릭 서치
		 * 2. 인접한 거리가 정답으로 가능한지 확인 -> 최대값 찾기
		 */
		
		
		System.out.println(binary_search());
		
	}
	
	static int binary_search() {
		
		int res = 0;
		
		// 최대값, 최소값 찾기 위해 + c개 두기 위해 정렬
		Arrays.sort(house);
		int lt = 1;
		int rt = house[house.length-1] - house[0];
		
		while(lt<=rt) {
			
			int mid = (lt+rt)/2;
			
			// 가능한 경우,
			if(determination(mid)) {
				// 최대값 찾기
				lt = mid+1;
				res = mid;
			}
			else rt = mid-1;
		}
		
		return res;
	}
	
	static boolean determination(int dis) {
		
		// 인접 거리가 dis일 때, c개 이상 둘 수 있는지 확인
		
		// house[0]에 두고, 카운트
		int cnt = 1;
		int tmp = house[0];
		for(int i=1; i<house.length ; i++) {
			
			// 인접한 거리가 dis 이상인 경우, 
			if(house[i] - tmp >= dis) {
				cnt++;
				tmp = house[i];
			}
		}
		
		return cnt>=c;
	}
}