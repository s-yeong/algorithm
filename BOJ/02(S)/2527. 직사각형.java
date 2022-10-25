import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        // 4번
        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 직사각형1
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken());

            // 직사각형2
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            // 공통부분X
            if(x1 > p2 || p1 < x2 || y1 > q2 || q1 < y2) sb.append("d");
            // 점
            else if ((x1 == p2 && q1 == y2) || (x1 == p2 && y1 == q2) || (x2 == p1 && q1 == y2) || (x2 == p1 && y1 == q2)) sb.append("c");
            // 선분
            else if(x1 == p2 || p1 == x2 || y1 ==q2 || q1 == y2) sb.append("b");
            // 직사각형
            else sb.append("a");

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
