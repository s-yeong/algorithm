import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        char[] chars = br.readLine().toCharArray();

        /**
         * N+1개의 I, N개의 O
         * I와 O이 교대로 나오는 문자열 Pn
         * => S안에 Pn이 몇 군데 포함되어 있는지
         * O(N*M) => 시간 초과
         * => I와O가 항상 붙어 있다고 생각
         *
         */

        int answer = 0;
        int count = 0;


        for(int i=0; i<m-1; i++) {

            if(chars[i] == 'I' && chars[i+1] == 'O') {
                count++;

                if (count == n && i + 2 < m && chars[i + 2] == 'I') {
                    answer++;
                    count--;
                }
                i++;
            }
            else count = 0;
        }

        System.out.println(answer);

    }
}