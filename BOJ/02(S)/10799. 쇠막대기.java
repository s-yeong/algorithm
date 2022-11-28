import java.util.*;
import java.io.*;

class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int cnt = 0;
        Stack<Character> st = new Stack<>();

        char[] chars = str.toCharArray();

        for(int i=0; i<chars.length; i++) {

            if(chars[i] == '(') st.push(chars[i]);
            else {
                st.pop();
                // 앞이 ')' -> 레이저X
                if(chars[i-1] == ')') cnt++;
                // 앞이 '(' -> 레이저O
                else cnt += st.size();
            }
        }

        System.out.println(cnt);

    }
}
