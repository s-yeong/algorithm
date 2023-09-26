import java.util.Scanner;
import java.util.Stack;

public class Main1 {
    public static String solution(String str) {

        String answer = "YES";
        Stack<Character> st = new Stack<>();

        for(char x : str.toCharArray()) {
            if(x == '(') st.push(x);
            else {
                if(st.isEmpty()) return "NO";
                else st.pop();
            }
        }
        if(!st.isEmpty()) return "NO";

/*
        try {
            for(char x : str.toCharArray()) {

                if(x == '(') st.push(x);
                else if(x == ')') st.pop();
            }

            if(!st.isEmpty()) return "NO";

        } catch (Exception e) {
           return "NO";
        }
*/

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();


        System.out.println(solution(str));
    }

}