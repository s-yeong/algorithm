import java.io.*;
import java.util.*;


public class Solution {

	static boolean pal(String s) {
		String tmp = new StringBuilder(s).reverse().toString();
		if(s.equals(tmp)) return true;
		else return false;
	}
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= 10; test_case++) {

            int test_num = Integer.parseInt(br.readLine());
            
            char[][] chars = new char[100][100];
            for(int i=0; i<100; i++) {
            	String s = br.readLine();
            	chars[i] = s.toCharArray();
            }
            
            int maxlen = 1;
            // 가로 검증, 세로 검증
            for(int i=0; i<100; i++) {
            	for(int lt=0; lt<100; lt++) {
            		for(int rt=0; rt<100; rt++) {
            			
            			if((rt-lt+1) < maxlen) continue;
            			
            			String s1 = "";	// 가로
            			String s2 = ""; // 세로

            			// lt부터 rt까지
            			for(int p=lt; p<=rt; p++) s1 += chars[i][p];
            			for(int p=lt; p<=rt; p++) s2 += chars[p][i];
            			if(pal(s1)) maxlen = rt-lt+1;
            			if(pal(s2)) maxlen = rt-lt+1;
            		}
            	}
            }
            
            
            sb.append("#").append(test_case).append(" ").append(maxlen).append("\n");
        }

        System.out.println(sb);
    }
}