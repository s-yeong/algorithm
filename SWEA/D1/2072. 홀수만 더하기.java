import java.util.Scanner;

class Solution {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        final int T = s.nextInt();
        int num; // 0 이상 10000이하 정수
        int[] sum = new int[T];

        // 입력
        for(int i=0; i<T; i++) {
            for(int j=0; j<10; j++) {
                num = s.nextInt();
                // 홀수만 더함
                if(num % 2 != 0) {
                    sum[i] += num;
                }
            }
        }

        // 출력
        for(int i=1; i<=T; i++) {
            System.out.println("#" + i + " " + sum[i-1]);
        }



    }
}

