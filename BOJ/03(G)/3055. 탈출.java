import java.io.*;
import java.util.*;

class Main {
    static int R,C;
    static char[][] board;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        int x = 0, y = 0;
        Queue<int[]> water = new LinkedList<>();

        for(int i=0; i<R; i++) {
            board[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                // 고슴도치면,
                if(board[i][j] == 'S') {
                    x = j;
                    y = i;
                }
                // 물이면,
                if(board[i][j] == '*') {
                    water.offer(new int[]{j, i});
                }
            }
        }

        int answer = BFS(x, y, water);
        if(answer == -1) System.out.println("KAKTUS");
        else System.out.println(answer);

        // 빈곳:. 물:* 돌:X
        // 비버의굴:D 고슴도치:S

        // 고슴도치 이동 상하좌우
        // 물 상하좌우 빈곳으로 확장

        // 돌 -> 장애물
        // 고슴도치 : 물이 찰 예정인 칸, 물이 찬 칸 이동X, 돌 이동 -> 빈곳, 비버의굴만 이동 가능
        // 물 : 비버의굴, 돌 이동X -> 빈곳 이동 가능

        // 고슴도치가 비버의 굴로 이동하기 위한 최소 시간
        // 1. BFS
        // 2. 물 먼저 이동시키기
        // 3. 물 이동시키는 부분에 고슴도치가 있으면 어떡하지? -> 어차피 큐에 좌표가 있기 때문에 그부분도 물 표시 해주면 된다
    }
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] ch;
    static int BFS(int x, int y, Queue<int[]> water) {

        Queue<int[]> Q = new LinkedList<>();
        int L = 0;
        Q.offer(new int[]{x, y});
        ch = new boolean[R][C];
        ch[y][x] = true;

        while(!Q.isEmpty()) {
            fill(water);

            int len = Q.size();

            while(len --> 0) {
                int[] cur = Q.poll();
                for(int i=0; i<4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    if(nx>=0 && ny>=0 && nx<C && ny<R && !ch[ny][nx]) {
                        // 방문체크
                        ch[ny][nx] = true;

                        // 비버의 굴이면
                        if(board[ny][nx] == 'D') return L + 1;
                        // 이동 가능하면
                        else if(board[ny][nx] == '.') {
                            Q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            L++;
        }
        return -1;
    }

    // 물 채우기
    static void fill(Queue<int[]> water) {

        int len = water.size();
        while(len --> 0) {
            int[] cur = water.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 빈칸이거나 고슴도치 자리면
                if (nx >= 0 && ny >= 0 && nx < C && ny < R && (board[ny][nx] == '.' || board[ny][nx] == 'S')) {
                    board[ny][nx] = '*';
                    water.offer(new int[]{nx, ny});
                }
            }
        }
    }
}