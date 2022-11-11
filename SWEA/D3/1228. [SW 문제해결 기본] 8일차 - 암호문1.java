import java.io.*;
import java.util.*;


public class Solution {
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= 10; test_case++) {

        	// 암호문의 길이
            int n = Integer.parseInt(br.readLine());
            
            // 원본 암호문        
            ArrayList<Integer> arr = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int i=0; i<n; i++) arr.add(Integer.parseInt(st.nextToken()));
        	
            int m = Integer.parseInt(br.readLine());
            
            // 수정된 결과의 처음 10개
            // x y s => x : i위치 바로 다음에  y개의 숫자 삽입
            
            int cnt = 0;
           
            while(cnt < m) {
            	if(st.nextToken().equals("I")) cnt++;
            	
            	int x = Integer.parseInt(st.nextToken());
            	int y = Integer.parseInt(st.nextToken());
            	for(int i=0; i<y; i++) {
            		arr.add(x++, Integer.parseInt(st.nextToken()));
            	}
            }
            
        	
            
            sb.append("#").append(test_case).append(" ");
            for(int i=0; i<10; i++) sb.append(arr.get(i)).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}