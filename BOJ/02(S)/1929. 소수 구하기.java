import java.io.*;
import java.util.*;

class Main {


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] ch = new int[N + 1];

        // M 이상 N 이하 소수
        ch[1] = 1;

        // 소수인 것들다 1로 처리
        for(int i=2; i<=N; i++) {
            if(ch[i] == 1) continue;
            for(int j=i+i; j<=N; j=i+j) {
                ch[j] = 1;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i=M; i<=N; i++) {
            if(ch[i] == 0) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}
