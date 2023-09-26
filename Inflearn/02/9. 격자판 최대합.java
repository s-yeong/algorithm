import java.util.Scanner;


public class Main9 {

    public static int solution(int n, int[][] arr) {

        int answer = Integer.MIN_VALUE;
        int sum1;
        int sum2;

        // 각 행의 합,    각 열의 합
        for(int i=0; i<n; i++) {

            sum1 = 0;
            sum2 = 0;
            for(int j=0; j<n; j++) {
                sum1 += arr[i][j];
                sum2 += arr[j][i];
            }
            answer = Math.max(answer, sum1);
            answer = Math.max(answer, sum2);
        }

        // 두 대각선
        sum1 = 0;
        sum2 = 0;
        for(int i=0; i<n; i++) {
            sum1 += arr[i][i];
            sum2 += arr[i][n-i-1];
        }

        answer = Math.max(answer, sum1);
        answer = Math.max(answer, sum2);


        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.print(solution(n, arr));
    }

}