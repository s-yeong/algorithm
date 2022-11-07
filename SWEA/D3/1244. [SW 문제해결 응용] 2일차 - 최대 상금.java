import java.io.*;
import java.util.*;


public class Main {
    static int cnt, answer;
    static char[] charN;

    static int charToInt(char[] charN) {
        StringBuilder sb = new StringBuilder();
        for (char c : charN) sb.append(c);
        return Integer.parseInt(sb.toString());
    }

    static void swap(int i, int j) {
        char tmp = charN[i];
        charN[i] = charN[j];
        charN[j] = tmp;
    }

    static void DFS(int L, int S) {

        if(L == cnt) {
            int num = charToInt(charN);
            answer = Math.max(answer, num);
        }
        else {
            for(int i=S; i<charN.length; i++) {
                for(int j=i+1; j<charN.length; j++) {

                    swap(i, j);

                    DFS(L + 1, i);

                    swap(i, j); // L값 다시 빽 했으니까 원래대로.
                }
            }
        }


    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String n = st.nextToken();

            cnt = Integer.parseInt(st.nextToken());

            charN = n.toCharArray();

            // 최대 교환 횟수 10, 최대 자릿수 6 => 동일한 위치 교환 반복할 수도 있음
            if(cnt > charN.length) cnt = charN.length;

            answer = 0;
            DFS(0, 0);

            sb.append("#").append(i + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);


    }
}