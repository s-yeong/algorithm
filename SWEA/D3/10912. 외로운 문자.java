import java.util.*;
import java.io.*;

class Solution {

	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=0; test_case<T; test_case++) {
			
			String s = br.readLine();
			String answer = "";
			
			char[] chars = s.toCharArray();
			
			Arrays.sort(chars);	// 정렬
			
			int i = 0;
			while(i<chars.length-1) {
				
				if(chars[i] == chars[i+1]) {
					i=i+2;
				}
				else {
					answer += chars[i];
					i++;
				}
			}
			
			// 앞에 문자가  짝짓지 않았으면		짝지었으면  -> i = chars.length
			if(i == chars.length-1) answer += chars[i];
			
			
			if(answer.equals("")) answer = "Good";
		
			sb.append("#").append(test_case+1).append(" ").append(answer).append("\n");
		}
		
	
		
		System.out.println(sb);

	}
}