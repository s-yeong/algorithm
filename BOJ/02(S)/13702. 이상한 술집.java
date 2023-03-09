import java.io.*;
import java.util.*;

public class Main {
	static int n,k;
	static int[] arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n:주전자의 수, k:사람의 수
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		// 
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		/*
		 * n <= k, 주전자의 개수보다 사람의 수가 많다
		 * k명에게 나눠줄 수 있는 최대의 막걸리 용량
		 * ex) 1002, 802, 705 => 401로 동등하게 나뉜 경우, 200, 0, 304 만큼 버림 -> k명 이상
		 */
		
		/*
		 * 막걸리 용량은 0~최대값 사이에 정답이 있다, 그 중 최대값 => 파라메트릭 서치
		 * lt+rt => int 범위 넘어감 => long 타입으로 선언!
		 */
		
		System.out.println(binary_search());
	}	
	
	static long binary_search() {
		
		long res = 0 ;
		
		long lt = 1;
		long rt = Arrays.stream(arr).max().getAsInt();
		
		while(lt<=rt) {
			
			long mid = (lt+rt)/2;
			
			// 답이 가능하면,
			if(determination(mid)) {
				res = mid;
				// 최대값 찾기
				lt = mid+1;
			}
			else rt = mid-1;
		}
		return res;
	}
	
	static boolean determination(long size) {
		
		// k명 이상에게 나눠줄 수 있는지,
		
		int cnt = 0;
		
		for(int i=0; i<n; i++) {
			cnt += arr[i]/size;
		}
		
		return cnt >= k;
	}
	
	
	
	
	
	
}