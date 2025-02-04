import java.io.*;
import java.util.*;

public class Main {
    static int RED=0,GREEN=1,BLUE=2;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 각 집을 R, G, B로 칠하는 비용
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][3];
        // 1~n번째까지 집의 R, G, B의 최소값
        dp[0][RED] = arr[0][RED];
        dp[0][GREEN] = arr[0][GREEN];
        dp[0][BLUE] = arr[0][BLUE];
        dp[1][RED] = Math.min(dp[0][GREEN] + arr[1][RED], dp[0][BLUE] + arr[1][RED]);
        dp[1][GREEN] = Math.min(dp[0][RED] + arr[1][GREEN], dp[0][BLUE] + arr[1][GREEN]);
        dp[1][BLUE] = Math.min(dp[0][GREEN] + arr[1][BLUE], dp[0][RED] + arr[1][BLUE]);

        for(int i=2; i<n; i++) {
            dp[i][RED] = Math.min(dp[i-1][GREEN] + arr[i][RED], dp[i-1][BLUE] + arr[i][RED]);
            dp[i][GREEN] = Math.min(dp[i-1][RED] + arr[i][GREEN], dp[i-1][BLUE] + arr[i][GREEN]);
            dp[i][BLUE] = Math.min(dp[i-1][RED] + arr[i][BLUE], dp[i-1][GREEN] + arr[i][BLUE]);
        }

        int answer = Math.min(Math.min(dp[n-1][RED], dp[n-1][GREEN]), dp[n-1][BLUE]);
        System.out.println(answer);
    }
}