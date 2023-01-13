import java.io.*;
import java.util.*;

class Main {
    static String explosion;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        explosion = br.readLine();

        System.out.println(solution(str));
    }

    static String solution(String str) {
        char[] exp = explosion.toCharArray();
        int len = exp.length;

        Stack<Character> stack = new Stack<>();
        for(char x : str.toCharArray()) {
            stack.push(x);

            // 최소 폭발 문자열 보다 스택이 길어야 함
            if(stack.size() >= len) {
                // 폭발 문자열 가능한지 검증

                // 첫번째 문자 인덱스
                int idx = stack.size() - len;

                boolean flag = true;
                for(int i=idx; i<stack.size(); i++) {
                    if(stack.get(i) != exp[i-idx]) {
                        flag = false;   // 폭발 문자열 다르면 false
                        break;
                    }
                }

                // 폭발 문자열이면
                if(flag) {
                    int cnt = len;
                    // 폭발!
                    while(cnt > 0) {
                        stack.pop();
                        cnt--;
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        if(stack.isEmpty()) sb.append("FRULA");
        else {
            for(char x : stack) sb.append(x);
        }
        return sb.toString();

    }
}