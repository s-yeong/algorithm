import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[101];

        for(int i=0; i<n; i++) {
            // 사다리의 정보
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x] = y;
        }
        for(int i=0; i<m; i++) {
            // 뱀의 정보
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u] = v;
        }

        /**
         * 게임의 목표 : 1번 칸에서 시작해서, 100번 칸에 도착하는 것
         * 1. 100번 칸에 도착하기 위해 주사위를 굴러야하는 최소값
         * 2. 뱀은 피하고, 사다리는 타기
         * 3. 뱀을 타고 내려왔을 때 더 빠를 수도 있다.
         *
         * => bfs
         */
        int answer = bfs(arr);
        System.out.println(answer);

    }

    static int bfs(int[] arr) {

        boolean[] ch = new boolean[101];
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(1);
        ch[1] = true;

        int answer = 0;

        while(!Q.isEmpty()) {

            int len = Q.size();
            while(len --> 0) {
                int x = Q.poll();
                // 도착지점이면,
                if (x == 100) return answer;

                // 6 범위 내에 사다리나 뱀 체크
                for (int i = 6; i >= 1; i--) {

                    // 방문, 범위 체크
                    if(x+i > 100 || ch[x+i]) continue;

                    if (arr[x + i] != 0) {
                        Q.offer(arr[x + i]);
                        ch[x+i] = ch[arr[x+i]] = true;
                    }
                    else {
                        Q.offer(x + i);
                        ch[x+i] = true;
                    }
                }
            }
            answer++;
        }

        return answer;
    }
}