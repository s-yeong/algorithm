import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {

    public static ArrayList<Integer> solution(int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(arr[0]);

//        String answer = arr[0] + " ";

        for(int i=1; i<arr.length; i++) {

            if(arr[i] > arr[i-1]) {
//                answer += arr[i] + " ";
                answer.add(arr[i]);
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