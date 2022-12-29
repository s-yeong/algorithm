import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        String[] arr = String.valueOf(n).split("");
        for(String x : arr) {
            answer += Integer.parseInt(x);
        }
        

        return answer;
    }
}