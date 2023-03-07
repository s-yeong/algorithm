import java.io.*;
import java.util.*;

class Main {
	static int[] answer = new int[2];

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램
		// 10억 + 10억 => 20억 int형으로 해결 가능
		// O(nlogn)으로 풀어야함 -> 이분탐색 생각
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 이분탐색 위해 정렬
		Arrays.sort(arr);
		
		/*
		 * 0에 가장 가까운 용액 -> 현재 값에 -1을 곱한 값과 가까운 값을 찾는다
		 * 오름차순 정렬 -> 현잽 값을 left로 두고 right를 구하는 이분탐색하기 (left < right)
		 * left 기준, left+1 부터 배열 끝까지
		 * 탐색 값(num) "이상"의 원소 중 가장 왼쪽의 원소 찾기 -> 찾은 값, 찾은 값-1이 가장 가까운 원소!
		 */
		
		int answer = Integer.MAX_VALUE;
		int[] answer_arr = new int[2];
		
		// 마지막 인덱스는 left로 될 수 없음
		for(int i=0; i<n-1; i++) {
			
			int res = binary_search(i+1, n-1, -arr[i], arr);
			
			// res, res-1이 후보
			
			// i=0, res가 1인 경우, res-1=i이기 때문에 고려X
			if(i < res-1 && Math.abs(arr[i] + arr[res-1]) < answer) {
				answer = Math.abs(arr[i] + arr[res-1]);
				answer_arr[0] = arr[i];
				answer_arr[1] = arr[res-1];
			}
			
			// -arr[i] 이상의 원소가 없으면 고려X
			if(res < n && Math.abs(arr[i] + arr[res]) < answer) {
				answer = Math.abs(arr[i] + arr[res]);
				answer_arr[0] = arr[i];
				answer_arr[1] = arr[res];
			}
		}
		
		for(int x : answer_arr) System.out.print(x + " ");
		
	}
	static int binary_search(int lt, int rt, int num, int[] arr) {
		
		// num 이상의 원소가 없다면, n(=rt+1)
		// ex) -99일 때, 99 이상인 원소 없는 경우, 배열 끝+1을 리턴
		int res = rt+1;
		while(lt<=rt) {
			
			int mid = (lt+rt)/2;
			
			// arr[mid]값이 num 이상의 원소인 경우,
			if(arr[mid] >= num) {
				res = mid;
				rt = mid-1;
			}
			else {
				lt = mid+1;
			}
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
	
}