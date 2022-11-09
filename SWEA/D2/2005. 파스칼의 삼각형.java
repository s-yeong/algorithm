import java.util.*;
import java.io.*;

class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case=0 ;test_case<T; test_case++) {

            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][n];

            // 첫번째 줄 항상 1
            arr[0][0] = 1;

            for(int i=1; i<n; i++) {    // 두번째 줄 부터
                arr[i][0] = 1;
                for(int j=1; j<=i; j++) {    // 두번째 칸 부터
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }

            // 출력
            sb.append("#").append(test_case + 1).append("\n");
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(arr[i][j] != 0) sb.append(arr[i][j]).append(" ");
                    else break;
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}