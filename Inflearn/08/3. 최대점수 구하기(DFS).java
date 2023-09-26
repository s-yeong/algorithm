import java.util.Scanner;

public class Main {

    static int n, m;
    static int answer = Integer.MIN_VALUE;
    public static void DFS(int L, int time, int sum, int[] ps, int[] pt) {
        // ps - problem score, pt - problem time
        if(time>m) return;
        if(L==n) {
            answer = Math.max(answer, sum);
        } else {
            DFS(L + 1, time + pt[L], sum + ps[L], ps, pt);
            DFS(L + 1, time, sum, ps, pt);
        }
    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        int[] score = new int[n];
        int[] time = new int[n];

        for(int i=0; i<n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            score[i] = a;
            time[i] = b;
        }

        DFS(0, 0,0, score, time);
        System.out.println(answer);

    }
}