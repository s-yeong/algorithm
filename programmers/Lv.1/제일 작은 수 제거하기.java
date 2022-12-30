import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        if(arr.length == 1) return new int[]{-1};
        
			answer = new int[arr.length-1];
        
        int[] tmp = arr.clone();
        Arrays.sort(tmp);
        int num = tmp[0];
        
        int p1=0;
        int p2=0;
        
        while(p1<answer.length && p2<arr.length) {
            if(arr[p2] == num) p2 ++;
            answer[p1] = arr[p2];
            p1++;
            p2++;
        }    
        
        return answer;
    }
}