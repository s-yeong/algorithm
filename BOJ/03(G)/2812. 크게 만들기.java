import java.util.*;
import java.io.*;

class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] numArr = br.readLine().toCharArray();

        Stack<Integer> stack = new Stack<>();

        for (char num : numArr) {

            int tmp = Integer.parseInt(String.valueOf(num));

            while (!stack.isEmpty() && stack.peek() < tmp && K > 0) {
                stack.pop();
                K--;
            }

            stack.push(tmp);
        }

        while(K > 0) {
            stack.pop();
            K--;
        }

        for(int x : stack) System.out.print(x);
    }
}
