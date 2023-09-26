import java.util.Scanner;
import java.util.Stack;

public class Main5 {
    public static int solution(String str) {

        int answer = 0;

        Stack<Character> st = new Stack<>();

        char[] chars = str.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if(chars[i] == '(') st.push(chars[i]);
            else {
                st.pop();
                if(chars[i-1] == '(') { // 레이저
                    answer += st.size();
                } else {
                    answer++;   // 막대기 끝나면 하나 더해줘야함
                }
            }
        }


        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        System.out.println(solution(str));
    }

}