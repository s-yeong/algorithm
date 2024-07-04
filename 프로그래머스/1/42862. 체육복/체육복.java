import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        // 체육복 적절히 빌려 최대한 많은 사람 수업듣기
        // 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있음
        // lost idx reverse idx 동일할 수도 있다
        
        answer = n - lost.length;
        Arrays.sort(lost);
        Arrays.sort(reserve);
        boolean[] ch = new boolean[reserve.length];
        
        // 도난 당한 경우
        boolean[] chLost = new boolean[lost.length];
        for(int idx=0; idx<lost.length; idx++) {
            for(int ridx=0; ridx<reserve.length; ridx++) {
                if(lost[idx] == reserve[ridx]) {
                    ch[ridx] = true;
                    chLost[idx] = true;
                    answer++;
                    break;
                }
            }
        }
        
        
        for(int idx=0; idx<lost.length; idx++) {
            if(chLost[idx]) continue;
            int lnum = lost[idx];
            
            for(int ridx=0; ridx<reserve.length; ridx++) {
                if(ch[ridx]) continue;
                int rnum = reserve[ridx];
                if(rnum-1 == lnum || rnum+1 == lnum) {
                    ch[ridx] = true;
                    answer++;
                    break;
                }
                
            }
            
        }
        
        return answer;
    }
}