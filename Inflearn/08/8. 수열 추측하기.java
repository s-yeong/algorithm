import java.util.Scanner;

public class Main {
    static int n, f;
    static int[] b, p, ch;
    static boolean flag = false;   // 재귀 끝나면 멈추게 하기 위헤
    static int[][] dy = new int[35][35];    // 메모이제이션

    public static int combi(int n, int r) {
        // nCr
        if(dy[n][r] > 0) return dy[n][r];
        if(r == 0 || n == r) return 1;
        else return dy[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
    }

    public static void DFS(int L, int sum) {
        if(flag) return;    // 다른 호출을 막기 위해 다 return 시킴
        if(L == n) {
            if(sum == f) {
                for(int x : p) System.out.print(x + " ");
                flag = true;    // 답을 찾은 경우 true
            }
        }
        else {

            for(int i=1; i<=n; i++) {   // i 자체가 숫자를 만드는 순열(앞에 있었던 인덱스 번호X)
                if(ch[i] == 0) {
                    ch[i] = 1;
                    p[L] = i;
                    DFS(L + 1, sum + (b[L] * p[L]));
                    ch[i] = 0;
                }

            }
        }

    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        f = sc.nextInt();

        b = new int[n];
        p = new int[n];
        ch = new int[n + 1];

        // n-1C0 부터 n-1Cn-1까지 나열한다
        for(int i=0; i<n; i++) {
            b[i] = combi(n - 1, i);
        }

        DFS(0, 0);


    }
}