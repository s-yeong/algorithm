import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] combi, ch;

    public static void DFS(int L, int s) {

        if(L==m) {
            for(int x : combi) System.out.print(x + " ");
            System.out.println();
        }
        else {
            for(int i=s; i<=n; i++) {
                combi[L] = i;
                DFS(L+1, i + 1);
            }
        }


    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        combi = new int[m];
        ch = new int[n + 1];
        DFS(0, 1);
        // L - level, s - start



    }
}