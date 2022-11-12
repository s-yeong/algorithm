import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for(int test_case = 1; test_case <= T; test_case++) {

            int answer = 1;

            String str = br.readLine().trim();

            int lt = 0;
            int rt = str.length()-1;
            while(lt<rt) {
                if(str.charAt(lt) != str.charAt(rt)) {
                    answer = 0;
                    break;
                }
                else {
                    lt++;
                    rt--;
                }
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}