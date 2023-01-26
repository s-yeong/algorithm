
import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있음
        //순서대로 K번째 사람을 제거
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 1; i <= N; i++) Q.offer(i);

        int[] arr = new int[N+1];
        int count = 0;
        while (!Q.isEmpty()) {

            int num = Q.poll();
            count++;
            if(count%K == 0) arr[count / K] = num;
            else Q.offer(num);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for(int i=1; i<=N; i++) {
            sb.append(arr[i]);
            if(i==N) sb.append(">");
            else sb.append(", ");
        }

        System.out.print(sb);
    }
}