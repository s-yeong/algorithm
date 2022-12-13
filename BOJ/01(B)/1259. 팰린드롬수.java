import java.io.*;
import java.util.*;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {

            String num = br.readLine();
            if(Integer.parseInt(num) == 0) break;

            String tmp = new StringBuilder(num).reverse().toString();
            if(num.equals(tmp)) sb.append("yes");
            else sb.append("no");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
