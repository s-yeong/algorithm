import java.util.*;





class Solution
{

    public static void main(String args[])
    {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        char[] chars = str.toCharArray();

        for(char x : chars) {
            System.out.print((x-'A' +1) + " ");
        }




    }

}