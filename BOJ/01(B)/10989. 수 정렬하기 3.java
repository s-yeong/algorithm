
import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] counting = new int[10001];

        for (int i = 0; i < N; i++) {
            counting[Integer.parseInt(br.readLine())]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < counting.length; i++) {
            if(counting[i] == 0) continue;
            while(counting[i] --> 0) sb.append(i).append("\n");
        }

        System.out.print(sb);
    }
}