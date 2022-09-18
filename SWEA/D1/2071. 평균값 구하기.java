import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        int avg;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int sum = 0;
            for(int j=0; j<10; j++) {
                int x = sc.nextInt();
                sum += x;
            }
//            long avg = Math.round(sum / 10.0);
            avg = (int)((sum/10.0) + 0.5);
            System.out.printf("#%d %d%n",test_case, avg);
        }
    }
}
