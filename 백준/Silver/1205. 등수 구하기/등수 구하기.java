import java.io.*;
import java.util.*;

/**
 * 1205. 등수 구하기
 * 1. 랭킹 리스트에 올라갈 수 있는 점수의 개수 : P
 * 2. 리스트에 있는 점수 n개 비오름차순
 * 3. 랭킹 리스트가 꽉 차있을 때, 새 점수가 이전 점수보다 더 좋을 때만 점수가 바뀐다.
 *[입력]
 * 1. P는 10보다 크거나 같고, 50보다 작거나 같다.
 * 2. N은 0보다 크거나 같고 P보다 작거나 같다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        if (n == 0) {
            System.out.println(1);
               System.exit(0);
        }

        st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        if(n==p && target <= arr[n-1]) {
            System.out.println(-1);
            System.exit(0);
        }

        int answer = 0;
        int rank = 1;

        for(int idx=0; idx<n; idx++) {

            int num = arr[idx];

            if(target >= num) {
                rank = idx + 1;
                break;
            }
            else {
                rank++;
            }
        }

        if(rank > p) answer = -1;
        else answer = rank;
        System.out.println(answer);
    }
}