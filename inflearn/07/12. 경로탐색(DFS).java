import java.util.Scanner;


public class Main {

    static int n,m,answer=0;
    static int[][] graph;
    static int[] ch;

    public static void DFS(int v) {

        if(v == n) answer++;
        else {
            for(int i=1; i<=n; i++) {
                // 현재 v 정점에서 i 정점으로, i 방문 안했을시
                if(graph[v][i] == 1 && ch[i] == 0) {
                    ch[i] = 1;  // 체크
                    DFS(i);
                    // 백 한 시점
                    ch[i] = 0;  // 체크 풀어주기
                }
            }
        }
    }





    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        graph = new int[n+1][n+1];
        ch = new int[n + 1];
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
        }

        ch[1] = 1;  // 출발점 체크
        DFS(1); // 출발점 넘김
        System.out.println(answer);

    }
}