import java.io.*;
import java.util.*;

/**
 * 4659. 비밀번호 발음하기
 * 1. 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
 * 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
 * 3. 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        while(true) {
            String input = br.readLine();
            if("end".equals(input)) {
                break;
            }

            boolean isAcceptable = true;
            boolean hasVowel = false;
            int vowelCount = 0;
            int consonantCount = 0;
            char temp = ' ';

            char[] chars = input.toCharArray();
            temp = chars[0];
            if(isVowel(temp)) {
                hasVowel = true;
                vowelCount++;
            } else {
                consonantCount++;
            }

            for(int idx=1; idx<chars.length; idx++) {

                char ch = chars[idx];

                if(ch == temp && ch != 'e' && ch != 'o') {
                    isAcceptable = false;
                    break;
                }

                if(isVowel(ch)) {
                    hasVowel = true;
                    vowelCount++;
                    consonantCount = 0;
                } else {
                    consonantCount++;
                    vowelCount = 0;
                }

                if(vowelCount == 3 || consonantCount == 3) {
                    isAcceptable = false;
                    break;
                }
                temp = ch;
            }   // for end


            sb.append("<" + input + "> is ");
            if(hasVowel && isAcceptable) {
                sb.append("acceptable.\n");
            } else {
                sb.append("not acceptable.\n");
            }
        }   // while end
        System.out.println(sb);
    }
    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}