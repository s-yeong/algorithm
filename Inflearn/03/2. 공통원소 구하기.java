import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    public static ArrayList<Integer> solution(int n, int m, int[] arrN, int[] arrM) {

        ArrayList<Integer> answer = new ArrayList<>();
        int p1=0, p2=0; // p1은 arrN배열을 가리키고, p2는 arrM 배열을 가리킨다.
        Arrays.sort(arrN);
        Arrays.sort(arrM);

        while(p1<n && p2<m) {   // p1이나 p2 둘 중 하나가 거짓이 되면 멈춤

            if(arrN[p1] == arrM[p2]) {
                answer.add(arrN[p1++]);
                p2++;
            }
            else if(arrN[p1] < arrM[p2]) p1++;
            else p2++;
        }


        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 배열의 크기
        int[] arrN = new int[n];

        for(int i=0; i<n; i++) {
            arrN[i] = sc.nextInt();  // 중복X
        }

        int m = sc.nextInt();
        int[] arrM = new int[m];
        for(int i=0; i<m; i++) {
            arrM[i] = sc.nextInt(); // 중복 X
        }

        for (int x : solution(n, m, arrN, arrM)) {
            System.out.print(x + " ");
        }
    }

}