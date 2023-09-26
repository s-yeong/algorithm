import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main7 {
    public static String solution(String need, String plan) {

        // need - 필수 과목 순서
        // plan - 현수가 짠 수업설계
        String answer = "YES";

        Queue<Character> Q = new LinkedList<>();

        for(char x : need.toCharArray()) Q.offer(x);

        for(char x : plan.toCharArray()) {

            if(Q.contains(x)) { // 필수과목인지 확인
                if(Q.poll() != x) return "NO";
            }
        }
        if(!Q.isEmpty()) return "NO";
/*
        char[] chars1 = need.toCharArray();
        Queue<Character> Q = new LinkedList<>();
        int i = 1;

        for(char x : plan.toCharArray()) {
            if(i<chars1.length && chars1[i] == x) {
                if(!Q.contains(chars1[i-1])) return "NO";
                i++;
            }
            Q.offer(x);
        }
        while(i< chars1.length) {
            if(!Q.contains(chars1[i])) return "NO";
        }
*/




        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        System.out.println(solution(str1, str2));
    }

}