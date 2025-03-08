import java.io.*;
import java.util.*;

public class Main {

    /**
     * 1. 이전 이동 기억하고 있어야함
     * - 이전 => 현재
     *  - 0 => 1
     *  - 1 => 2
     *  - 2 => 3
     *  - 3 => 0
     * 2. 정사각형 개수
     */
    static int n, g;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[102][102];

        for(int a=0; a<n; a++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            //0세대
            board[y][x] = 1;
            int[] next = nextPoint(x, y, d);
            board[next[1]][next[0]] = 1;

            List<Integer> beforeD = new ArrayList<>();
            beforeD.add(d);
            create(next[0], next[1], 1, beforeD);
        }

        int answer = 0;
        for (int i = 0; i <= 100; i++) {
            for(int j = 0; j <= 100; j++) {
                if(board[i][j] == 1 && board[i+1][j] == 1 && board[i][j+1] == 1 && board[i+1][j+1] == 1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
    static void create(int curX, int curY, int curG, List<Integer> beforeD) {

        if(curG == g+1) {
            return;
        }

        int index = beforeD.size() - 1;
        for(int i = index; i >= 0 ; i--) {

            int d = beforeD.get(i);
            int nextD = (d + 1) % 4;

            int[] next = nextPoint(curX, curY, nextD);
            board[next[1]][next[0]] = 1;

            beforeD.add(nextD);

            curX = next[0];
            curY = next[1];
        }

        create(curX, curY, curG+1, beforeD);
    }

    static int[] nextPoint(int x, int y, int d) {
        if(d==0) {
            x += 1;
        }
        else if(d==1) {
            y -= 1;
        }
        else if(d==2) {
            x -= 1;
        }
        else {
            y += 1;
        }

        return new int[]{x, y};
    }



}
