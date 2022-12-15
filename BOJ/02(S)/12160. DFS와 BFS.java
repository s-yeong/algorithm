import java.io.*;
import java.util.*;

class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] ch;
    static void DFS(int v) {
        System.out.print(v + " ");

        for(int nv : graph.get(v)) {
            if(ch[nv] == 0) {
                ch[nv] = 1;
                DFS(nv);
            }
        }
    }

    static void BFS(int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            System.out.print(cur + " ");
            for(int nv : graph.get(cur)) {
                if(ch[nv] == 0) {
                    ch[nv] = 1;
                    Q.offer(nv);
                }
            }
        }

    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        ch = new int[N + 1];

        // 양방향
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) Collections.sort(graph.get(i));

        // DFS
        ch[V] = 1;
        DFS(V);
        System.out.println();

        // BFS
        ch = new int[N + 1];
        ch[V] = 1;
        BFS(V);

    }
}