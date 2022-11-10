import java.util.*;
import java.io.*;

class Solution {
	

	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=0; test_case<T; test_case++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());	// 퍼즐 가로,세로
			int k = Integer.parseInt(st.nextToken());	// 단어 길이
			
			int[][] arr = new int[n][n];
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;

			// 가로 체크  - 0 을 기준으로 split
			for(int i=0; i<n; i++) {
				String s1 = "";	// 가로
				String s2 = "";	// 세로
				for(int j=0; j<n; j++) {
					s1 += arr[i][j];
					s2 += arr[j][i];
				}
				String[] split1 = s1.split("0");
				String[] split2 = s2.split("0");
				for(String x : split1) if(x.length() == k) cnt++;
				for(String x : split2) if(x.length() == k) cnt++;
			}
								
			
			
			
			sb.append("#").append(test_case+1).append(" ").append(cnt).append("\n");
		}
		
	
		
		System.out.println(sb);

	}
}