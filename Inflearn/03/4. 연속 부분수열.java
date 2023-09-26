import java.util.Scanner;

public class Main4 {

    public static int solution(int n, int m, int[] arr) {

        int answer = 0;
        int lt = 0;
        int sum = 0;

        for(int rt=0; rt<n; rt++) { // rt가 증가하고
            sum += arr[rt]; // 그 지점을 더하고 (lt부터 rt까지 합)
            if(sum == m) answer++;
            while(sum >=m) {    // 합이 m보다 큰 경우만!
                sum -= arr[lt++]; // 값을 뺴고 lt 증가
                if(sum == m) answer++;
            }
        }


/*
        for(int i=0; i<n; i++) {
            int sum = 0;
            int j = i;
            while(sum < m && j < n) {
                sum += arr[j];
                j++;
            }
            if(sum == m) answer++;
        }
*/
        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // n개의 수
        int m = sc.nextInt();   // m
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(n,m,arr));
    }

}