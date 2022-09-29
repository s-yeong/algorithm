import java.util.Arrays;
import java.util.Scanner;

public class Main10 {
    public static int count(int[] arr, int dist) {
        int ep = arr[0];
        int cnt = 1;
        for(int i=1; i<arr.length; i++) {
            if(arr[i] - ep >= dist) {
                cnt++;
                ep = arr[i];
            }
        }
        return cnt;
    }
    public static int solution(int n, int c, int[] arr) {

        int answer = 0;

        int lt = 1;
        int rt = arr[n - 1];
//        int rt = Arrays.stream(arr).max().getAsInt() - Arrays.stream(arr).min().getAsInt();
        Arrays.sort(arr);
        while(lt <= rt) {
            int mid = (lt + rt) / 2;
            if(count(arr, mid) >= c) {
                lt = mid + 1;
                answer = mid;
            } else rt = mid - 1;

        }



        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print(solution(n,c,arr));


    }
}