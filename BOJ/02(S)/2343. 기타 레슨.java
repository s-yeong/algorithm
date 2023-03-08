import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] lecture;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// n:강의의 수 , m:블루레이 수
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		lecture = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			lecture[i] = Integer.parseInt(st.nextToken());

		/*
		 * m개의 블루레이에 강의를 녹화 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고함 => 블루레이의 크기 중 최소를 구하기 ex) 1 2
		 * 3 4 5 6 7 8 9에서, 블루레이 수가 3인 경우, (1 2 3 4 5), (6,7), (8,9)인 경우, 블루레이 크기
		 * 15,13,17 => 17
		 */

		/*
		 * 풀이) 블루레이 크기가 (배열 최대값, 배열 모두 더한값) 사이에 정답이 있다, 그 중 최소값 => 파라메트릭 서치 블루레이 크기로 m개
		 * 블루레이를 만들 수 있는지 -> 최소값 찾기
		 */
		System.out.println(binary_search());
	}

	static int binary_search() {

		int res = 0;

		// 정렬X (조건)
		// lt=배열 최대값, rt=배열 모두 더한값
		int lt = 0;
		int rt = 0;
		for (int i = 0; i < n; i++) {
			if (lecture[i] > lt) {
				lt = lecture[i];
			}
			rt += lecture[i];
		}

		while (lt <= rt) {

			int mid = (lt + rt) / 2;

			// 답이 가능하면,
			if (determination(mid)) {
				res = mid;
				// 최소값 찾기
				rt = mid - 1;
			} else
				lt = mid + 1;
		}
		return res;
	}

	static boolean determination(int size) {

		// 블루레이 크기로 m개 이하의 블루레이를 만들 수 있는지
		int tmp_sum = 0;
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			tmp_sum += lecture[i];

			// 블루레이 크기 초과시, 다음 블루레이
			if (tmp_sum > size) {
				cnt++;
				tmp_sum = lecture[i];
			}
		}

		// 마지막 블루레이 추가
		cnt++;

		return cnt <= m;
	}
}