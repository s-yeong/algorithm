import java.util.Scanner;

public class Main5 {

    public static int solution(int n) {

        int answer = 0;
        int m = n / 2 + 1;  // 연속된 숫자 필요한 수
        int[] arr = new int[m];

        for(int i=0; i<m; i++) {
            arr[i] = i+1;
        }

        int lt = 0;
        int sum = 0;

        for(int rt=0; rt<m; rt++) {
            sum += arr[rt];
            if(sum == n) answer++;
            while(sum >= n) {   // >= 로 해서 같은 경우에도 lt++
                sum -= arr[lt++];
                if(sum == n) answer++;
            }
        }

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 양의 정수

        System.out.println(solution(n));
    }

}