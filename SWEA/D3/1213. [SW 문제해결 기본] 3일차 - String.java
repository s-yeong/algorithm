import java.io.*;
import java.util.*;


public class Solution {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= 10; test_case++) {

            int test_num = Integer.parseInt(br.readLine());
            
            // 찾는 문자열
            String fs = br.readLine();
            
            // 검색할 문장
            String str = br.readLine();
            
            int answer = 0;
            int idx;
            while((idx = str.indexOf(fs)) != -1) {
            	
            	answer++;
            	str = str.substring(idx+fs.length()-1);
            }
           
           
            
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}