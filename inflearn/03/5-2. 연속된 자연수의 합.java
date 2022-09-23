import java.util.Scanner;

public class Main52 {

    public static int solution(int n) {

        int answer = 0;

        int cnt = 1;    // 연속된 자연수의 개수
        n--;
        while(n>0) {
            cnt++;
            n=n-cnt;
            if(n%cnt==0) answer++;
        }

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 양의 정수

        System.out.println(solution(n));
    }

}