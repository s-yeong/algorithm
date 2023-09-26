import java.util.Scanner;


public class Main {

   static int n, m;
   static int[] unf;    // 집합 표현 -> 인덱스 번호가 학생 번호, 값은 집합의 번호

   public static int Find(int v) {  // v라는 학생의 집합 번호 리턴 -> 외우기
       if(v==unf[v]) return v;
       else return unf[v] = Find(unf[v]);   // 경로 압축
   }

   public static void Union(int a, int b) {
    int fa = Find(a);
    int fb = Find(b);
    if(fa != fb) unf[fa] = fb;
   }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) unf[i] = i;    // 초기화 - n개의 집합을 만듬
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            Union(a, b);
        }   // -> 서로소 집합으로 만들어짐

        int a = sc.nextInt();
        int b = sc.nextInt();
        int fa = Find(a);
        int fb = Find(b);

        if(fa == fb) System.out.println("YES");
        else System.out.println("NO");


    }
}