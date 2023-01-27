import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();
        long add = -'a' + 1;
        long r = 31;
        long M = 1234567891;

        long sum = 0;
        for(int i=0; i<L; i++) {
            long mul = 1;
            for(int j=i; j>0; j--) {
                mul*=r;
                mul%=M;
            }
            long num = ((str.charAt(i) + add) * mul) % M;
            sum += num;
            sum %= M;
        }
        System.out.println(sum);
    }
}