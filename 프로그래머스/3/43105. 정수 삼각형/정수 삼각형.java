import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int h = triangle.length;
        int[][] arr = new int[h][h];
        arr[0][0] = triangle[0][0];
        //-1 0 
        for(int i=1; i<h; i++) {
            arr[i][0] = arr[i-1][0] + triangle[i][0];
            arr[i][i] = arr[i-1][i-1] + triangle[i][i];
            for(int j=1; j<i; j++) {
                arr[i][j] = Math.max(arr[i-1][j-1], arr[i-1][j]) + triangle[i][j];
            }
        }
        
        for(int i=0; i<h; i++) {
            answer = Math.max(answer, arr[h-1][i]);
        }
        
        return answer;
    }
}