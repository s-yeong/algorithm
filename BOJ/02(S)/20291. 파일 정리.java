import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];

        for(int i=0; i<N; i++) {
            String[] split = br.readLine().split("\\.");
            arr[i] = split[1];
        }

        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        for(int i=1; i<N; i++) {
            if(arr[i].equals(arr[i-1])) {
                cnt++;
            }
            else {
                sb.append(arr[i-1]).append(" ").append(cnt).append("\n");
                cnt = 1;
            }
        }

        // last
        sb.append(arr[N-1]).append(" ").append(cnt).append("\n");

        System.out.print(sb);
    }
}