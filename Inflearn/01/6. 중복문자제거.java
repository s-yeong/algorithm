import java.util.Scanner;

public class Main6 {
    
    public static String solution(String str) {

        String answer = "";

        for(int i=0; i<str.length(); i++) {
//            System.out.println(str.charAt(i) +" "+ i + " " + str.indexOf(str.charAt(i)));
            if(i == str.indexOf(str.charAt(i))) answer += str.charAt(i);
        }

/*
        char[] chars = str.toCharArray();

        for(int i=0; i<chars.length; i++) {

            for(int j=i+1; j<chars.length; j++) {
                if(chars[i] != ' ') {
                    if (chars[i] == chars[j]) {
                        chars[j] = ' ';
                    }
                }
            }
            if(chars[i] != ' ') answer += chars[i];
        }
*/


        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        String str = sc.next();

        System.out.println(solution(str));

    }
}