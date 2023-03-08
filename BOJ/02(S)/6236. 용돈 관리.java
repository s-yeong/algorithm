import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int[] money;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n일 동안 자신이 사용할 금액 계산, m번 통장에서 돈을 빼서 씀
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		money = new int[n];
		for(int i=0; i<n; i++) money[i] = Integer.parseInt(br.readLine());
		
		/*
		 * k원 인출 -> 하루 보낼 수 있으면 -> 그대로 사용
		 * 모자라면 -> 남은 금액 통장에 집어넣고 다시 k원 인출
		 * (남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다.)
		 * = M번 이하로 출금 하기
		 * 
		 * 인출 금액 K를 최소화 => 최소 금액 K 구하기
		 * 
		 */
		
		/*
		 * 풀이)
		 * 정답은 (배열의 최대값, 배열 모두 더한값) 사이에 있다, 그 중 최소값 => 파라메트릭 서치
		 */
		
		
		
		System.out.println(binary_search());
		
	}
	
	static int binary_search() {
		
		int res = 0;
		
		int lt = 0;	// 배열의 최대값
		int rt = 0;	// 배열 모두 더한값
		for(int i=0; i<n; i++) {
			rt += money[i];
			if(money[i] > lt) {
				lt = money[i];
			}
		}
		
		while(lt<=rt) {
			
			int mid = (lt+rt)/2;
			
			
			// 답이 가능한지,
			if(determination(mid)) {
				res = mid;
				// 최소 금액 찾기
				rt = mid-1;
			}
			else lt = mid+1;
		}
		
		return res;
	}
	
	static boolean determination(int num) {
		
		// 인출 금액
		int tmp = num;
		
		// 인출 수
		int cnt = 1;
		
		for(int i=0; i<n; i++) {
			
			tmp -= money[i];
			
			// 모자라면 -> 남은 금액 통장에 집어넣고 다시 k원 인출
			if(tmp < 0) {
				cnt++;
				tmp = num;
				tmp -= money[i];
			}
		}		
		
		return cnt<=m;
	}
	
	
}