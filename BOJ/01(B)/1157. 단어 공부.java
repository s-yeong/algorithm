import java.util.*;
import java.io.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str = str.toUpperCase();
        // 여러개 존재하는 경우 ? 출력

        HashMap<Character, Integer> map = new HashMap<>();
        for(char x : str.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        char answer = ' ';

        int max = Integer.MIN_VALUE;
        for(char key : map.keySet()) {
            if(map.get(key) > max) {
                max = map.get(key);
                answer = key;
            }
            else if(map.get(key) == max) {
                answer = '?';
            }
        }

        System.out.println(answer);

    }
}
