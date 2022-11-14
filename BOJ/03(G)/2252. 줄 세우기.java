import java.io.*;
import java.util.*;

class Main {
    static List<LinkedList<Integer>> graph;
    static int N;
    static ArrayList<Integer> solution(int[] inDegree) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> Q = new LinkedList<>();

        // 진입 차수 0인 노드 큐 삽입
        for(int i=1; i<=N; i++) {
            if(inDegree[i] == 0) Q.offer(i);
        }

        while(!Q.isEmpty()) {
            int v = Q.poll();
            result.add(v);
            inDegree[v] = -1;

            for(int nv : graph.get(v)) {
                // 새로운 진입 차수가 0이되면 큐 삽입
                if(--inDegree[nv] == 0) Q.offer(nv);
            }
        }


        return result;
    }

    // 시간 복잡도 - 정점 개수 + 간선 개수 = 32,000 + 100,000 = 132,000
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N명의 학생 초기화
        graph = new LinkedList<>();
        for (int i = 0; i <= N; i++) graph.add(new LinkedList<>());
        int[] degree = new int[N + 1];

        // N명의 학생, M번 키 비교
        for(int i=0; i<M; i++) {
            // 학생 A가 학생 B를 앞선다
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            // A -> B
            graph.get(A).add(B);
            degree[B]++; // 진입 차수 증가
        }

        ArrayList<Integer> result = solution(degree);
        for(int x : result) System.out.print(x + " ");

    }
}