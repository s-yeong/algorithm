import java.util.Scanner;

public class Main9 {

    public static int solution(String str) {
        String answer = "";
        for(char x : str.toCharArray()) {
            if(Character.isDigit(x)) {
                answer += x;
            }
        }
/*
        int answer = 0;
        char[] chars = str.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if(chars[i] >= '0' && chars[i] <= '9') {
                answer = answer * 10 + (chars[i] - '0');
            }
        }
*/

/*
        String answer = ""
        answer = str.replaceAll("[^0-9]", "");

        // 앞에 숫자가 0인지 아닌지 확인 (정수로 바꾸게 되면 앞에 숫자 0은 없어짐)
        while(answer.charAt(0) == '0') {
            answer = answer.substring(1);
        }
*/


        return Integer.parseInt(answer);
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        String str = sc.next();

        System.out.println(solution(str));

    }
}