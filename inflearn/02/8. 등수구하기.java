import java.util.Scanner;


public class Main8 {

    public static int[] solution(int n, int[] arr) {

        int[] answer = new int[n];

        for(int i=0; i<n; i++) {
            int cnt = 1;
            for(int j=0; j<n; j++) {;
                if(arr[i] < arr[j]) {   // 점수가 작으면 등수(숫자)가 올라감
                    cnt++;
                }
            }
            answer[i] = cnt;
        }




        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
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