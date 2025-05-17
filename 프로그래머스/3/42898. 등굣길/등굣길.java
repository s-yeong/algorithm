import java.io.*;
import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        for(int[] arr : puddles) {
            
            
        }
        
        int[][] dp = new int[m+1][n+1];
        //dp[i][j] = dp[i-1][j] dp[i][j-1]
        dp[0][1] = 1;
        
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                boolean isPuddle = false;
                for(int[] w : puddles) {
                    if(i==w[0] && j==w[1]) {
                        isPuddle = true;
                    }
                }
                if(isPuddle) continue;
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
            }
        }
        
        return dp[m][n];
    }
}