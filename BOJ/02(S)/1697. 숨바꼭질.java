import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(BFS(N, K));
    }


    static int BFS(int cur, int k) {
        int[] ch = new int[200001];

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(cur);
        int L = 0;
        while(!Q.isEmpty()) {

            int len = Q.size();

            for(int i=0; i<len; i++) {
                int x = Q.poll();
                if(x==k) return L;

                int nx1 = x * 2;
                int nx2 = x + 1;
                int nx3 = x - 1;

                if(nx1 >=0 && nx1 <= 200000 && ch[nx1] == 0) {
                    Q.offer(nx1);
                    ch[nx1] = 1;
                }
                if(nx2 >=0 && nx2 <= 200000 && ch[nx2] == 0) {
                    Q.offer(nx2);
                    ch[nx2] = 1;
                }
                if(nx3 >=0 && nx3 <= 200000 && ch[nx3] == 0) {
                    Q.offer(nx3);
                    ch[nx3] = 1;
                }
            }
            L++;
        }
        return 0;
    }
}