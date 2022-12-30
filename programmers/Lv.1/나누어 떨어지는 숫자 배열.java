import java.util.*;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        for(int x : arr) {
            if(x%divisor == 0) list.add(x);
        }
        Collections.sort(list);
        
        if(list.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        else answer = new int[list.size()];
        
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
    
        return answer;
    }
}