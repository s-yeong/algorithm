
import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a==0 && b==0 && c==0) break;

            // a가 빗변이면,
            if(a>b && a>c) {
                if(a*a == b*b+c*c) sb.append("right");
                else sb.append("wrong");
            }
            // b가 빗변이면,
            else if(b>a && b>c) {
                if(b*b == a*a+c*c) sb.append("right");
                else sb.append("wrong");
            }
            // c가 빗변이면,
            else {
                if(c*c == a*a+b*b) sb.append("right");
                else sb.append("wrong");
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
}