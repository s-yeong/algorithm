import java.io.*;
import java.util.*;


class Solution {

	// alt+s+a+g  : 매개변수 있는 생성자 생성
 public static void main(String args[]) throws Exception {
	 
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringBuilder sb = new StringBuilder();
   int T = Integer.parseInt(br.readLine().trim());
   
   for(int tc = 1; tc<=T; tc++) {
	   
	   StringBuilder s = new StringBuilder();
	   int N = Integer.parseInt(br.readLine());
	   for(int i=0; i<N; i++) {
		   StringTokenizer st = new StringTokenizer(br.readLine());
		   char c = st.nextToken().charAt(0);
		   int k = Integer.parseInt(st.nextToken());
		   for(int j=0; j<k; j++) s.append(c);
	   }
	   
	   sb.append("#").append(tc).append("\n");
	   int cnt = 0;
	   for(int i=0; i<s.length(); i++) {
		   cnt++;
		   sb.append(s.charAt(i));
		   if(cnt == 10) {
			   cnt = 0;
			   sb.append("\n");
		   }
	   }
	  sb.append("\n");
   }
   System.out.println(sb);
      
 }
}