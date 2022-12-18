import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int[] a = {1,2,3,4,5};
        int[] b = {2,1,2,3,2,4,2,5};
        int[] c = {3,3,1,1,2,2,4,4,5,5};
        int[] count = new int[3];
        
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == (a[i%a.length])) count[0]++;
            if(answers[i] == (b[i%b.length])) count[1]++;
            if(answers[i] == (c[i%c.length])) count[2]++;
        }
        
        int max = Math.max(Math.max(count[0], count[1]),count[2]);
        
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<3; i++) {
            if(max == count[i]) arr.add(i+1);
        }
        
        Collections.sort(arr);
        
        answer = new int[arr.size()];
        for(int i=0; i<arr.size(); i++) {
            answer[i] = arr.get(i);
        }

        return answer;
    }
}