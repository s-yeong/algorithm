import java.io.*;
import java.util.*;

public class Main {
    static char[][] board;
    static int answer = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1. 25C7
        //2. Y 3개 이하
        //3. 연속
        board = new char[5][5];
        for(int idx=0; idx<5; idx++) {
            board[idx] = br.readLine().toCharArray();
        }
        combi = new int[7];
        recur(0, 0);
        System.out.println(answer);
    }
    static int[] combi;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0,0,-1,1};
    static void recur(int start, int depth) {
        if(depth == 7) {
            int ycount = 0;
            List<int[]> path = new ArrayList<>();
            for(int i=0; i<7; i++) {
                int row = combi[i] / 5;
                int col = combi[i] % 5;

                if(board[row][col] == 'Y') {
                    ycount++;
                    if(ycount >= 4) return;
                }

                path.add(new int[]{row, col});
            }

            if (isConnected(path)) {
                answer++;
            }


            return;
        }
        for(int i=start; i<25; i++) {
            combi[depth] = i;
            recur(i+1, depth+1);
        }
    }
    static boolean isConnected(List<int[]> path) {
        Queue<int[]> Q = new ArrayDeque<>();
        boolean[] ch = new boolean[7];

        int count = 0;

        //init
        Q.offer(path.get(0));
        ch[0] = true;
        count++;

        while(!Q.isEmpty()) {
            int[] cur = Q.poll();

            for(int dir=0; dir<4; dir++) {
                int nextRow = cur[0] + dr[dir];
                int nextCol = cur[1] + dc[dir];

                for(int idx=0; idx<7; idx++) {
                    if(ch[idx]) continue;
                    int[] target = path.get(idx);

                    if(nextRow == target[0] && nextCol == target[1]) {
                        ch[idx] = true;
                        count++;
                        Q.offer(target);
                    }
                }
            }
            if(count == 7) {
                return true;
            }
        }
        return false;
    }
}