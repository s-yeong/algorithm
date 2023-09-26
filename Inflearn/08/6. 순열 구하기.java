import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] ch, pm;
    static int cnt = Integer.MAX_VALUE;


    public static void DFS(int L) {

        if(L == m) {    // 순열 완성
            for(int x : pm) System.out.print(x + " ");
            System.out.println();
        }
        else {

            for(int i=0; i<n; i++) {
                if(ch[i] == 0) {
                    ch[i] = 1;
                    pm[L] = arr[i];
                    DFS(L + 1);
                    ch[i] = 0;
                }
            }

        }



    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];
        ch = new int[n];
        pm = new int[m];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        DFS(0);

    }
}