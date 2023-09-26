import java.util.Scanner;

public class Main7 {

    public static int solution(int n, int[] arr) {

        int answer = 0;
        int cnt = 0;

        for(int i=0; i<n; i++) {
                if(arr[i] == 0) {
                    cnt = 0;
                } else if(arr[i] == 1) {
                    cnt++;
                    answer += cnt;
                }
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
        System.out.println(solution(n, arr));

    }

}