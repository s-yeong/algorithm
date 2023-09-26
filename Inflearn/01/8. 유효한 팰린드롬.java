import java.util.Scanner;

public class Main8 {

    public static String solution(String str) {

        String answer = "NO";
        str = str.toUpperCase().replaceAll("[^A-Z]", "");
        String tmp = new StringBuilder(str).reverse().toString();
        if(str.equals(tmp)) answer = "YES";
/*

        String answer = "YES";
        int lt = 0, rt = str.length() - 1;

        str = str.toUpperCase();
        char[] chars = str.toCharArray();

        while(lt < rt) {

            if(!Character.isAlphabetic(chars[lt])) {
                lt++;
            } else if (!Character.isAlphabetic(chars[rt])) {
                rt--;
            } else {
                if(chars[lt] != chars[rt]) {
                    answer = "NO";
                    break;
                } else {
                    lt++;
                    rt--;
                }

            }

        }
*/

        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        String str = sc.nextLine();

        System.out.println(solution(str));

    }
}