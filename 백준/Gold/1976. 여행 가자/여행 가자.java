import java.io.*;
import java.util.*;


/**
 * 1976. 여행 가자
 * 여행 가능한 경로인지 알아보기
 * 1. 중간에 경유 가능
 * 2. 같은 도시 반복 방문 가능
 * 3. 무방향 그래프
 * [풀이]
 * 해당 지점을 방문체크 할 수 있으면 여행 가능한 경로다.
 * 1. 방문해야 하는 지점을 1로 두고
 * 2. 그 지점을 방문하면 0으로 바꾼다.
 * 3. 시작점에서 연결시킨다.
 */
public class Main {

    static int N, M;
    static int[] plan;
    static boolean[][] ch;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                int isLink = Integer.parseInt(st.nextToken());
                if(isLink == 1) {
                    //3. 무방향 그래프
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // 여행 계획
        int startIdx = 0;
        plan = new int[N+1];
        ch = new boolean[N+1][N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int idx = Integer.parseInt(st.nextToken());
            if(i==0) startIdx = idx;

            // 방문 해야 하는 지점 = 1
            plan[idx] = 1;
        }

        solve(startIdx);

        String answer = "YES";
        for(int idx : plan) {
            // 방문안한 지점이 있으면,
            if(idx == 1) answer = "NO";
        }

        System.out.println(answer);
    }
    static void solve(int cur) {

        plan[cur] = 0;

        for(int next : graph.get(cur)) {

            if(ch[cur][next]) continue;

            ch[cur][next] = true;
            solve(next);
        }
    }

}