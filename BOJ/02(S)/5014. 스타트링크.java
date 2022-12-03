import java.util.*;
import java.io.*;

class Main {
    static int F, G, U, D;
    static int[] ch = new int[1000001];
    static int BFS(int L, int S) {

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(S);

        while(!Q.isEmpty()) {
            int len = Q.size();
            for(int i=0; i<len; i++) {
                int cur = Q.poll();
                if(cur == G) return L;

                int curU = cur + U;
                int curD = cur - D;

                if(curU <= F && ch[curU] == 0) {
                    Q.offer(curU);
                    ch[curU] = 1;
                }
                if(curD >= 1 && ch[curD] == 0) {
                    Q.offer(curD);
                    ch[curD] = 1;
                }
            }
            L++;
        }
        return -1;
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int answer = BFS(0, S);
        if(answer == -1) System.out.println("use the stairs");
        else System.out.println(answer);

    }
}
