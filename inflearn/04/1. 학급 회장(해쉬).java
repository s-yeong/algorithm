import java.util.HashMap;
import java.util.Scanner;

public class Main1 {

    public static char solution(int n, String str) {

        char answer = ' ';

        HashMap<Character, Integer> map = new HashMap<>();

        for(char x : str.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int max = Integer.MIN_VALUE;
        for(char key : map.keySet()) {    // map 객체 key를 하나하나 탐색
//            System.out.println(key + " " + map.get(key));
            if(map.get(key) > max) {
                max = map.get(key);
                answer = key;
            }
        }

/*
        for(int i=0; i<n; i++) {
            if(map.containsKey(str.charAt(i))) {
                int value = map.get(str.charAt(i));
                map.put(str.charAt(i), value + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }

        int max = 0;
        for(int i=0; i<n; i++) {
            if(map.get(str.charAt(i)) > max) {
                max = map.get(str.charAt(i));
                answer = str.charAt(i);
            }
        }
*/

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 반 학생수

        String str = sc.next();

        System.out.println(solution(n,str));
    }

}