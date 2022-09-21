import java.util.Scanner;

public class Main2 {

    public static int solution(int[] arr) {

        int answer = 0;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<arr.length; i++) {
            if(arr[i] > max) {
                answer++;
                max = arr[i];
            }
        }





        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();
        int[] intArr = new int[n];

        for(int i=0; i<n; i++) {
            intArr[i] = sc.nextInt();
        }


        System.out.println(solution(intArr));

    }
}