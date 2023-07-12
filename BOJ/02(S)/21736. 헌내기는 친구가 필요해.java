import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] campus;
    static int count = 0;
    static boolean[][] ch;
    static int x,y;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /**
         * 캠퍼스 크기 : n x m
         * O:빈공간, X:벽, I:도연이, P:사람
         * => 도연이가 만날 수 있는 사람의 수 구하기, 아무도 못만날 경우 TT 출력
         * => dfs, bfs
         */

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        campus = new char[n][m];
        ch = new boolean[n][m];


        for(int i=0; i<n; i++) {
            campus[i] = br.readLine().toCharArray();

            for(int j=0; j<m; j++) {
                if(campus[i][j]=='I') {
                    x = j;
                    y = i;
                }
            }
        }

        // dfs
        ch[y][x] = true;
        dfs(x,y);
        if(count == 0) {
            System.out.println("TT");
        }
        else {
            System.out.println(count);
        }

    }

    // 상하좌우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static void dfs(int x, int y) {

        for(int i=0; i<4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx < m && ny < n && !ch[ny][nx] && campus[ny][nx] != 'X') {

                if(campus[ny][nx] == 'P') {
                    count++;
                }
                ch[ny][nx] = true;
                dfs(nx, ny);
            }

        }

    }


}