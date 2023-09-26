import java.util.*;

public class Main {
    static int[] dy;
    public static int solution (int n, int[] arr) {

        int answer = 0;
        dy[0] = 1;
        for(int i=1; i<n; i++) {
            int max = 0;    // ex 4 6 7 2에서 2이면, 1 출력해야 함
            for(int j=i-1; j>=0; j--) {
                if(arr[j] < arr[i] && dy[j] > max) max = dy[j];
            }
            dy[i] = max + 1;
            answer = Math.max(answer, dy[i]);
        }
        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        dy = new int[n];
        System.out.print(solution(n, arr));



    }
}