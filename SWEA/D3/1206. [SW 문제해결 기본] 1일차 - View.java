import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=10; i++) {
            int num = Integer.parseInt(br.readLine());  // 건물의 개수

            int[] arr = new int[num];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<num; j++) {
                arr[j] = Integer.parseInt(st.nextToken());  // 건물의 높이
            }

            int sum = 0;
            for(int j=2; j<num-2; j++) {

                // 건물들은 왼쪽 두개 오른쪽 두개 명향

                if((arr[j] - arr[j-1] > 0) && (arr[j] - arr[j-2] > 0) && (arr[j] - arr[j+1] > 0) && (arr[j] - arr[j+2] > 0)) {

                    int tmp = Math.max(arr[j - 1], arr[j - 2]);
                    tmp = Math.max(tmp, arr[j + 1]);
                    tmp = Math.max(tmp, arr[j + 2]);

                    sum += arr[j] - tmp;

                }
            }


            sb.append("#").append(i).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);


    }
}