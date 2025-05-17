import java.io.*;
import java.util.*;

class Solution {
    //괄호, 사칙연산, 나누기 연산
    //최소값이 8보다 크면 -1 리턴
    public int solution(int N, int number) {
        int answer = 0;
        //N을 i번 사용해서 만들 수 있는 모두의 집합
        List<Set<Integer>> dp = new ArrayList<>();
        for(int i=0; i<=8; i++) {
            dp.add(new HashSet<>());
        }
        
        dp.get(0).add(0);
        
        
        for(int i=1; i<9; i++) {
            
            Set<Integer> set = dp.get(i-1);
            
            int temp = N;
            for(int j=0; i+j<9; j++) {
                
                temp = temp*10 + N;
                if(j==0) temp = N;
                
                Set<Integer> next = dp.get(i+j);
                
                for(int x : set) {
                    // +
                    next.add(x + temp);
                    // -
                    next.add(x - temp);
                    // *
                    next.add(x * temp);
                    // /
                    next.add(x / temp);
                }    
            }
        }
        
        for(int i=1; i<=8; i++) {
            Set<Integer> set = dp.get(i);
            if(set.contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}