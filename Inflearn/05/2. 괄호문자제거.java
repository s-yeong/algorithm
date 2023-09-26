import java.util.Scanner;
import java.util.Stack;

public class Main2 {

    public static String solution(String str) {

        String answer = "";
        Stack<Character> st = new Stack<>();

        for(char x : str.toCharArray()) {


            if(x == ')') {

                while(st.pop() != '(') ;

            } else st.push(x);

        }

        for(int i=0; i<st.size(); i++) {
            answer += st.get(i);
        }
/*
        while(!st.isEmpty()) {
            answer += st.pop();
        }

        answer = new StringBuilder(answer).reverse().toString();
*/
        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();


        System.out.println(solution(str));
    }

}