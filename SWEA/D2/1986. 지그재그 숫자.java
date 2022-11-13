import java.io.*;
import java.util.*;


public class Solution {
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {

        	int n = Integer.parseInt(br.readLine());
        	int sum = 0;

        	// È¦¼ö ´õÇÏ°í Â¦¼ö –A´Ù
        	for(int i=1; i<=n; i++) {
        		if(i%2 == 0) sum -= i;
        		else sum += i;
        	}
            sb.append("#").append(test_case).append(" ").append(sum).append("\n");
    }
        System.out.println(sb);
  }
}