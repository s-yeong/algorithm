import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N == K) System.out.print(0 + "\n" + N);
        else {
            BFS(N, K);
            System.out.println(sb);
        }
    }

    static int[] dx = {-1, 1, 0};
    static int[] ddx = {0, 0, 2};
    static StringBuilder sb = new StringBuilder();
    static void BFS(int N, int K) {

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(N);
        int[] route = new int[100001];

        Arrays.fill(route, -1);
        // 방문 기록을 하는데, 이전 위치로 방문 기록!
        // 이전 위치가 0이될 수 있기 때문에 배열값 -1로 채우기

        int time = 0;
        while(!Q.isEmpty()) {

            int len = Q.size();
            while (len -- > 0) {
                int x = Q.poll();
                for (int i = 0; i < 3; i++) {
                    int nx = (dx[i] != 0) ? x + dx[i] : x * ddx[i];

                    // 수빈이가 동생 찾으면,
                    if (nx == K) {
                        sb.append(time + 1).append("\n");   // 가장 빠른 시간

                        // 체크
                        route[nx] = x;
                        int[] ans = new int[time + 2];    // 이동 경로 넣을 배열

                        // 이동 경로 계산
                        route(ans, route, time+2, K);
                        return;
                    }
                        // 방문안했으면,
                        if (nx >= 0 && nx <= 100000 && route[nx] == -1) {
                            // 이전 위치 값 넣어서 방문 처리
                            route[nx] = x;
                            Q.offer(nx);
                        }
                    }
                }
            time++;
        }
    }
    static void route(int[] ans, int[] route, int time, int K) {

        int tmp = K;
        while(time --> 0) {
            ans[time] = tmp;
            tmp = route[tmp];   // 이전 값 넣기
        }

        // 저장
        for(int x : ans) sb.append(x).append(" ");

    }
}