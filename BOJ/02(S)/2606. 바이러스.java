import java.util.*;

public class Main {

    static ArrayList<ArrayList<Integer>> graph; // 인접리스트 사용
    static int n,m;
    static int[] ch;    // 체크 배열을 통해 방문 체크
    static int cnt = 0; // 1번 컴퓨터를 통해 바이러스 걸리게 되는 컴퓨터 수

    // 전형적인 그래프 문제 - 무방향
    static void BFS(int v) {

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        ch[v] = 1;

        while(!Q.isEmpty()) {

            int len = Q.size();
            for(int i=0; i<len; i++) {
                int cv = Q.poll();

                for(int nv : graph.get(cv)) {
                    if(ch[nv] == 0) {
                        cnt++;
                        ch[nv] = 1;
                        Q.offer(nv);
                    }
                }
            }
        }


    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();   // 컴퓨터의 수

        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        m = sc.nextInt();   // 컴퓨터 쌍의 수

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }


        ch = new int[n + 1];

        BFS(1);

        System.out.println(cnt);


    }
}