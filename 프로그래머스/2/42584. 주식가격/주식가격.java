import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<Integer> st = new Stack<>();
        st.push(0);
        
        for(int idx=1; idx<prices.length; idx++) {
            while(!st.isEmpty() && prices[st.peek()] > prices[idx]) {
                int stIdx = st.pop();
                answer[stIdx] = idx - stIdx;
            }
            st.push(idx);
        }
        while(!st.isEmpty()) {
            int stIdx = st.pop();
            answer[stIdx] = prices.length - stIdx - 1;
        }
        
        
        return answer;
    }
}