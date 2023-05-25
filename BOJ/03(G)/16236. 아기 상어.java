import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    static int shark_size = 2;
    static int shark_count = 0;
    static int[] shark_pos;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        shark_pos = new int[2];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9) {
                    shark_pos[0] = j;
                    shark_pos[1] = i;
                    board[i][j] = 0;
                }
            }
        }

        /**
         * 1. 처음 아기 상어 크기 : 2, 1초에 상하좌우 인접한 한 칸 이동
         * 2-1. 자신의 크기보다 작은 물고기만 먹을 수 있고
         * 2-2. 자신의 크기와 같은 물고기는 지나갈 수 있고
         * 2-3. 자신의 크기보다 큰 물고니는 지나가지 못한다.
         * 3. 이동 순서
         * 3-1. 더 이상 먹을 수 있는 물고기가 공간에 없다 -> 종료
         * 3-2. 먹을 수 있는 물고기 1마리 -> 먹으러 감
         * 3-3. 먹을수 있는 물고기 1마리 이상 -> 거리 순
         *      3-3-1. 거리는 지나야하는 칸의 개수의 최소값
         *      3-3-2. 가까운 물고기가 많을시 -> 가장 위에 있는 물고기 -> 가장 왼쪽에 있는 물고기 순
         * 4. 아기 상어가 먹은 물고기 칸은 빈 칸이됨 (=> 0으로 두기)
         * 4-1. 아기 상어의 `크기`와 `같은 수의` 물고기를 먹을 때 마다 크기가 1 증가 (=> shark_size, shark_count)
         */

        /**
         * 몇 초 동안 물고기를 잡아먹을 수 있는지 구하기
         * 아기 상어 위치 : 9
         * 물고기의 크기가 커지기 때문에 매번 최단 거리가 달라질 수 있음
         * => 따라서 먹을 수 있는 물고기를 매번 체크해줘야함
         * => 최소 거리 물고기 먹은 아기 상어 위치 저장
         *
         * 1. bfs 돌리면서 아기 상어 움직이기
         * 2. 최소인 값이 여러개인 경우에 비교해야 하기 때문에 min_x, min_y 두기
         * 3. 먹을 수 있는 물고기 있을 때 까지 반복문 돌리기
         */

        int answer = 0;
        while(true) {

            // 최소 거리기준으로 구하기
            int count = bfs();

            if(count == 0) break;
            else {
                answer += count;
            }
        }
        System.out.println(answer);
    }

    // 상좌하우 (가장 위에 있는, 가장 왼쪽에 있는)
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};


    static int bfs() {

        // 초기화
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(shark_pos);
        boolean[][] ch = new boolean[n][n];
        int L = 0;
        ch[shark_pos[1]][shark_pos[0]] = true;
        int min_x=Integer.MAX_VALUE;
        int min_y=Integer.MAX_VALUE;
        int min_len=Integer.MAX_VALUE;

        while(!Q.isEmpty()) {

            int len = Q.size();

            while(len --> 0) {

                int[] cur = Q.poll();

                for(int i=0;i<4;i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    if(nx>=0 && ny>=0 && nx<n && ny<n && !ch[ny][nx]) {

//                                2-1. 자신의 크기보다 작은 물고기만 먹을 수 있고
//                                * 2-2. 자신의 크기와 같은 물고기는 지나갈 수 있고
//                                * 2-3. 자신의 크기보다 큰 물고니는 지나가지 못한다.
                        if(shark_size >= board[ny][nx]) {

                            ch[ny][nx] = true;
                            Q.offer(new int[]{nx, ny});

                            if (board[ny][nx] != 0 && shark_size > board[ny][nx]) {

                                min_len = L+1;

//                            3-3-2. 가까운 물고기가 많을시 -> 가장 위에 있는 물고기 -> 가장 왼쪽에 있는 물고기 순
                                // y값 작고 -> x값 작은 순
                                if (ny < min_y) {
                                    min_y = ny;
                                    min_x = nx;
                                } else if (ny == min_y && nx < min_x) {
                                    min_x = nx;
                                }
                            }
                        }
                    }
                }
            }
            if(min_len != Integer.MAX_VALUE) {
                shark_count++;
                shark_pos[0] = min_x;
                shark_pos[1] = min_y;
                board[min_y][min_x] = 0;
                if(shark_count == shark_size) {
                    shark_size++;
                    shark_count = 0;
                }
                return min_len;
            }

            L++;
        }
        return 0;
    }

}
