import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        int n = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];
        while(st.hasMoreTokens()) {
            arr[count++] = Long.parseLong(new StringBuilder(st.nextToken().trim()).reverse().toString());
        }
        while(count < n) {
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                arr[count++] = Long.parseLong(new StringBuilder(st.nextToken().trim()).reverse().toString());
            }
        }

        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}