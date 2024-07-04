import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int time = 0;
        int len = progresses.length;
        
        int[] arr = new int[len];
        
        List<Integer> list = new ArrayList<>();
        //7 3 9
        int count = 0;
        for(int idx=0; idx<len; idx++) {
            int T = (int) Math.ceil((100 - progresses[idx]) / (double) speeds[idx]);
            // 현재 시간보다 크면,
            if(time < T) {
                // 이전 값 넣고
                if(count !=0) list.add(count);
                // 초기화
                count=1;            
                time = T;
            }
            // 현재 시간보다 작거나 같으면
            else {
                count++;
            }
        }
        list.add(count);
        
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}