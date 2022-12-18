import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        // 1번
        int[] a = new int[answers.length];
        
        for(int i=0; i<a.length; i++) {
            a[i] = (i+1)%5;
            if(a[i] == 0) a[i] = 5;
        }
        
        // 2번
        int[] b = new int[answers.length];
        int[] tmp = {1,3,4,5};
        int idx = 0;
        for(int i=0; i<b.length; i++) {
            if(i%2 == 0) b[i] = 2;
            else {
                // 1 3 4 5
                b[i] = tmp[idx++];
                if(idx == 4) idx = 0;
            }
        }
        
        // 3번
        int[] c = new int[answers.length];
        int[] tmp2 = {3,1,2,4,5};
        idx = 0;
        for(int i=0; i<b.length; i=i+2) {
            c[i] = tmp2[idx];
            if(i+1<b.length) c[i+1] = tmp2[idx++];
            if(idx == 5) idx = 0;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<answers.length; i++) {
            if(a[i] == answers[i]) map.put(1, map.getOrDefault(1, 0) +1);
            if(b[i] == answers[i]) map.put(2, map.getOrDefault(2, 0) +1);
            if(c[i] == answers[i]) map.put(3, map.getOrDefault(3, 0) +1);
        }
    
        ArrayList<Integer> arr = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(int key : map.keySet()) {
            if(max < map.get(key)) {
                arr.clear();
                arr.add(key);
                max = map.get(key);
            } else if(max == map.get(key)) arr.add(key);
        }
        
        Collections.sort(arr);
        
        answer = new int[arr.size()];
        for(int i=0; i<arr.size(); i++) {
            answer[i] = arr.get(i);
        }
        
        return answer;
    }
}