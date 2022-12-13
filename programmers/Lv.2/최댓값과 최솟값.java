import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        String[] sArr = s.split(" ");
        int[] arr = new int[sArr.length];
        for(int i=0; i<arr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        
        Arrays.sort(arr);
                
        String answer = "";
        
        answer += arr[0] + " " + arr[arr.length-1];
        
        return answer;
    }
}