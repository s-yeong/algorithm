import java.util.*;





class Solution
{

    public static void main(String args[])
    {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i=1; i<=t; i++) {
            System.out.print("#" + i + " ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.print(a / b);
            System.out.println(" " + a % b);


        }



    }

}