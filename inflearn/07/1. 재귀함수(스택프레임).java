import java.util.*;

public class Main1 {

    public static void DFS(int n) {
        if(n==0) return ;
        else {
            DFS(n - 1); // 자기가 자기자신을 호출
            System.out.print(n + " ");
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//        int n = sc.nextInt();

        DFS(3);


    }
}