import java.util.Scanner;

public class Main2 {

    public static int[] solution(int n, int[] arr) {

        int[] answer;
        for(int i=n-1; i>0; i--) {  // for(int i=0; i<n-1; i++)
            for (int j = 0; j < i; j++) {   // for(int j=0; j<n-1-i; j++)
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        answer = arr;

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = sc.nextInt();


        for(int x : solution(n, arr)) {
            System.out.print(x + " ");
        }
    }

}