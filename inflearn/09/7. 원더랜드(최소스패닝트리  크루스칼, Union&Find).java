import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
    int v1;
    int v2;
    int cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}



public class Main {

    static int[] unf;

    public static int Find(int v) {

        if(unf[v] == v) return v;
        else return unf[v] = Find(unf[v]);
    }

    public static void Union(int a, int b) {

        int fa = Find(a);
        int fb = Find(b);

        if(fa != fb) unf[fa] = fb;
    }

    public static int solution (ArrayList<Edge> arr) {
        int answer = 0;

        Collections.sort(arr);
        for(Edge ob : arr) {
            if(Find(ob.v1) != Find(ob.v2)) {
                Union(ob.v1, ob.v2);
                answer += ob.cost;
            }
        }


        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();   // 도시 개수
        int e = sc.nextInt();   // 도로의 개수

        unf = new int[v + 1];   // 도시 집합 번호
        ArrayList<Edge> arr = new ArrayList<>();
        for (int i = 1; i <= v; i++) unf[i] = i;

        for(int i=0; i<e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            arr.add(new Edge(a, b, c));
        }

        System.out.print(solution(arr));



    }
}