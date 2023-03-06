import java.io.*;
import java.security.DigestOutputStream;
import java.util.*;

class Main {

    public static void main(String args[]) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int T = Integer.parseInt(br.readLine());
       StringBuilder sb = new StringBuilder();
       while(T-->0) {
    	   StringTokenizer st = new StringTokenizer(br.readLine());
    	   int n = Integer.parseInt(st.nextToken());
    	   int m = Integer.parseInt(st.nextToken());
    		
    	   int[] A = new int[n];
    	   int[] B = new int[m];
    	   
    	   // A의 크기가 B보다 큰 쌍이 몇 개나 있는지
    	   st = new StringTokenizer(br.readLine());
    	   for(int i=0; i<n; i++) {
    		   A[i] = Integer.parseInt(st.nextToken());
    	   }
    	   
    	   st = new StringTokenizer(br.readLine());
    	   for(int i=0; i<m; i++) {
    		   B[i] = Integer.parseInt(st.nextToken());
    	   }
    	   
    	   // n*n => 시간초과 -> mlogm(정렬) + nlogm(이분탐색)
    	   
    	   // 이분탐색 위해 정렬
    	   Arrays.sort(B);
    	   int sum = 0;	// 합계
    	   for(int i=0; i<n; i++) {
    		   sum += binary_search(A[i], B);
    	   }
    	   
    	   sb.append(sum).append("\n");
       }
       	System.out.println(sb);
    }
    static int binary_search(int num, int[] B) {
 	   
 	   int res = 0;
 	   
 	   int lt = 0;
 	   int rt = B.length-1;
 	   
 	   while(lt <= rt) {
 		   
 		   int mid = (lt+rt)/2;
 		   
 		   // num이 B를 먹으면
 		   if(num > B[mid]) {
 			   res = mid+1;
 			   lt = mid+1;
 		   }
 		   else {
 			   rt = mid-1;
 		   }
 	   }
 	   
 	   return res;
    }
}