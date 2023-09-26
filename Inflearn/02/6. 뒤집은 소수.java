import java.util.ArrayList;
import java.util.Scanner;

public class Main6 {

    public static boolean isPrime(int num) {

        if(num == 1) return false;
        for(int i=2; i<num; i++) {
            if(num % i == 0) {  // 소수가 아닌지 판단 -> 2부터 자기 숫자 전까지 약수가 존재하면
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> solution(int n, int[] arr) {

        ArrayList<Integer> answer = new ArrayList<>();

        for(int i=0; i<n; i++) {

            int tmp = arr[i];
            int res = 0;    // 뒤집은 수 저장

            while(tmp>0) {
                int t = tmp % 10;   // 일의 자리 추출
                res = res * 10 + t;
                tmp = tmp / 10; // 일의 자리 삭제
            }   // 1/10 -> 0임

            if(isPrime(res)) answer.add(res);
        }


        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        for(int x : solution(n, arr)) {
            System.out.print(x + " ");
        }
    }
}