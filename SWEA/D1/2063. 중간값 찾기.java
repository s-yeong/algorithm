import java.util.*;

class Solution
{
    public static void main(String args[])
    {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // n 홀수, 9이상 199이하 정수
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        int len = arr.length;
        System.out.println(arr[len/2]);
    }
}