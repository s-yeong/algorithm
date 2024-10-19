import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        System.out.println();
        
        //30만*10만 => nlogn 풀이
        int lt = 1;
        int rt = 100000;
        
        long tmp_limit = limit;
        
        while(lt<=rt) {
            boolean isPossible = true;    
            int level = (lt+rt)/2;
            
            for(int idx=0; idx<diffs.length; idx++) {
                if(diffs[idx] <= level) {
                    limit -= times[idx];
                }
                else {
                    // diff-level번 틀림
                    int cnt = diffs[idx] - level;
                    int time = times[idx] + times[idx-1];
                    limit -= (cnt * time) + times[idx];
                }
                if(limit < 0) {
                    isPossible = false;
                    break;
                }
            }
            
            
            if(isPossible) {
                answer = level;
                rt = level-1;
                limit = tmp_limit;
            }
            else {
                lt = level+1;
                limit = tmp_limit;
            }
        }
        
        
        return answer;
    }

}