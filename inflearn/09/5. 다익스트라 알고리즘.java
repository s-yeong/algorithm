import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge implements Comparable<Edge> {

    public int vex;     // 정점
    public int cost;    // 비용

    public Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {

    static int n, m;
    static int[] dis;
    static ArrayList<ArrayList<Edge>> graph;    // Edge라는 객체를 저장할 수 있는 ArrayList를 저장하는 ArrayList

    public static void solution(int v) {

        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(v, 0));

        dis[v] = 0;
        while(!pQ.isEmpty()) {  // 다익스트라
            Edge tmp = pQ.poll();
            int now = tmp.vex;
            int nowCost = tmp.cost;
            if(nowCost > dis[now]) continue;
            // 나온 값이 dis에 저장된 값보다 크면 더 볼 필요가 없음
            // 그 전에 작은 값이 나와서 뻗어나가 봤기 때문에 볼 이유가 없음
            for(Edge ob : graph.get(now)) { // now와 연결된 것들의 간선 정보가 다 나옴
                if(dis[ob.vex] > nowCost+ob.cost) {
                    dis[ob.vex] = nowCost + ob.cost;
                    pQ.offer(new Edge(ob.vex, nowCost + ob.cost));
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new ArrayList<ArrayList<Edge>>();

        for(int i=0; i<=n; i++) {   // ArrayList는 0번부터 시작
            graph.add(new ArrayList<Edge>());   // 0번부터 n번까지 ArrayList 객체 생성
        }

        dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Edge(b, c));
        }

        solution(1);    // 1번 정점에서 출발
        for(int i=2; i<=n; i++) {
            if(dis[i] != Integer.MAX_VALUE) System.out.println(i + " : " + dis[i]);
            else System.out.println(i + " : impossible");
        }
    }
}