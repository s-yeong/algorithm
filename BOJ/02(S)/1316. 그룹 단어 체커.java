import java.io.*;
import java.util.*;

class Main {
    static String explosion;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for(int tc=0; tc<N; tc++) {
            String str = br.readLine();

            boolean[] ch = new boolean['z' - 'a'+1];
            char point = ' ';
            boolean flag = true;
            for(char x : str.toCharArray()) {

                // 방문안했거나, 연속 단어인 경우
                if(!ch[x-'a'] || point == x) {
                    ch[x-'a'] = true; // 방문 체크
                }
                // 방문했는데 연속 단어X
                else flag = false;

                point = x;  // 현재 지점
            }
            if(flag) answer++;
        }
        System.out.println(answer);
    }
}