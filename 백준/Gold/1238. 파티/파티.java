import java.io.*;
import java.util.*;

/**
 * 1238. 파티
 * 1. N개의 숫자로 구분된 마을에 한 명의 학생 삼
 * 2. N명의 학생이 X번 마을에 모여서 파티를 벌이기로 함
 * 3. 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나가는데 Ti 만큼 소비함
 * => N명의 학생들 X번 마을에 갔다가 다시 마을로 오는데 최단 거리로 이동
 * => 가장 많은 시간을 소비한 학생 시간 구하기
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        List<List<Node>> graph = new ArrayList<>();
        for(int idx=0; idx<=n; idx++) {
            graph.add(new ArrayList<>());
        }
        for(int count=0; count<m; count++) {
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }


        int[][] dis = new int[n+1][n+1];
        for(int idx=1; idx<=n; idx++) {
            for(int target=1; target<=n; target++) {
                if(idx==target) continue;
                dis[idx][target] = Integer.MAX_VALUE;
            }
        }

        // 모든 지점에서 다익스트라
        for(int idx=1; idx<=n; idx++) {
            dijkstra(idx, dis, graph);
        }

        int answer = 0;
        // 각 지점 에서 x 거리 + x에서 각 지점 거리 구한 후 최대값 구하기
        for(int idx=1; idx<=n; idx++) {
            answer = Math.max(answer, dis[idx][x] + dis[x][idx]);
        }
        System.out.println(answer);
    }

    static void dijkstra(int start, int[][] dis, List<List<Node>> graph) {

        PriorityQueue<Node> pQ = new PriorityQueue<>();
        pQ.offer(new Node(start, 0));

        while(!pQ.isEmpty()) {

            Node cur = pQ.poll();

            if(dis[start][cur.vertex] < cur.cost) continue;

            for(Node next : graph.get(cur.vertex)) {

                if(dis[start][next.vertex] > dis[start][cur.vertex] + next.cost) {
                    dis[start][next.vertex] = dis[start][cur.vertex] + next.cost;
                    pQ.offer(new Node(next.vertex, dis[start][next.vertex]));
                }
            }
        }
    }


    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
}