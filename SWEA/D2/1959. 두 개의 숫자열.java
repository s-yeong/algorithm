import java.io.*;
import java.util.*;


class Solution {
	// alt+s+a+g  : 매개변수 있는 생성자 생성
 public static void main(String args[]) throws Exception {
	 
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringBuilder sb = new StringBuilder();
   int T = Integer.parseInt(br.readLine().trim());
   
   for(int test_case = 1; test_case<=T; test_case++) {
	   
	   StringTokenizer st = new StringTokenizer(br.readLine());
	   int N = Integer.parseInt(st.nextToken());
	   int M = Integer.parseInt(st.nextToken());
	   
	   st = new StringTokenizer(br.readLine());
	   int[] arrN = new int[N];
	   for(int i=0; i<N; i++) arrN[i] = Integer.parseInt(st.nextToken());
	   
	   st = new StringTokenizer(br.readLine());
	   int[] arrM = new int[M];
	   for(int i=0; i<M; i++) arrM[i] = Integer.parseInt(st.nextToken());
	   
	   int answer=Integer.MIN_VALUE;
	   // N = 5 M=3 0 1 2    2 3 4
	   if(N>M) {
		   // M 옮기기
		   for(int i=M; i<N+1; i++) {
			   int sum = 0;
			   for(int j=0; j<i; j++) {
				   sum += arrN[j]*arrM[M-i+j];
			   }
			   answer = Math.max(answer,sum);
		   }
	   }
	   else {
		   // N 옮기기
		   for(int i=N; i<M+1; i++) {
			   int sum = 0;
			   for(int j=0; j<i; j++) {
				   sum += arrN[N-i+j]*arrM[j];
			   }
			   answer = Math.max(answer,sum);
		   }
	   }
	   
	   
	   
	   sb.append("#").append(test_case).append(" ").append(answer).append("\n");
   }
   System.out.println(sb);
 }
}