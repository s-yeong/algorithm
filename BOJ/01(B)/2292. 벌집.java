import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n==1) System.out.println(1);
        else {
            int cnt = 1;
            int range = 2;
             while(range<=n) {
                 range = range + (cnt * 6);
                 cnt++;
             }
            System.out.println(cnt);
        }


    }
}
