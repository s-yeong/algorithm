import java.io.*;
import java.util.*;

public class Main {
    static int[] ch = new int[10];
    static boolean check0to9 (){
        boolean flag = true;
        for(int i=0; i<=9; i++) {
            if(ch[i] == 0) return false;
        }
        return flag;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            int xN;
            int cnt = 1;
            while(!(check0to9())) {
                xN = cnt * n;

                while(xN > 0) {
                    int tmp = xN % 10;  // 일의 자리 나옴
                    ch[tmp] = 1;
                    xN = xN / 10;
                }

                if(check0to9()) break;
                cnt++;
            }
            Arrays.fill(ch, 0);

            sb.append("#").append(i+1).append(" ").append(n*cnt).append("\n");
        }


        System.out.println(sb);


    }
}