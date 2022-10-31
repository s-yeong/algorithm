import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());    // 1~1000

        for(int i=0; i<n; i++) {

            int[] a = new int[5];
            int[] b = new int[5];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());    // A 딱지의 총 개수
            for (int j = 0; j < num1; j++) a[Integer.parseInt(st.nextToken())]++;

            st = new StringTokenizer(br.readLine());
            int num2 = Integer.parseInt(st.nextToken());    // B 딱지의 총 개수
            for (int j = 0; j < num2; j++) b[Integer.parseInt(st.nextToken())]++;

            boolean flag = false;
            for(int j=4; j>=1; j--) {
                if(a[j] > b[j]) {
                    sb.append("A").append("\n");
                    flag = true;
                    break;
                } else if(a[j] < b[j]) {
                    sb.append("B").append("\n");
                    flag = true;
                    break;
                }
            }
            if(!flag) sb.append("D").append("\n");
        }

        System.out.println(sb);



    }
}