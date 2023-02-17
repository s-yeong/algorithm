import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Long, Integer> map = new HashMap<>();
        while(N-->0) {
            long num = Long.parseLong(br.readLine());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int max = 0;
        long answer = 0;
        for(long key : map.keySet()) {
            if(map.get(key) > max) {
                answer = key;
                max = map.get(key);
            }
            else if(map.get(key) == max && answer > key) answer = key;
        }
        System.out.println(answer);

    }
}