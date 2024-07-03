import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] arr) {
        String answer = "";
        
        String[] nums = new String[arr.length];
        for(int idx=0;idx<nums.length;idx++) {
            nums[idx] = String.valueOf(arr[idx]);
        }
        
        Arrays.sort(nums, (o1, o2) -> {
            return (o2+o1).compareTo(o1+o2);
        });
        
        StringBuilder sb = new StringBuilder();
        for(int idx=0;idx<nums.length;idx++) {
            sb.append(nums[idx]);
        }
        
        answer = sb.toString();
        if(answer.indexOf("0") == 0) {
            answer = "0";
        }
        
        return answer;
    }
    
}