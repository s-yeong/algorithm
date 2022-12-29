import java.io.*;
import java.util.*;

class Solution {
    public long solution(long n) {
        // long answer = -1;
        // long num = (long)Math.sqrt(n);
        // for(long i = num-1; i<=num+1; i++) {
        //     if(i*i == n) {
        //         answer = (i+1)*(i+1);
        //     }
        // }
        
        double i = Math.sqrt(n);
        long answer = Math.floor(i) == i ? (long) Math.pow(i+1,2) : -1;
        
        
        return answer;
    }
}