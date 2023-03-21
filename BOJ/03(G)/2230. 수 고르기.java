import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];

		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		// 두 수를 골랐을 때(같은 수일 수도 있다), 그 차이가 M 이상이면서 제일 작은 경우를 구하는 프로그램
		int answer = Integer.MAX_VALUE;

		for(int lt=0; lt<n; lt++) {
			for (int rt = lt+1; rt < n; rt++) {

				if(arr[rt] - arr[lt] > answer) break;
				if(arr[rt] - arr[lt] >= m) {
					answer = arr[rt] - arr[lt];
					break;
				}
			}
		}
		System.out.println(answer);
	}
}