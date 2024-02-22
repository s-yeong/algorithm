import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 5972. 택배 배송
 *  1. 현서(헛간 1) -> 찬홍(헛간 N)
 *  2. N개의 헛간, M개의 양방향 길
 *  2-1. M개의 길에는 C_i 마리의 소가 있다.
 *  2-2. 소들의 길은 두 개의 떨어진 헛간인 A_i와 B_i 를 잇는다.
 *  2-3. 두 개의 헛간은 하나 '이상'의 길로 연결되어 있을 수도 있다. => 두 개의 길로도 연결되어 있을 수 있다.
 *
 * - 1에서 N까지 최소한의 소를 만나면서 지나가기
 * - 양방향 그래프
 * - 간선의 가중치가 양수이면서, 최소 값을 찾기 때문에 다익스트라
 * - O(ElogV)
 *
 */
public class Main {

    static int n,m;
    static List<List<Node>> graph;
    static boolean[] ch;
    static int[] dis;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        ch = new boolean[n+1];
        for(int idx=0; idx<=n; idx++) {
            graph.add(new ArrayList<>());
        }

        // 최단 거리 테이블 초기화
        dis = new int[n+1];
        for(int idx=2; idx<=n; idx++) {
            dis[idx] = Integer.MAX_VALUE;
        }

        for(int count=0; count<m; count++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 양방향
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        ch[1] = true;
        solve();
        System.out.println(dis[n]);
    }

    static void solve() {

        PriorityQueue<Node> pQ = new PriorityQueue<>();
        pQ.offer(new Node(1, 0));

        // 다익스트라
        while(!pQ.isEmpty()) {
            Node now = pQ.poll();

            // 우선순위 큐에서 나온 값이 dis에 저장된 값 보다 크면 볼 필요가 없다.
            // 그 전에 작은 값이 나와서 뻗어나가 봤기 때문이다.
            if(now.cost > dis[now.vex]) continue;

            for (Node next : graph.get(now.vex)) {
                if(dis[next.vex] > now.cost + next.cost) {
                    dis[next.vex] = now.cost + next.cost;
                    pQ.offer(new Node(next.vex, now.cost + next.cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int vex;
        int cost;

        public Node(int vex, int cost) {
            this.vex = vex;
            this.cost = cost;
        }

        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
}
