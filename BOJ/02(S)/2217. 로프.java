import java.util.*;

public class Main {

    public static void main(String[] args) {

        // 여러개의 로프를 병렬로 연결 -> 중량 나눌 수 있음
        // "모두 고르게" 중량이 나눠진다!
        // 최대 중량 구하기, 모든 로프 사용X, 임의 사용 가능

        Scanner sc = new Scanner(System.in);

        // 로프 개수
        int n = sc.nextInt();

        int[] arr = new int[n];

        // 로프 최대 중량
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();


        Arrays.sort(arr);


        int answer = Integer.MIN_VALUE;

        for(int i=0; i<n; i++) {
            int sum = arr[i] * (n-i);
            answer = Math.max(answer, sum);
        }

//        int weight = arr[0] * n;
//        for(int i=1; i<n; i++) {
//            if(arr[i]*(n-i) > weight) weight = arr[i] * (n - i);
//        }
//        System.out.println(weight);

        System.out.println(answer);


    }
}