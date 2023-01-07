import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long A = Integer.parseInt(br.readLine());
        long B = Integer.parseInt(br.readLine());
        long C = Integer.parseInt(br.readLine());

        long sum = A * B * C;

        HashMap<Integer, Integer> map = new HashMap<>();

        while(sum > 0) {

            int tmp = (int)sum % 10;
            sum = sum / 10;

            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }

        for(int i=0; i<=9; i++) {

            if(!map.containsKey(i)) System.out.println(0);
            else System.out.println(map.get(i));
        }

    }
}