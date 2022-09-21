import java.util.Scanner;

public class Main3 {

    public static String solution(int n, int[] arrA, int[] arrB) {

        String answer = "";



        for(int i=0; i<n; i++) {
            // 비기는 경우
            if(arrA[i] == arrB[i]) answer += "D";
            // A가 이기는 경우
            else if(arrA[i] == 1 && arrB[i] == 3) answer += "A";
            else if(arrA[i] == 2 && arrB[i] == 1) answer += "A";
            else if(arrA[i] == 3 && arrB[i] == 2) answer += "A";
            /*else if((arrA[i] > arrB[i] && !(arrA[i] == 3 && arrB[i] == 1)) || (arrA[i] == 1 && arrB[i] == 3)) {
                answer[i] = 'A';
            }*/
            // A가 지는 경우
            else answer += 'B';
        }


        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();
        int[] intArrA = new int[n];
        int[] intArrB = new int[n];

        for(int i=0; i<n; i++) {
            intArrA[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++) {
            intArrB[i] = sc.nextInt();
        }

        for(char x : solution(n,intArrA,intArrB).toCharArray()) {
            System.out.println(x);
        }

    }
}