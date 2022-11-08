import java.io.*;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            int cnt = 0;
            int num = i;

            while(num>0) {
                int tmp = num%10; // 1의 자리 나옴
                if(tmp == 3 || tmp == 6 || tmp == 9) cnt++;
                num = num/10;
            }


            if(cnt==0) System.out.print(i);
            else for(int j=0; j<cnt; j++) System.out.print("-");

            System.out.print(" ");
        }

    }
}