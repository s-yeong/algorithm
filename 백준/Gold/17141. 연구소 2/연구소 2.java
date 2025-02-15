import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] board;
    static final int BLANK=0, BRICK=1, VIRUS=2;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<int[]> list;
    static int blankCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        board = new int[n][n];
        blankCount = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == VIRUS) {
                    list.add(new int[]{i, j});
                }
                else if (board[i][j] == BLANK) {
                    blankCount++;
                }
            }
        }
        blankCount += list.size() - m;
        
        // 빈 칸이 없는 경우
        if(blankCount == 0) {
            System.out.println(0);
            System.exit(0);
        }

        combi = new int[m];
        answer = Integer.MAX_VALUE;
        recur(0, 0);

        if(answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }
    static int answer;
    static int[] combi;
    static void recur(int depth, int start) {
        if(depth == m) {
            int minTime = bfs();
            if(minTime != -1 && answer > minTime) {
                answer = minTime;
            }
            return;
        }
        for(int i=start; i<list.size(); i++) {
            combi[depth] = i;
            recur(depth+1, i+1);
        }
    }
    static int bfs() {

        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                temp[i][j] = board[i][j];
                if(temp[i][j] == VIRUS) {
                    temp[i][j] = BLANK;
                }
            }
        }

        Queue<int[]> Q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            int[] pos = list.get(combi[i]);
            temp[pos[0]][pos[1]] = VIRUS;
            Q.offer(pos);
        }

        int minLen = 0;
        int count = 0;
        while(!Q.isEmpty()) {
            int size = Q.size();
            while (size-- > 0) {
                int[] cur = Q.poll();
                for(int dir=0; dir<4; dir++) {
                    int nextRow = cur[0] + dr[dir];
                    int nextCol = cur[1] + dc[dir];

                    if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                        continue;
                    }

                    if(temp[nextRow][nextCol] == BLANK) {
                        count++;
                        temp[nextRow][nextCol] = VIRUS;
                        Q.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
            minLen++;
            if(count == blankCount) {
                return minLen;
            }
        }
        return -1;
    }
}