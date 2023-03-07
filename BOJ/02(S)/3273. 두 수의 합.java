import java.io.*;
import java.util.*;

class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		
		// 자연수 x가 주어졌을 때, 
		// ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램
		
		// 이분 탐색을 위해 정렬
		Arrays.sort(arr);
		
		int cnt = 0;
		// 왼쪽의 값을 기준으로 왼쪽+1부터 배열의 끝까지 탐색 
		for(int i=0; i<n-1; i++) {
			// x - arr[i] 찾기
			if(binary_search(x-arr[i], i+1, n-1, arr)) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	static boolean binary_search(int num, int lt, int rt, int[] arr) {
		
		while(lt<=rt) {
			
			int mid = (lt+rt)/2;
			
			
			if(arr[mid] == num) return true;
			else if(arr[mid] < num) {
				lt = mid+1;
			}
			else rt = mid-1;
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
}