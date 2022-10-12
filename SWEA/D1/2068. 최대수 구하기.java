import java.util.*;

class Solution
{
    public static void main(String args[])
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();




        for(int test_case = 1; test_case <= T; test_case++)
        {
            int max = Integer.MIN_VALUE;
            for(int i=0; i<10; i++) {
                int x = sc.nextInt();
                max = Math.max(max, x);
            }
            System.out.print("#" + test_case + " ");
            System.out.println(max);
        }
    }
}