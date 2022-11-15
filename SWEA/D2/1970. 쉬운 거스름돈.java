import java.io.*;
import java.util.*;


class Solution {
	// alt+s+a+g  : 매개변수 있는 생성자 생성
 public static void main(String args[]) throws Exception {
	 
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringBuilder sb = new StringBuilder();
   int T = Integer.parseInt(br.readLine().trim());
   
   for(int tc = 1; tc<=T; tc++) {
	   
	  int n = Integer.parseInt(br.readLine());
	  // 최소 개수로 거슬러 주기 위하여 각 종류의 돈이 몇 개씩 필요한지
	  
	  sb.append("#").append(tc).append("\n");
	   
	  int[] arr = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	  for(int i=0; i<arr.length; i++) {
		  int answer=0;
		  if((n/arr[i]) != 0) {
			  answer = n/arr[i];
			  n -= answer*arr[i];
		  }
		  sb.append(answer).append(" ");
	  }
	   
	   
	   
	   sb.append("\n");
   }
   System.out.println(sb);
 }
}