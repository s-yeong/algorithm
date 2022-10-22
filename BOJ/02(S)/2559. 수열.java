import java.util.*;

public class Main {


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }


        int lt = 0;
        int rt = k - 1;

        // 처음 합
        int sum = 0;
        for(int i=0; i<=rt; i++) sum += arr[i];
        int max = sum;

        while(rt < n-1) {
            sum -= arr[lt++];
            sum += arr[++rt];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}