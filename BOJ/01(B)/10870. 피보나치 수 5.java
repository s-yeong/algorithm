import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 재귀
        System.out.println(fibo(n));
    }

    static int fibo(int num) {

        if(num == 0) return 0;
        else if(num == 1) return 1;
        else {
            return fibo(num - 1) + fibo(num - 2);
        }
    }
}