import java.io.*;
import java.util.*;

/**
 * 2467. 용액
 * => 0에 가장 가까운 용액 만들어 내는 두 용액 출력
 * [풀이]
 * 1. O(n^2) 시간초과 => O(nlogn) 이하로 풀이 생각
 * 2. 투포인터
 *
 */
public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int idx=0;idx<n;idx++) {
            arr[idx]=Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[2];
        int minDiff = Integer.MAX_VALUE;
        int lt = 0;
        int rt = n-1;
        while(lt<rt) {

            int num = arr[lt];
            int diff = Math.abs(num + arr[rt]);
            while(lt<rt-1 && diff > Math.abs(num + arr[rt-1])) {
                diff = Math.abs(num + arr[rt - 1]);
                rt--;
            }
            if(diff < minDiff) {
                answer[0] = arr[lt];
                answer[1] = arr[rt];
                minDiff = diff;
            }
            lt++;
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}