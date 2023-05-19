import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        /**
         * 새로운 언어 AC
         * AC => 정수 배열에 연산을 하기 위해 만든 언어
         * 1. R : 배열에 있는 수 뒤집기
         * 2. D : 첫번쨰 수 버리기 (배열이 비어있는데 D를 사용한 경우 에러 발생)
         */

        while(T --> 0) {

            // 함수
            String p = br.readLine();
            // 배열에 들어갈 수
            int n = Integer.parseInt(br.readLine());

            String[] split = br.readLine().split(",");
            boolean flag = true;

            split[0] = split[0].substring(1);
            split[split.length-1] = split[split.length-1].substring(0, split[split.length-1].length()-1);

            boolean isLeft = true;
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            if(n != 0) {
                for (String x : split) dq.add(Integer.parseInt(x));
            }

            // 함수 적용
            for(char function : p.toCharArray()) {

                if(!flag) break;

                // 1. R : 배열에 있는 수 뒤집기
                if('R' == function) {
                    isLeft = !isLeft;
                }
                // 2. D : 첫번쨰 수 버리기 (배열이 비어있는데 D를 사용한 경우 에러 발생)
                else {
                    // 빈 경우 -> lt > rt
                    if(dq.isEmpty()) {
                        // error 발생
                        flag = false;
                    }
                    else {
                        if (isLeft) {
                            dq.pollFirst();
                        } else {
                            dq.pollLast();
                        }
                    }
                }
            }

            if(!flag) {
                sb.append("error").append("\n");
            }
            else {
                sb.append("[");
                while(dq.size() >= 2) {
                    if(isLeft) {
                        sb.append(dq.pollFirst()).append(",");
                    }
                    else {
                        sb.append(dq.pollLast()).append(",");
                    }
                }
                if(!dq.isEmpty()) sb.append(dq.pollFirst());
                sb.append("]").append("\n");
            }
        }
        System.out.print(sb);
    }
}