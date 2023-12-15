import java.io.*;
import java.util.*;


/**
 * 1253. 좋다
 * 1. N개의 수 중에서 하나의 수가 'N개의 수' 중 다른 두 개의 수의 합으로 나타낼 수 있으면 '좋다'
 * 2. '좋다'의 개수 세기
 * 3. 수의 위치가 다르면 값이 같아도 다른 수이다.
 * [풀이]
 * 1. 그 수 보다 작은 수를 찾기 위해 정렬
 * 2. 투 포인터로 풀기 => O(N^2)
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        //1. 그 수 보다 작은 수를 찾기 위해 정렬
        Arrays.sort(arr);

        for(int idx=0; idx<N; idx++) {

            int target = arr[idx];

            int left = 0;
            int right = N - 1;

            while (left < right) {

                // 자기 자신을 제외하고 합 계산
                if(left == idx) left++;
                else if(right == idx) right--;
                else {

                    int sum = arr[left] + arr[right];

                    if(sum == target) {
                        count++;
                        break;
                    }

                    if(sum < target) left++;
                    else right--;
                }
            }

        }

        System.out.println(count);

    }

}