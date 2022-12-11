import java.io.*;
import java.util.*;

class Main {


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            // N 번째로 도착한 손님에게 배정될 방 번호 계산
            // H*W 호텔

            int Y = N % H;
            int X = N / H + 1;
            if(Y==0) {
                Y = H;
                X -= 1;
            }



            sb.append(Y);
            if(X<10) sb.append(0).append(X);
            else sb.append(X);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
