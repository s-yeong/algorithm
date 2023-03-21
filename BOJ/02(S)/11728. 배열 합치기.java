import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arrA = new int[n];
		int[] arrB = new int[m];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}

		int pA = 0;
		int pB = 0;

		StringBuilder sb = new StringBuilder();
		while(pA<n && pB<m) {

			if(arrA[pA] <= arrB[pB]) {
				sb.append(arrA[pA]).append(" ");
				pA++;
			}
			else if(arrA[pA] > arrB[pB]) {
				sb.append(arrB[pB]).append(" ");
				pB++;
			}
		}

		while(pA<n) {
			sb.append(arrA[pA]).append(" ");
			pA++;
		}
		while(pB<m) {
			sb.append(arrB[pB]).append(" ");
			pB++;
		}

		System.out.println(sb);
	}
}