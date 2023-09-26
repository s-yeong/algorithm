import java.util.Scanner;

public class Main2 {

    public static String solution(String str) {
        String answer = "";
        for(char x : str.toCharArray()) {
            if(Character.isLowerCase(x)) {
//          if(x >= 'a' && x <= 'z') {
                answer += Character.toUpperCase(x);
//                answer += (char)(x - ('a' - 'A'));
            }
            else {
                answer += Character.toLowerCase(x);
//                answer += (char)(x + ('a' - 'A'));
            }


        }

        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        String str = sc.next();

        System.out.println(solution(str));
    }
}