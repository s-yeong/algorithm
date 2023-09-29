import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 3055. 탈출
 * 1. '.' : 비어있음, '*' : 물이 차 있음, 'X' : 돌 (=벽)
 * 2. `매 분`마다 고슴도치 이동, 물 확장(비어있는 칸으로 `확장 - 상하좌우`)
 * 2-1. 물이 찰 `예정`인 칸으로도 고슴도치 이동 불가
 * => 도착 지점 가기 위한 최소 시간
 *
 * 출력
 * - 도착 지점 이동 불가 => "KAKTUS"
 *
 * 풀이
 * 1. 물이 찰 예정인 칸으로 고슴도치 이동 불가능하니, 물 먼저 확장 시킨다음 고슴도치 이동하면 된다.
 * 2. 물 확장 시킬 큐와 고슴도치 이동 큐를 분리하자
 * 3. 최단거리 => bfs로 풀자
 *
 */
public class BOJ_3055_탈출 {

    static int rowLen, colLen;
    static char[][] board;
    // 시작지점
    static int[] start;
    static boolean[][] ch;

    //고슴 도치 이동 큐, 물 큐
    static Queue<int[]> Q, waterQ;

    // 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {

        // 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new char[rowLen][colLen];
        Q = new ArrayDeque<>();
        waterQ = new ArrayDeque<>();
        for(int row=0; row<rowLen; row++) {
            board[row] = br.readLine().trim().toCharArray();
            for(int col=0; col<colLen; col++) {

                // 시작 지점이면,
                if (board[row][col] == 'S') {
                    start = new int[]{row, col};
                }
                // 물이면, 큐에 넣기
                else if (board[row][col] == '*') {
                    waterQ.offer(new int[]{row, col});
                }
            }
        }

        // bfs
        int answer = bfs();
        if (answer == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
    }

    static int bfs() {

        ch = new boolean[rowLen][colLen];

        // 시작지점 큐에 넣기
        Q.offer(start);
        ch[start[0]][start[1]] = true;  // 방문 체크

        int time = 0;

        while(!Q.isEmpty()) {

            // 물 확장
            waterExpand();

            int size = Q.size();

            while(size --> 0) {

                int[] cur = Q.poll();

                // 고슴도치 이동
                for(int dir=0; dir<4; dir++) {

                    int nextRow = cur[0] + dr[dir];
                    int nextCol = cur[1] + dc[dir];

                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen) {

                        // 도착 지점이면,
                        if (board[nextRow][nextCol] == 'D')
                            return time + 1;

                        // 갈 수 있으면,
                        if (board[nextRow][nextCol] == '.' && !ch[nextRow][nextCol]) {
                            ch[nextRow][nextCol] = true;
                            Q.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
            // 시간 증가
            time++;
        }
        // 도착 지점 못 간 경우,
        return -1;
    }

    static void waterExpand() {

        int size = waterQ.size();

        while(size --> 0) {

            int[] water = waterQ.poll();

            for(int dir=0; dir<4; dir++) {

                int nextRow = water[0] + dr[dir];
                int nextCol = water[1] + dc[dir];

                // 빈 칸으로 확장
                if(nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen
                    && board[nextRow][nextCol] == '.') {
                    board[nextRow][nextCol] = '*';
                    waterQ.offer(new int[] {nextRow, nextCol});
                }
            }
        }
    }

}