import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= 10; test_case++) {

            // 평탄화 완료 -> 최고점 최저점 높이 차 반환 => 0또는1이겠네
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[100];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) arr[i] = Integer.parseInt(st.nextToken());

            // 평탄화 완료 이후 어차피 반복해도 상관X
            for(int i=0; i<n; i++) {
                Arrays.sort(arr);
                arr[0]++;
                arr[arr.length-1]--;
            }

            // 답 구하기
            Arrays.sort(arr);
            int answer = arr[arr.length - 1] - arr[0];


            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}