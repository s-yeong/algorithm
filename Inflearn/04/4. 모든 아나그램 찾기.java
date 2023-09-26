import java.util.HashMap;
import java.util.Scanner;

public class Main4 {

    public static int solution(String b, String a) {

        int answer = 0;

        HashMap<Character, Integer> amap = new HashMap<>();
        HashMap<Character, Integer> bmap = new HashMap<>();


        for(char x : a.toCharArray()) {
            amap.put(x, amap.getOrDefault(x, 0) + 1);
        }


        // 초기값 설정
        for (int i = 0; i < a.length() - 1; i++) {
            bmap.put(b.charAt(i), bmap.getOrDefault(b.charAt(i), 0) + 1);
        }
        int lt = 0;

        for(int rt = a.length()-1; rt < b.length(); rt++) {
            bmap.put(b.charAt(rt), bmap.getOrDefault(b.charAt(rt), 0) + 1);
            if(bmap.equals(amap)) answer++;

            bmap.put(b.charAt(lt), bmap.get(b.charAt(lt)) - 1);
            if(bmap.get(b.charAt(lt)) == 0) bmap.remove(b.charAt(lt));
            lt++;
        }





        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        String t = sc.next();

        System.out.println(solution(s, t));
    }

}