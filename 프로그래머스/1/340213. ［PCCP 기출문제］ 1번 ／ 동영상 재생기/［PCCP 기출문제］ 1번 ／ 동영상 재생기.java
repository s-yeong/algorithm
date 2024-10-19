import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int vls = convertSeconds(video_len);
        int ps = convertSeconds(pos);
        int oss = convertSeconds(op_start);
        int oes = convertSeconds(op_end);
                
        if(isOp(ps, oss, oes)) {
            ps = oes;
        }
        
        
        for(String command : commands) {
            
            if("prev".equals(command)) {
                ps -= 10;
                if(ps < 10) ps = 0;
            }
            else {
               ps += 10;
                if(vls - ps < 10) {
                    ps = vls;
                }
            }
         
            
            if(isOp(ps, oss, oes)) {
                ps = oes;
            }
        }
        
        int min = ps / 60;
        int second = ps % 60;
        
        return String.format("%02d:%02d", min, second);
    }
    
    private boolean isOp(int ps, int oss, int oes) {
        return ps >= oss && ps <= oes;
    }
    
    private int convertSeconds(String str) {
        
        String[] split = str.split(":");
        int min = Integer.parseInt(split[0]);
        int second = Integer.parseInt(split[1]);
        
        return min*60 + second;
    }
    
}