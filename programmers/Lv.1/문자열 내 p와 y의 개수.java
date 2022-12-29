import java.io.*;
import java.util.*;

class Solution {
    boolean solution(String s) {
        s = s.toUpperCase();
        boolean answer = s.chars().filter(e -> 'P'==e).count() == s.chars().filter(e -> 'Y'==e).count();
        
        return answer;

    }
}