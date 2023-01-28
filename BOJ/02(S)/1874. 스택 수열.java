import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써,
        // 하나의 수열을 만들 수 있다.
        // 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자.

        // 입력
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
        int p = 0;  // 위치

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {

            //push
            stack.push(i);
            sb.append("+").append("\n");

            //pop
            while(!stack.isEmpty() && stack.peek() == arr[p]) {
                stack.pop();
                sb.append("-").append("\n");
                p++;
            }
        }

        /*
        1. 이 수가 나올 때까지 push
        2-1. 나왔으면 pop 하고 인덱스 증가시킴
        2-2. 그 다음 수가 현재 스택에 있는지 확인하고 있으면 pop하고 인덱스 증가시킴
        3. 다시 그 다음 수가 나올 때 까지 push
        4. for문 돌고 스택이 비어있지 않으면 NO 출력
         */

        if(!stack.isEmpty()) System.out.println("NO");
        else System.out.print(sb);

    }
}