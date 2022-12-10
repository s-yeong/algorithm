import java.io.*;
import java.util.*;

class Main {


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 배열로 정수를 저장하는 스택 구현
        int[] stack = new int[10000];
        int idx = -1;

        int N = Integer.parseInt(br.readLine());


        for(int i=0; i<N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            // push
            String command = st.nextToken();
            if(command.equals("push")) {
                stack[++idx] = Integer.parseInt(st.nextToken());
            }

            // pop
            if(command.equals("pop")) {
                // 스택에 들어가 있는 정수가 없으면 -1 출력
                if(idx < 0) sb.append(-1);
                else sb.append(stack[idx--]);
                sb.append("\n");
            }

            // size
            if(command.equals("size")) {
                sb.append(idx + 1).append("\n");
            }

            // empty
            if(command.equals("empty")) {
                // 비어있으면 1
                if(idx < 0) sb.append(1);
                // 아니면 0
                else sb.append(0);
                sb.append("\n");
            }

            // top
            if(command.equals("top")) {
                // 스택에 들어가 있는 정수가 없으면 -1 출력
                if(idx < 0) sb.append(-1);
                else sb.append(stack[idx]);
                sb.append("\n");
            }

        }

        System.out.println(sb);
    }

}
