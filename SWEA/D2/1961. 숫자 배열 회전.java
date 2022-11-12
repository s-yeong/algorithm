import java.io.*;
import java.util.*;


public class Main {
    static int n;

    static int[][] solution(int[][] arr) {

        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = arr[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for(int test_case = 1; test_case <= T; test_case++) {

            n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][n];
            // n x n
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 90도
            int[][] result90 = solution(arr);

            // 180도
            int[][] result180 = solution(result90);

            // 270도
            int[][] result270 = solution(result180);

            // 출력
            sb.append("#").append(test_case).append("\n");
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    sb.append(result90[i][j]);
                }
                sb.append(" ");
                for(int j=0; j<n; j++) {
                    sb.append(result180[i][j]);
                }
                sb.append(" ");
                for(int j=0; j<n; j++) {
                    sb.append(result270[i][j]);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}