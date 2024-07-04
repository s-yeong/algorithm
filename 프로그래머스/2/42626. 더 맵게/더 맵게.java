import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] arr, int k) {
        int answer = 0;
        
        // 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for(int idx=0; idx<arr.length; idx++) {
            pQ.offer(arr[idx]);
        }
        
        while(!pQ.isEmpty()) {
            int num1 = pQ.poll();
            if(num1 >= k) {
                return answer;
            }
            
            // 스코빌 지수 계산
            if(!pQ.isEmpty()) {
                int num2 = pQ.poll();
                pQ.offer(num1 + (num2 * 2));
            }
            answer++;
        }
        
        return -1;
    }
}