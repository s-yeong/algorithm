import java.io.*;
import java.util.*;


public class Main {
	static int n;
	static int[] arr;
	static int m;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * 정해진 총액 이하에서 가능한 한 최대의 총 예산을 다음과 같은 방법으로 배정
		 * 1. 모든 요청이 배정될 수 있는 경우 요청한 금액을 그대로 배정
		 * 
		 * 2. 모든 요청이 배정될 수 없는 경우 특정한 정수 상한액을 계산하여, 
		 * 2-1. 그 이상인 예산요청에는 모두 상환액을 배정
		 * 2-2. 상한액 이하의 예산요청에는 요청한 금액 그대로 배정
		 */
		
		
		// 지방의 수
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 총 예산
		m = Integer.parseInt(br.readLine());
		
		// 여러 지방의 예산요청과 국가예산의 총액이 주어졌을 때, 위의 조건을 모두 만족하도록 예산을 배정하는 프로그램
		// 배정된 예산들 중 최댓값인 정수를 출력 -> 배정될 수 없으면 상한액의 최대값, 배정될 수 있으면 배열의 최대값 
		
		
		/**
		 * 풀이
		 * 1. 지방의 예산 총합을 더한 후 모든 요청이 배정될 수 있는지 체크
		 * 2-1. 배정될 수 있으면 -> 최대값 찾아서 출력
		 * 2-2. 배정될 수 없으면 -> 
		 * 2-2-1. 상한액 계산 (상한액은 0부터 배열의 최대값이 될 수 있다 -> 답이 정해져 있다 -> 파라메트릭 서치)
		 * 2-2-2. 배정 -> 최대값 출력
		 */

		// 1. 지방의 예산 총합을 더한 후 모든 요청이 배정될 수 있는지 체크
		// 지방 예산 총합
		int sum = 0;
		for(int i=0; i<n; i++) sum += arr[i];
			
		// 모두 배정 가능하면 -> 최대값 출력
		if(sum <= m) {
			int max = Integer.MIN_VALUE;
			for(int i=0; i<n; i++) {
				max = Math.max(max, arr[i]);
			}
			System.out.println(max);
		}
		// 배정될 수 없으면
		else {
			// 상한액 최대값 계산
			int max = binary_search();
			System.out.println(max);
		}		
	}
	
	static int binary_search() {
		
		Arrays.sort(arr);
		int lt = 0;
		int rt = arr[arr.length-1];
		
		int res = 0;
		
		while(lt<=rt) {
			
			int mid = (lt+rt)/2;
			
			// 상한액이 총 예산 안에 들어오는지 체크
			if(check(mid)) {
				// 가능하면, 최대값 찾기
				res = mid;
				lt = mid+1;
			}
			else rt = mid-1;
			
		}
		return res;
	}
	static boolean check(int num) {
		
		/*
		 * 2-1. 그 이상인 예산요청에는 모두 상환액을 배정
		 * 2-2. 상한액 이하의 예산요청에는 요청한 금액 그대로 배정
		 */
		
		int sum = 0;
		for(int x : arr) {
			
			if(x >= num) sum += num;
			else sum += x;
		}
		
		// 총 예산 안에 들어오면
		if(sum <= m) return true;
		else return false;
	}
}