import java.io.*;
import java.util.*;

public class Main {

    // 색종이가 놓이는 평면은 가로 최대 1001칸, 세로 최대 1001칸으로 구성된 격자 모양
    static int[][] board = new int[1001][1001];
    static int cnt = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            cnt++;

            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());   // 너비
            int h = Integer.parseInt(st.nextToken());   // 높이
            for(int j=x; j<x+w; j++) {
                for(int k=y; k<y+h; k++) {
                    board[k][j] = cnt;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<n+1; i++) {
            int answer = 0;
            for(int j=0; j<1001; j++) {
                for(int k=0; k<1001; k++) {
                    if(board[k][j] == i) answer++;
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}