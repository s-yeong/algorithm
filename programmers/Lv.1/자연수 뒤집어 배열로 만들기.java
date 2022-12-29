import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(long n) {
        // String[] arr = new StringBuilder(String.valueOf(n)).reverse().toString().split("");
        // int[] answer = new int[arr.length];
        
        // for(int i=0; i<arr.length; i++) {
        //     answer[i] = Integer.parseInt(arr[i]);
        // }
        
        int[] answer = new StringBuilder().append(n).reverse().chars().map(Character::getNumericValue).toArray();
        
        return answer;
    }
}