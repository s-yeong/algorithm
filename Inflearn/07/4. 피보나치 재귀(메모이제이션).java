import java.util.Scanner;

public class Main4 {
    static int[] fibo;
    public static int DFS(int n) {
        if(fibo[n] > 0) return fibo[n]; // 메모이제이션 활용
        if(n == 1) return fibo[n] = 1;
        else if(n == 2) return fibo[n] = 1;
        else return fibo[n] = DFS(n - 2) + DFS(n - 1);
        // 똑같이 리턴하는데 배열에 저장해서 리턴한다!

    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        fibo = new int[n + 1];
        DFS(n);
        for(int i=1; i<=n; i++) System.out.print(fibo[i] + " ");


    }
}