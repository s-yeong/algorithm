import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}


		int lt = 0;
		// k개 이상의 라이언 인형을 포함하는 가장 작은 연속된 인형들의 집합의 크기
		int answer = Integer.MAX_VALUE;
		int count = 0;
		for (int rt = 0; rt < n; rt++) {

			if (arr[rt] == 1) {
				count++;
			}

			while(count >=k) {
				answer = Math.min(answer,rt-lt+1);

				if(arr[lt++] == 1) {
					count--;
				}
			}
		}

		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}