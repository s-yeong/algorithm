import java.util.*;
import java.io.*;

class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            String S = st.nextToken();

            for(char x : S.toCharArray()) {
                    for(int j=0; j<R; j++) {
                        sb.append(x);
                    }
            }
            sb.append("\n");
        }
        System.out.println(sb);


    }
}
