import java.io.*;
import java.util.*;


public class Solution {
	static int n,m;
	
	static int solution(int L, int sum) {
		
		if(L == m) return sum;
		else return solution(L+1, sum*n);
	}
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= 1; test_case++) {

            int test_num = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            
            // nÀÇ  m °ÅµìÁ¦°ö => Àç±Í È£ÃâÀ» ÀÌ¿ëÇÏ¿© ±¸Çö
            
            // 2ÀÇ 5½Â
            int answer = solution(0, 1);
            
        	
            
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}