
import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> Q = new LinkedList<>();
        for(int i=1; i<=N; i++) Q.offer(i);

        while(Q.size() > 1) {
            //제일 위에 있는 카드를 바닥에 버린다
            Q.poll();

            // 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
            Q.offer(Q.poll());
        }

        if(!Q.isEmpty()) System.out.println(Q.poll());
    }
}