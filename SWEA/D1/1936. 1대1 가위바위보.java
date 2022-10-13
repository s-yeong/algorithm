import java.util.*;





class Solution
{

    public static void main(String args[])
    {

        Scanner sc = new Scanner(System.in);

        // 1 - 가위, 2 - 바위, 3 - 보
        int a = sc.nextInt();
        int b = sc.nextInt();

        // 이기는 경우 -> 1 3, 2 1, 3 2
        if((b-a+1)%3==0) {
            System.out.println('A');
        } else System.out.println('B');
    }

}