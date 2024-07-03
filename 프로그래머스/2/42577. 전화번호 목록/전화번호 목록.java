import java.io.*;
import java.util.*;

class Solution {
    public boolean solution(String[] arr) {
        
        boolean answer = true;
        int len = arr.length;
        
        Arrays.sort(arr);
    
        for(int idx=0;idx<len-1;idx++) {
            String phone = arr[idx];
            String target = arr[idx+1];
            
            if(target.indexOf(phone) == 0) {
                return false;
            }
        }
        
        return answer;
    }
}