import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String str = br.readLine();
            if (str.equals(".")) break;

            Stack<Character> stack = new Stack<>();
            boolean flag = true;
            // 괄호만 스택에 넣기
            for (char x : str.toCharArray()) {
                if (x == '(' || x == '[') stack.push(x);
                else if (x == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
                    else {
                        flag = false;
                        break;
                    }
                }
                else if (x == ']'){
                    if (!stack.isEmpty() && stack.peek() == '[') stack.pop();
                    else {
                        flag = false;
                        break;
                        }
                }
            }

            // stack이 남아 있는 경우
            if(!stack.isEmpty()) flag=false;

            if (flag) sb.append("yes").append("\n");
            else sb.append("no").append("\n");
        }

        System.out.print(sb);
    }
}