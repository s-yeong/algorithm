import java.io.*;
import java.util.*;

class Main {
    static int min = Integer.MAX_VALUE;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 수빈이 위치에서 X-1, X+1, X*2 이동
        // 동생을 찾을 수 있는 가장 빠른 시간 => BFS
        // 가중치가 다름 -> 일반적인 BFS로 못품

        // 0초인 경우
        if(N==K) System.out.println(0);
        else System.out.println(BFS(N, K));
    }

    static int[] dx = {-1, 1};
    static class Point implements Comparable<Point>{
        int x;
        int time;

        public Point(int x, int time) {
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            return this.time - o.time;
        }
    }
    static int BFS(int N, int K) {

        // 시간
        boolean[] ch = new boolean[100001];

        PriorityQueue<Point> pQ = new PriorityQueue<>();

        pQ.offer(new Point(N, 0));
        ch[N] = true;

        while(!pQ.isEmpty()) {
            Point ob = pQ.poll();
            int time = ob.time;
            int x = ob.x;

            // 큐에 넣을 때 방문처리하면 우선순위때문에 최소값이 안들어갈 수도 있음
            // 순간 이동할 때와 걸을 때의 시간이 다름!!
            // -> 똑같은 값일 때 순간 이동한 경우가 시간이 최소인데,
            // 걷기할 때 값이 먼저 나와서 방문 처리해버릴 수도 있음

            // 큐에서 꺼낼 때는 시간순으로 꺼내기 때문에 이 때 방문처리해야함

            ch[x] = true;
            if(x == K) {
                return time;
            }

            // 순간 이동
            int nx = x * 2;
            if (nx <= 100000 && !ch[nx]) {
                pQ.offer(new Point(nx, time));
            }

            // 걷기
            for(int i=0; i<2; i++) {
                nx = x + dx[i];
                if(nx>=0 && nx<= 100000 && !ch[nx]) {
                    pQ.offer(new Point(nx, time+1));
                }
            }
        }
        return 0;
    }
}