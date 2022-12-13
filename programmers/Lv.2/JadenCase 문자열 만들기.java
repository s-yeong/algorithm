import java.io.*;
import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        s += " ";
        char[] chars = s.toCharArray();
        int lt = 0;
        
        for(int rt=0; rt<chars.length; rt++) {
            
            // 문장 끝 - lt부터 rt까지
            if(chars[rt] == ' ') {
                if(!Character.isDigit(chars[lt])) {
                    answer += Character.toUpperCase(chars[lt++]);
                } else answer += chars[lt++];
                
                String tmp = "";
                for(int i=lt; i<=rt; i++) {
                    tmp += chars[i];
                }
                answer += tmp.toLowerCase();
                
                lt = rt+1;
            }    
        }
        answer = answer.substring(0, answer.length()-1);
        
        return answer;
    }
}