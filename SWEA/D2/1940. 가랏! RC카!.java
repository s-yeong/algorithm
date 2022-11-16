import java.io.*;
import java.util.*;


class Solution {

	// alt+s+a+g  : 매개변수 있는 생성자 생성
 public static void main(String args[]) throws Exception {
	 
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringBuilder sb = new StringBuilder();
   int T = Integer.parseInt(br.readLine().trim());
   for(int tc = 1; tc<=T; tc++) {
	   int answer = 0;
	   int N = Integer.parseInt(br.readLine());
	   // 0 : 현재 속도 유지
	   // 1 : 가속
	   // 2 : 감속
	   int v = 0; // 속도
	   for(int i=0; i<N; i++) {
		   StringTokenizer st = new StringTokenizer(br.readLine());
		   int com = Integer.parseInt(st.nextToken());
		   switch(com) {
		   case 0 : break;
		   case 1 : 
			   v += Integer.parseInt(st.nextToken());;
			   break;
		   case 2 :
			   v -= Integer.parseInt(st.nextToken());;
			   break;
		   }
		   answer += v;
	   }
	   
	   sb.append("#").append(tc + " ").append(answer + "\n");
   }
   System.out.println(sb);
      
 }
}