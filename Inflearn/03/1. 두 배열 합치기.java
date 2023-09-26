import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
    
    public static ArrayList<Integer> solution(int n, int m, int[] arrN, int[] arrM) {

        ArrayList<Integer> answer = new ArrayList<>();
        int p1=0, p2=0; //p1은 arrN배열을 가리키고, p2는 arrM 배열을 가리킨다.
        while(p1<n && p2<m) {   // p1이나 p2 둘 중 하나가 거짓이 되면 멈춤

            if(arrN[p1] < arrM[p2]) answer.add(arrN[p1++]);
            else answer.add(arrM[p2++]);
        }

        while(p1<n) answer.add(arrN[p1++]);
        while(p2<m) answer.add(arrM[p2++]);

/*
        int[] answer = new int[n + m];

        for(int i=0; i<n; i++) {
            answer[i] = arrN[i];
        }
        for(int i=n; i<n+m; i++) {
            answer[i] = arrM[i-n];
        }

        Arrays.sort(answer);
*/

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 배열의 크기
        int[] arrN = new int[n];

        for(int i=0; i<n; i++) {
            arrN[i] = sc.nextInt();  // 오름차순
        }

        int m = sc.nextInt();
        int[] arrM = new int[m];
        for(int i=0; i<m; i++) {
            arrM[i] = sc.nextInt(); // 오름차순
        }

        for (int x : solution(n, m, arrN, arrM)) {
            System.out.print(x + " ");
        }
    }

}