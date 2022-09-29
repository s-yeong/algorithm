import java.util.Arrays;
import java.util.Scanner;

public class Main9 {
    public static int count(int[] arr, int capacity) {

        int cnt = 1;    // DVD 장수
        int sum = 0;    // DVD에 담아낸 곡들의 합
        for(int x : arr) {
            if(sum+x>capacity) {
                cnt++;
                sum = x;
            } else sum += x;
        }
        return cnt;
    }
    public static int solution(int n, int m, int[] arr) {

        int answer = 0;

        int lt = Arrays.stream(arr).max().getAsInt();
        // arr의 최대값이 DVD가 될 수 있는 최소 용량(DVD에 arr 최대값 하나만 들어간 경우)
        int rt = Arrays.stream(arr).sum();


        while (lt <= rt) {
            int mid = (lt + rt) / 2;    // DVD 한장 용량
            if (count(arr, mid) <= m) {
                answer = mid;
                rt = mid - 1;
            } else lt = mid + 1;

        }
        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print(solution(n,m,arr));


    }
}