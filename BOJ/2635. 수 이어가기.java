import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int max = Integer.MIN_VALUE;
    static ArrayList<Integer> arrM = new ArrayList<>();
    static ArrayList<Integer> arr = new ArrayList<>();
    static void DFS(int cnt, int n, int L) {

        if(L<0) {
            if(cnt > max) {
                max = cnt;
                arrM.clear();
                arrM.addAll(arr);
            }
            arr.clear();
        }
        else {
            arr.add(L);
            DFS(++cnt, L,n-L);
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // n<=30000
        // 세번쨰 수부터 앞의 앞의 수에서 앞의 수를 빼서 만든다

        for(int i=n/2; i<=n; i++) { // n/2보다 작은 값은 모두 max값 3임
            arr.add(n); // 매번 clear()하기 때문에 넣어줘야함
            DFS(1, n, i);
        }

        System.out.println(max);
        for(int x : arrM) System.out.print(x + " ");

    }
}