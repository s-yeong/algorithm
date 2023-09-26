import java.util.Scanner;

public class Main7 {
    
    public static String solution(String str) {

        String answer = "";

        str = str.toUpperCase();

        int len = str.length();
        for(int i=0; i<len/2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) return "NO";
        }
        return "YES";
/*
        if(str.equalsIgnoreCase(new StringBuilder(str).reverse().toString())) {
            answer = "YES";
        } else answer = "NO";

*/

//        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        String str = sc.next();

        System.out.println(solution(str));

    }
}