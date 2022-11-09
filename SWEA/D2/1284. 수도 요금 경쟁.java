import java.util.*;
import java.io.*;

class Main {

    static int aCom(int W, int P) {
        // 1L당 요금 P
        return W*P;
    }
    static int bCom(int Q, int R, int S, int W) {
        // R리터 이하? 요금 Q
        if(W <= R) return Q;
        else {
            int a = W-R;    // 초과량
            return Q + a*S;
        }
    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // A사 : 1리터당 P원의 돈

        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            // 수도양 W
            int answer = Math.min(aCom(W, P), bCom(Q, R, S, W));


            sb.append("#").append(i + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}