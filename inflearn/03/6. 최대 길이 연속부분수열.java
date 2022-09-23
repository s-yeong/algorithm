import java.util.Scanner;

public class Main6 {

    public static int solution(int n, int k, int[] arr) {

        int answer = 0;
        int lt = 0;
        int count = 0;
//        int length = 0;

        for(int rt=0; rt<n; rt++) {

            if(arr[rt] == 0) count++;

            while(count > k) {
                if(arr[lt] == 0) count--;
                lt++;
            }
            answer = Math.max(answer, rt - lt + 1);
            /*
            if(count > k) {
                length = rt-lt;
                while(arr[lt] != 0) {
                    lt++;
                }
                lt++;
                count--;
            }
            if(length > answer) answer = length;
            */
        }






        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 수열의 길이
        int k = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(n,k,arr));
    }

}