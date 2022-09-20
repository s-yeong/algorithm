import java.util.Scanner;

public class Main5 {
    
    public static String solution(String str) {

        String answer = "";

        char[] chars = str.toCharArray();

        int lt = 0;
        int rt = chars.length - 1;
        // lt(left) rt(right)

        while(lt < rt) {
/*
            if(!Character.isAlphabetic(chars[lt])) {
                lt++;
                continue;
            }
            if(!Character.isAlphabetic(chars[rt])) {
                rt--;
                continue;
            }

            char tmp = chars[lt];
            chars[lt] = chars[rt];
            chars[rt] = tmp;
            lt++;
            rt--;
*/

            if(!Character.isAlphabetic(chars[lt])) lt++;
            else if(!Character.isAlphabetic(chars[rt])) rt--;
            else {
                char tmp = chars[lt];
                chars[lt] = chars[rt];
                chars[rt] = tmp;
            }


        }

        answer = String.valueOf(chars);


        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        String str = sc.next();

        System.out.println(solution(str));

    }
}