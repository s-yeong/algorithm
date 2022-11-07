import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        for(int i=0; i<10; i++) {

            int tc = Integer.parseInt(br.readLine());
            Queue<Integer> Q = new LinkedList<>();

            // 8 개의 데이터
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++) Q.offer(Integer.parseInt(st.nextToken()));
            int cnt = 1;

            while (true) {
                int tmp = Q.poll();
                if(cnt==6) cnt = 1;
                tmp -= cnt;
                if(tmp > 0) Q.offer(tmp);
                else {
                    Q.offer(0);
                    break;
                }
                cnt++;
            }

            sb.append("#").append(tc);
            for(int x : Q) sb.append(" ").append(x);
            sb.append("\n");
        }



        System.out.println(sb);


    }
}