import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;

        for(int i=1; i<=N; i++) {
            int num = i;
            while(num%5 == 0) {
               num/=5;
               count++;
            }
        }

        System.out.println(count);
    }
}