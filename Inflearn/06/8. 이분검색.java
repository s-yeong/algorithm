import java.util.Arrays;
import java.util.Scanner;

public class Main8 {
    public static int solution(int n, int m, int[] arr) {

        int answer = -1;

        Arrays.sort(arr);

        int lt = 0;
        int rt = arr.length;

        while(lt<=rt) {
            int mid=(lt+rt)/2;
            if(arr[mid] == m) return mid+1;
            else if(m > arr[mid]) {
                lt = mid+1;
            } else {
                rt = mid-1;
            }
        }

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print(solution(n,m,arr));


    }
}