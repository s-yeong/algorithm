import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        // 최대 공약수, 최소 공배수
        ArrayList<Integer> arr = new ArrayList<>();

        int x=1;
        int max = 0;
        while(x<=num1 && x<=num2) {

            if(num1%x == 0 && num2%x == 0) {
                arr.add(x);
                max = Math.max(max, x);
            }
            x++;
        }

        // 최대 공약수
        sb.append(max).append("\n");
        sb.append((num1 * num2) / max).append("\n");
        System.out.print(sb);
    }
}