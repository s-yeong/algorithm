import java.util.*;

public class Main {

    static int[] dy;
    static int n, m;
    public static int solution(int[] coin) {

        Arrays.fill(dy, Integer.MAX_VALUE);

        int answer = 0;
        dy[0] = 0;
        for(int i=0; i<n; i++) {
            for(int j = coin[i]; j<=m; j++) {
                dy[j] = Math.min(dy[j], dy[j - coin[i]] + 1);
            }
        }


        return dy[m];
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int[] coin = new int[n];

        for (int i = 0; i < n; i++) coin[i] = sc.nextInt();
        m = sc.nextInt();
        dy = new int[m + 1];

        System.out.print(solution(coin));



    }
}