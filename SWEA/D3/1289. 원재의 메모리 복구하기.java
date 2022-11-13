import java.io.*;
import java.util.*;

class Solution {
	
 public static void main(String args[]) throws Exception {
	 
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringBuilder sb = new StringBuilder();
   int T = Integer.parseInt(br.readLine());
   for(int test_case = 1; test_case<=T; test_case++) {
	   
	   // 메모리 원래상태
	   char[] chars = br.readLine().toCharArray();
	   int[] mem = new int[chars.length];
	   for(int i=0; i<chars.length; i++) mem[i] = chars[i] - '0';
	   
	   // 초기화 0000 -> 원래상태로 돌아가는데 최소 몇 번
	   // 0000 -> 0011
	   // 000 -> 111 -> 100
	   
	   // 처음에 1나오는 부분 찾아, 이후에 0으로바뀌는 부분 찾아, 이후에 1로 바뀌는 부분 찾아 ..
	   int cnt = 0;
	   int b=1;
	   for(int i=0; i<mem.length; i++) {
		   if(mem[i] == b) {
			    cnt++;
			    if(b==1) b = 0;
			    else b = 1;
		   }
	   }
	   
	   
	   
	   sb.append("#").append(test_case).append(" ").append(cnt).append("\n" );
   }
   System.out.println(sb);
   
   

   
 }
}