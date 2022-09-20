import java.util.Scanner;

public class Main10 {


    public static int[] solution(String s, char c) {

        // 문자열, 문자 분리
//        String[] split = str.split(" +");
//        String s = split[0];
//        char c = split[1].charAt(0);

        int[] answer = new int[s.length()];
        int p = s.length()-1;


        // 왼쪽 기준 포문
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == c) {
                p = 0;
                answer[i] = p;
            } else {
              p++;
              answer[i] = p;
            }
        }

        p = s.length()-1;
        // 오른쪽 기준 포문
        for(int i=s.length()-1; i >=0; i--) {
            if(s.charAt(i) == c) p = 0;
            else {
                p++;
                answer[i] = Math.min(answer[i], p);
            }
        }

/*

        for(int i=0; i<s.length(); i++) {
            int min = s.length()-1;

            for(int j=0; j<s.length(); j++) {

                int index = Math.abs(s.indexOf(c, j) - i);

                if(min > index) {
                    min = index;
                }
            }
            answer[i] = min;
        }
*/


        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        String str = sc.next();
        char c = sc.next().charAt(0);

        for(int x : solution(str, c)) {
            System.out.print(x + " ");
        }

    }
}