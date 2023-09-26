import java.util.HashMap;
import java.util.Scanner;

public class Main2 {

    public static String solution(String a, String b) {

        String answer = "YES";

        HashMap<Character, Integer> map = new HashMap<>();
        for(char x : a.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for(char x : b.toCharArray()) {

            if(!map.containsKey(x) || map.get(x) == 0) return "NO";
            map.put(x, map.get(x) - 1);
        }

/*
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for(char x : a.toCharArray()) {
            map1.put(x, map1.getOrDefault(x, 0) + 1);
        }

        for(char x : b.toCharArray()) {
            map2.put(x, map2.getOrDefault(x, 0) + 1);
        }

        for(char key : map1.keySet()) {
            if(map1.get(key) != map2.get(key)) {
                answer = "NO";
                break;
            }
        }
*/

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String a = sc.next();   // 1 단어
        String b = sc.next();   // 2 단어


        System.out.println(solution(a,b));
    }

}