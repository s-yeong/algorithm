import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 10815. 숫자 카드
 * 1. 상근이가 숫자 카드 N개를 가지고 있고, 정수 M개가 주어졌을 때, 이 수가 상근이가 가지고 있는지 확인하기
 * [입력]
 * - N,M 50만 이하
 * - 숫자 -천만 ~ 천만, 같은 숫자는 없음
 * [풀이]
 * - N이 최대 50만이기 때문에 최대 O(NlogN)
 * - 정렬 + 이분 탐색을 통해 O(NlogN)으로 문제 해결 가능
 */
public class Main {

    static int n,m;
    static int[] cards;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cards = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<n; idx++) {
            cards[idx] = Integer.parseInt(st.nextToken());
        }
        // 정렬
        Arrays.sort(cards);

        StringBuilder sb = new StringBuilder();
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<m; idx++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(target)).append(" ");
        }
        System.out.println(sb);
    }
    static int binarySearch(int target) {

        int low = 0;
        int high = n-1;

        while(low <= high) {

            int mid = (low+high) / 2;

            if(cards[mid] == target) {
                return 1;
            }
            else if(cards[mid] > target) {
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }
        return 0;
    }
}
