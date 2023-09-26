import java.util.Scanner;

public class Main5 {

    public static int solution(int n) {

        int answer = 0;
        int[] ch = new int[n+1];  // 체크 배열

        for(int i=2; i<=n; i++) {
            if(ch[i] == 0) {
                answer++;
                for(int j=i; j<=n; j=j+i) {
                    ch[j] = 1;
                }
            }
        }


        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(solution(n));
    }
}