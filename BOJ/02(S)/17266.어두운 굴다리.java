import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int[] arr;
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[m];
		for (int i = 0; i < m; i++) arr[i] = Integer.parseInt(st.nextToken());

		// 굴다리의 길이 N을 모두 비추기 위한 가로등의 최소 높이
		// 높이는 0~N사이에 있고, 그 중 최소값 구하기 -> 파라메트릭 서치

		System.out.println(binary_search());
	}

	static int binary_search() {
		int res = 0;

		int lt = 0;
		int rt = n;

		while (lt <= rt) {
			int mid = (lt + rt) / 2;

			// 답이 가능한지,
			if(determination(mid)) {
				res = mid;
				rt = mid-1;
			}
			else lt = mid+1;
		}
		return res;
	}

	static boolean determination(int height) {

		int last = 0;

		for (int i = 0; i < m; i++) {
			if(arr[i] - last <= height) last = arr[i] + height;
			else return false;
		}

		return last >= n;
	}
}