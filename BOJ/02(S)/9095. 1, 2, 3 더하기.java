import java.io.*;
import java.util.*;

class Main {
    static int answer;
    static void sol(int n, int sum) {
        if(sum==n) {
            answer++;
            return;
        }
        if(sum>n) return;
        for(int i=1; i<=3; i++) {
            sol(n, sum + i);
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc=0; tc<T; tc++) {
            int n = Integer.parseInt(br.readLine());
            // 1 2 3으로 표현
            answer = 0;
            sol(n, 0);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}