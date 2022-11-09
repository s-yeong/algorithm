import java.util.*;
import java.io.*;

class Main {


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int test_case = 0; test_case<10; test_case++) {
            int T = Integer.parseInt(br.readLine());
            int[][] arr = new int[100][100];
            int answer = 0;

            for(int i=0; i<100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            // 가로 합
            // 세로 합
            for(int i=0; i<100; i++) {
                int sum1 = 0;
                int sum2 = 0;
                for(int j=0; j<100; j++) {
                    sum1 += arr[i][j];	// 가로
                    sum2 += arr[j][i];	// 세로
                }
                answer = Math.max(answer, sum1);
                answer = Math.max(answer, sum2);
            }

            // 대각선 합
            int sum3 =0;
            int sum4 =0;
            for(int i=0; i<100; i++) {
                sum3 += arr[i][i];
                sum4 += arr[99-i][i];
            }

            answer = Math.max(answer, sum3);
            answer = Math.max(answer, sum4);




            sb.append("#").append(test_case+1).append(" ").append(answer).append("\n");
        }


        System.out.println(sb);
    }
}