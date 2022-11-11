import java.util.*;
import java.io.*;

class Main {

    static Queue<Integer> Q = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> graph;
    static void solution(int[] answer) {

        while(!Q.isEmpty()) {

            int v = Q.poll();
            for(int nv : graph.get(v)) {
                answer[nv] = Math.max(answer[nv], answer[v] + 1);
            }
        }
    }


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 과목 수
        int m = Integer.parseInt(st.nextToken());
        int[] answer = new int[n + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        Arrays.fill(answer, 1);
        for(int i=0; i<m; i++) {

            // A번 과목이 B번 과목의 선수 과목 A<B
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // A -> B
            graph.get(A).add(B);
        }

        for(int i=1; i<=n; i++) {
            if(graph.get(i).size() != 0) Q.offer(i); // 값이 존재하는 경우만 넣기
        }

        solution(answer);

        // 출력
        for(int i=1; i<=n; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}