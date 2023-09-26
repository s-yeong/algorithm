import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
class Edge implements Comparable<Edge>{
    int vex;
    int cost;

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

    static int[] ch;
    public static int solution (ArrayList<ArrayList<Edge>> graph) {
        int answer = 0;

        PriorityQueue<Edge> pQ = new PriorityQueue<>();

        pQ.offer(new Edge(1, 0));   // 1번 정점으로 가는데 비용은 0

        while(!pQ.isEmpty()) {

            Edge tmp = pQ.poll();
            int ev = tmp.vex;
            if(ch[ev] == 0) {   // 회로가 되는 것을 방지
                ch[ev] = 1;
                answer += tmp.cost;
                for(Edge ob : graph.get(ev)) {  // 연결된 정보들 다 탐색
                    if(ch[ob.vex] == 0) pQ.offer(new Edge(ob.vex, ob.cost));
                    // 이미 큐에서 들어갔던 간선(이미 체크되어 있는 간선) 선택하면 X
                }
            }
        }


        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();   // 도시 개수
        int e = sc.nextInt();   // 도로의 개수

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>(); // 인접리스트
        for (int i = 0; i <= v; i++) graph.add(new ArrayList<Edge>());

        ch = new int[v + 1];

        for(int i=0; i<e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));   // 무방향이기 때문에 추가해줘야함!!!  <-> 다익스트라는 방향이 있음
        }

        System.out.print(solution(graph));



    }
}