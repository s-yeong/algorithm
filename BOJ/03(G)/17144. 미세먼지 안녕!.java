import java.io.*;
import java.util.*;

class Main {
    static int R, C, T;
    static int[][] board;
    static int upy, downy;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 공기 청정기 위치 찾기
        upy = 0; // 위
        downy = 0; // 아래
        for (int i = 0; i < R; i++) {
            // 공기 청정기라면,
            if (board[i][0] == -1) {
                upy = i;
                downy = i + 1;
                break;
            }
        }

        /*
        미세먼지의 양을 실시간으로 모니터링
        1. 공기청정기 위치 항상 0열 (y=0), 두 행 차지 => -1로 표시
        2. 1초 동안 일어나는 일

        2-1-1. 미세먼지 네 방향으로 확산, 모든 칸 동시에
        2-1-2. 인접한 방향 공기청정기 있거나 칸 없으면 확산X
        2-1-3. 확산 양 = 값/5
        2-1-4. 확산 후 남은 미세먼지양 = 값 - (값/5)×(확산된 방향의 개수)

        2-2. 공기청정기 작동
        2-2-1. 위쪽 방향 -> 반시계 방향으로 바람 순환
        2-2-2. 아럐쪽 방향 -> 시계 방향으로 바람 순환
        2-2-3. 바람이 불면 미세먼지가 바람 방향대로 "한 칸" 이동
         */

        // T초가 지난 후 방에 남아있는 미세먼지 양 계산
        System.out.println(solution());
    }

    static int solution() {

        // T초가 지난 후
        while (T-- > 0) {

            // 미세먼지 확산
            diffuse();

            // 공기 청정기 작동
            clean();
        }

        // 방에 남아있는 미세먼지 양 계산
        int sum = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                // 공기청정기 위치 제외 계산
                if(upy == i && j == 0) continue;
                if(downy == i && j == 0) continue;
                sum += board[i][j];
            }
        }
        return sum;
    }

    // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    // 미세먼지 확산
    static void diffuse() {

        /*
        2-1-1. 미세먼지 네 방향으로 확산, 모든 칸 동시에
        2-1-2. 인접한 방향 공기청정기 있거나 칸 없으면 확산X
        2-1-3. 확산 양 = 값/5
        2-1-4. 확산 후 남은 미세먼지양 = 값 - (값/5)×(확산된 방향의 개수)
         */

        // 확산전 배열, 확산 후 배열 필요
        int[][] before = new int[R][C];
        for (int i = 0; i < R; i++) {
            before[i] = board[i].clone();
        }

        // 확산
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {

                // 0(빈칸)아니고 -1(공기청정기) 아니면, 미세먼지
                if (before[y][x] != 0 && before[y][x] != -1) {

                    int cnt = 0;    // 확산된 방향의 개수
                    // 2-1-3. 확산 양 = 값/5
                    int value = before[y][x] / 5;

                    // 2-1-1. 미세먼지 네 방향으로 확산
                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        // 움직일 수 있는지
                        if (nx >= 0 && ny >= 0 && nx < C && ny < R && before[ny][nx] != -1) {
                            cnt++;  // 확산된 방향의 개수 증가
                            // 확산!
                            board[ny][nx] += value;
                        }
                    }
                    // 2-1-4. 확산 후 남은 미세먼지양 = 값 - (값/5)×(확산된 방향의 개수)
                    board[y][x] = board[y][x] - (value * cnt);
                }
            }
        }
    }

    // 공기 청정기 작동
    static void clean() {

        /*
        2-2. 공기청정기 작동
        2-2-1. 위쪽 방향 -> 반시계 방향으로 바람 순환
        2-2-2. 아럐쪽 방향 -> 시계 방향으로 바람 순환
        2-2-3. 바람이 불면 미세먼지가 바람 방향대로 "한 칸" 이동
         */

        // "위쪽" 방향 이동
        // 아래방향 -> 공기 청정기로 들어간 미세먼지 정화됨
        for (int y = upy - 1; y >=0 ; y--) {
            board[y+1][0] = board[y][0];
        }
        // 공기 청정기 위치에 값을 다시 둬야함
        board[upy][0] = -1;
        // 왼쪽 방향
        for (int x = 1; x < C; x++) {
            board[0][x - 1] = board[0][x];
        }
        // 위 방향
        for (int y = 1; y <= upy; y++) {
            board[y-1][C-1] = board[y][C-1];
        }
        // 오른쪽 방향
        for (int x = C-2; x>=1; x--) {
            board[upy][x+1] = board[upy][x];
        }
        // 마지막 이동한 값 0
        board[upy][1] = 0;

        // "아래쪽" 방향 이동
        // 위 방향  -> 공기 청정기로 들어간 미세먼지 정화됨
        for (int y = downy+1; y < R; y++) {
            board[y-1][0] = board[y][0];
        }
        // 공기 청정기 위치에 값을 다시 둬야함
        board[downy][0] = -1;
        // 왼쪽 방향
        for (int x = 1; x < C; x++) {
            board[R-1][x - 1] = board[R-1][x];
        }
        // 아래방향
        for (int y = R-2; y >=downy ; y--) {
            board[y+1][C-1] = board[y][C-1];
        }
        // 오른쪽 방향
        for (int x = C-2; x>=1; x--) {
            board[downy][x+1] = board[downy][x];
        }

        // 마지막 이동한 값 0
        board[downy][1] = 0;

    }


}