import java.util.*;





class Solution
{

    public static void main(String args[])
    {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i=1; i<=t; i++) {

            String n = sc.next();


            System.out.print("#" + i + " ");

            String year = n.substring(0, 4);
            String month = n.substring(4, 6);
            String date = n.substring(6, 8);

            int monthD = Integer.parseInt(month);
            int dateD = Integer.parseInt(date);

            boolean flag = false;

            if(monthD == 1 || monthD == 3 || monthD == 5
                    || monthD == 7 || monthD == 8 || monthD == 10 || monthD == 12) {
                if(dateD <= 31) flag = true;
            } else if(monthD == 4 || monthD == 6 || monthD == 9 || monthD == 11) {
                if(dateD <= 30) flag = true;
            } else if(monthD == 2) {
                if(dateD <= 28) flag = true;
            }

            if(flag) {
                System.out.println(year + "/" + month + "/" + date);
            } else System.out.println(-1);

        }

    }

}