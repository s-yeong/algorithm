import java.util.Scanner;
import java.util.Stack;

public class Main4 {
    public static int solution(String str) {

        int answer;
        Stack<Integer> st = new Stack<>();

        for(char x : str.toCharArray()) {

            // 숫자인 경우
            if(Character.isDigit(x)) {
                st.push(x - '0');
            } else {
                int rt = st.pop();
                int lt = st.pop();
                // 수식인 경우
                switch(x) {
                    case '+' :
                        st.push(lt + rt);
                        break;
                    case '-' :
                        st.push(lt - rt);
                        break;
                    case '*' :
                        st.push(lt * rt);
                        break;
                    case '/' :
                        st.push(lt / rt);
                        break;
                }
            }

        }

        answer = st.pop();



        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        System.out.println(solution(str));
    }

}