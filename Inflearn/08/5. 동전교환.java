import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static int n, m;
    static Integer[] arr;
    static int cnt = Integer.MAX_VALUE;


    public static void DFS(int L, int sum) {
        if(L >= cnt) return;
        if(sum > m) return;
        if(sum == m) {
            cnt = Math.min(cnt, L);
        }
        else {

            for(int i=0; i<n; i++) {   // 중복순열과 비슷
                DFS(L + 1, sum + arr[i]);
            }

        }



    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new Integer[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr, Collections.reverseOrder());

        m = sc.nextInt();

        DFS(0,0);
        System.out.print(cnt);

    }
}