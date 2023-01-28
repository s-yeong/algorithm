import java.io.*;
import java.util.*;

class Main {
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        // 정점 N개, 간선 M개
        for (int i = 0; i < N+1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        ch = new boolean[N + 1];

        // 연결 요소 개수
        int count = 0;

        for(int i=1; i<=N; i++) {
            if(!ch[i]) {
                count++;
                ch[i] = true;
                DFS(i);
            }
        }
        DFS(1);
        System.out.println(count);
    }

    static boolean[] ch;
    static void DFS(int cur) {
        for (int next : graph.get(cur)) {
            if(!ch[next]) {
                ch[next] = true;
                DFS(next);
            }
        }
    }
}