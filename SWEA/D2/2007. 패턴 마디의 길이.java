import java.io.*;
import java.util.*;


public class Main {

    static boolean solution(String str, String s) {

        // str에서 s가 반복되는지
        while(str.length() >= s.length()) {
            String tmp = str.substring(0, s.length());
            if(!tmp.equals(s)) return false;
            else {
                str = str.substring(s.length());
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for(int test_case = 1; test_case <= T; test_case++) {

            // 길이 30 문자열
            String str = br.readLine();

            int len = 1;

            while(len <= 10) {
                // 최소 마디 길이?
                String s = str.substring(0, len);
                if(solution(str, s)) break;
                else len++;
            }

            sb.append("#").append(test_case).append(" ").append(len).append("\n");
        }

        System.out.println(sb);
    }
}