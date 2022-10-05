import java.util.Scanner;

public class Main {

    static String answer = "NO";
    static int n, total = 0;
    static boolean flag = false; // YES가 발견되면, 그 다음 재귀들은 다 리턴하기 위해서
    static int sum;

    public static void DFS(int L, int sum, int[] arr) {
        if(flag) return;
        if(sum>total/2) return;
        if(L == n) {
            if(total-sum == sum) {
                answer = "YES";
                flag = true;
            }
        }
        else {
            DFS(L + 1, sum +arr[L], arr);
            DFS(L + 1, sum, arr);
        }
    }
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<n; i++) {
            total += arr[i];
        }
        DFS(0, 0, arr);
        System.out.println(answer);

    }
}