import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);

            if(abs1==abs2) return o1.compareTo(o2);
            else return abs1-abs2;
        });

        /**
         * x가 0이 아님 -> 배열에 x값 넣기
         * x가 0 -> 배열에서 절댓값이 가장 작은 값을 출력 및 제거
         */

        StringBuilder sb = new StringBuilder();

        while(n-->0) {
            int x = Integer.parseInt(br.readLine());

            if(x==0) {
                if(pq.isEmpty()) sb.append(0).append("\n");
                else sb.append(pq.poll()).append("\n");
            }
            else {
                pq.offer(x);
            }
        }
        System.out.print(sb);
    }
}