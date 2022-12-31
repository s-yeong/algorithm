import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        int min = 10000;

        for(int i=N; i>=M; i--) {
            if(isPrime(i)) {
                sum += i;
                min = i;
            }
        }

        if(sum == 0) System.out.println(-1);
        else {
            System.out.println(sum);
            System.out.println(min);
        }
    }

    static boolean isPrime(int num) {

        if(num == 1) return false;
        for(int i=2; i<num; i++) {
            if(num%i == 0) return false;
        }
        return true;
    }
}