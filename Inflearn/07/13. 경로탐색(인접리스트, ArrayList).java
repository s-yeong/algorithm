import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> graph; // ArrayList<Integer>를 저장하는 ArrayList
    static int[] ch;
    static int answer;

    public static void DFS(int v) {

        if(v == n) answer++;
        else {
            for(int nv : graph.get(v)){ // next v
                // v번 ArrayList
                if(ch[nv] == 0) {
                    ch[nv] = 1;
                    DFS(nv);
                    ch[nv] = 0;
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

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }

        ch = new int[n + 1];

        ch[1] = 1;
        DFS(1);

        System.out.println(answer);
    }
}