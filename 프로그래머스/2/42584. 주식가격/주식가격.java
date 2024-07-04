import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        PriorityQueue<Price> pQ = new PriorityQueue<>();
        List<Price> list = new ArrayList<>();
        
        pQ.offer(new Price(0, prices[0]));
        for(int idx=1; idx<prices.length; idx++) {
            
            Price highprice = pQ.peek(); 
            int val = prices[idx];
            
            // 최대값이 주식 떨어지면,
            if(highprice.val > val) {
                while(!pQ.isEmpty()) {
                    Price price = pQ.poll();
                    // 떨어졌으면, 시간 기록
                    if(price.val > val) {
                        price.time = idx - price.idx;
                        list.add(price);
                    }
                    // 안떨어졌으면,
                    else {
                        pQ.offer(price);
                        break;
                    }
                }
            }
            // pQ 넣기
            pQ.offer(new Price(idx, val));
        }
        
        while(!pQ.isEmpty()) {
            Price price = pQ.poll();
            // 안떨어졌으면, 시간추가
            if(price.time == 0) {
                price.time = prices.length - price.idx - 1; 
            }
            list.add(price);
        }
        
        for(Price price : list) {
            answer[price.idx] = price.time;
        }
            
        
        return answer;
    }
    static class Price implements Comparable<Price> {
        int idx;
        int val;
        int time;
        
        public Price(int idx, int val) {
            this.idx=idx;
            this.val=val;
            time = 0;
        }
        
        public int compareTo(Price obj) {
            return obj.val - this.val;
        }
        
        
    }
}