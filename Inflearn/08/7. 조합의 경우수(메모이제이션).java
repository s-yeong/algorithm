import java.util.Scanner;

public class Main {
    static int n, r;
    static int[][] dy;  // 메모이제이션


    public static int DFS(int n, int r) {
        if(dy[n][r]>0) return dy[n][r]; // 값이 저장되어 있으면 바로 리턴
        if(r == 0 || n == r)  return 1; // nC0, nCn
        else return dy[n][r] = DFS(n - 1, r - 1) + DFS(n - 1, r); // 값을 저장하고 리턴

    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        r = sc.nextInt();

        dy = new int[n + 1][r + 1];
        System.out.print(DFS(n, r));

    }
}