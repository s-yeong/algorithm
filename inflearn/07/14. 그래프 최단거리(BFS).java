import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int n, m;
    static ArrayList<ArrayList<Integer>> graph; // ArrayList<Integer>를 저장하는 ArrayList
    static int[] ch, dis;

    public static void BFS(int v) {

        Queue<Integer> queue = new LinkedList<>();
        ch[v] = 1;
        dis[v] = 0;
        queue.offer(v); // 1번 정점 넣기
        while(!queue.isEmpty()) {

            int cv = queue.poll();
            for(int nv : graph.get(cv)) {
                if(ch[nv] == 0) {   // nv는 이미 v에서 갈 수 있는 정점들이니까 "체크되어있는지"만 확인
                    ch[nv] = 1;
                    queue.offer(nv);
                    dis[nv] = dis[cv] + 1;      // **** 중요 ****
                }
            }

        }


    }
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        graph = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<=n; i++) {   // 1번부터 n번까지 접근하기 위해서 n+1개 생성
            graph.add(new ArrayList<Integer>());    // 정수를 저장할 수 있는 ArrayList 객체를 생성해서 저장
        }

        ch = new int[n + 1];
        dis = new int[n + 1];

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }   // 인접리스트 만들기

        BFS(1);

        for(int i=2; i<=n; i++){
            System.out.println(i+" : "+dis[i]);
        }
    }
}