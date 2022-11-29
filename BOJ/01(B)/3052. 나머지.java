import java.util.*;
import java.io.*;

class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<10; i++) {
            int num = Integer.parseInt(br.readLine());
            int x = num%42;
            set.add(x);
        }

        System.out.println(set.size());

    }
}
