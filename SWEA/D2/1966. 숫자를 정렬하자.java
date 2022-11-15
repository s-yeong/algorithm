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
	  
	  int[] arr = new int[n];
	  StringTokenizer st= new StringTokenizer(br.readLine());
	  for(int i=0; i<n; i++) {
		  arr[i] = Integer.parseInt(st.nextToken());
	  }
	  
	  for(int i=0; i<n-1; i++) {
          int idx = i;

          for(int j=i+1;j<n; j++) {
              if(arr[j] < arr[idx]) idx = j;
          }

          int tmp = arr[i];
          arr[i] = arr[idx];
          arr[idx] = tmp;
      }
	   
	  sb.append("#").append(tc).append(" ");
	  for(int x : arr) sb.append(x + " ");
	  sb.append("\n");
   }
   System.out.println(sb);
 }
}