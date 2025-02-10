import java.io.*;
import java.util.*;

public class Main {

    static int n;
    // 이동 방법
    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int srow = Integer.parseInt(st.nextToken());
            int scol = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int erow = Integer.parseInt(st.nextToken());
            int ecol = Integer.parseInt(st.nextToken());
            int answer = bfs(srow, scol, erow, ecol);
            sb.append(answer + "\n");
        }
        System.out.print(sb);
    }

    static int bfs(int srow, int scol, int erow, int ecol) {
        Queue<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[]{srow, scol});
        boolean[][] ch = new boolean[n][n];
        int minLen = 0;

        while(!Q.isEmpty()) {
            int len = Q.size();

            while(len--> 0) {
                int[] cur = Q.poll();

                if (cur[0] == erow && cur[1] == ecol) {
                    return minLen;
                }

                for (int dir = 0; dir < 8; dir++) {
                    int nextRow = cur[0] + dr[dir];
                    int nextCol = cur[1] + dc[dir];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n || ch[nextRow][nextCol]) {
                        continue;
                    }
                    ch[nextRow][nextCol] = true;
                    Q.offer(new int[]{nextRow, nextCol});
                }
            }
            minLen++;
        }
        return minLen;
    }
}