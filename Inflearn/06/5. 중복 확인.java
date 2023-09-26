import java.util.Arrays;
import java.util.Scanner;

public class Main5 {
    public static char solution(int n, int[] arr) {

        char answer = 'U';  // unique

        Arrays.sort(arr);
        for(int i=0; i<n-1; i++) {
            if(arr[i] == arr[i+1]) return 'D';
        }

/*
        ArrayList<Integer> list = new ArrayList<>();
        for(int x : arr) {
            if(list.contains(x)) return 'D';
            else list.add(x);
        }
*/
        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = sc.nextInt();


        System.out.print(solution(n, arr));}


}