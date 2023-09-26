import java.util.Scanner;

public class Main3 {

    public static int solution(int n, int k, int[] arr) {

        int answer = 0;
        int sum = 0;

        // 처음 창문의 합 구하기
        for (int i = 0; i < k; i++) sum += arr[i];
        answer = sum;

        for(int i=k; i<n; i++) {
            sum = sum + arr[i] - arr[i-k];
            answer = Math.max(answer, sum);
        }

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // n일 동안 매출 기록
        int k = sc.nextInt();   // 연속된 k일
        int[] arrN = new int[n];

        for(int i=0; i<n; i++) {
            arrN[i] = sc.nextInt();
        }

        System.out.println(solution(n,k,arrN));
    }

}