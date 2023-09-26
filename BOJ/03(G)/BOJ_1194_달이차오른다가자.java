import java.io.*;
import java.util.*;

/**
 * 1194. 달이 차오른다, 가자.
 *
 * 1. 미로 구성
 * 1-1. '.' : 빈칸
 * 1-2. '#' : 벽
 * 1-3. 'a' ~ 'f' : 열쇠 (언제나 이동 가능, 처음 들어가면 열쇠 잡음)
 * 1-4. 'A' ~ 'F' : 문 (대응하는 열쇠가 있어야 이동 가능)
 * 1-5. '0' : 현재 위치, '1' : 탈출구
 * 2. 이동 (상하좌우)
 * => 미로 이동 횟수 최소값 구하기
 *
 * 입출력
 * - 탈출할 수 없으면 -1 출력
 * 1. 같은 타입의 열쇠, 문이 여러 개 있을 수 있음
 * 2. 문에 대응하는 열쇠가 없을 수도 있음
 * 3. 탈출구가 여러개일 수 있다.
 *
 * 풀이
 * 1. 탈출구가 여러개 있을 수 있기 때문에, dfs를 통해 최소값을 갱신하기 보다는, bfs를 통해 구한 다음 리턴하자
 * 2. 문에 들어가기 위해 열쇠를 가지고 있는지 체크해야 하기 때문에 열쇠 상태 저장이 필요
 * 3. 열쇠를 얻었을 때 마다 방문한 지점을 다시 가야할 수도 있음 => 열쇠 상태에 대한 방문 체크도 고려 => 3차원 체크 배열 (key 고려)
 *
 */
public class BOJ_1194_달이차오른다가자 {

    static int rowLen, colLen;
    static int startRow, startCol;
    static char[][] board;

    static boolean[][][] ch;

    // 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    // 큐에 담을 위치 클래스
    static class Position {
        
        int row, col;
        int moveCount;
        // 비트마스킹 활용하여 6개의 키 상태 저장
        int keys;

        Position(int row, int col, int moveCount, int keys) {
            this.row = row;
            this.col = col;
            this.moveCount = moveCount;
            this.keys = keys;
        }
    }


    public static void main(String[] args) throws IOException {

        // 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new char[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            board[row] = br.readLine().trim().toCharArray();
            for (int col = 0; col < colLen; col++) {
                // 시작 위치인 경우,
                if(board[row][col] == '0') {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        // bfs
        System.out.println(bfs());
    }


    static int bfs() {

        Queue<Position> Q = new ArrayDeque<>();

        // 위치 체크 + key 고려
        ch = new boolean[rowLen][colLen][1<<6];
        
        // 시작 지점 큐에 넣고 방문 체크
        Q.add(new Position(startRow, startCol, 0, 0));
        // 방문 체크
        ch[startRow][startCol][0] = true;

        while (!Q.isEmpty()) {

            Position cur = Q.poll();

            // 도착 지점인 경우,
            if (board[cur.row][cur.col] == '1') {
                // 최소값 리턴
                return cur.moveCount;
            }

            for (int dir = 0; dir < 4; dir++) {

                int nextRow = cur.row + dr[dir];
                int nextCol = cur.col + dc[dir];

                // 범위 체크, 벽이 아닌 경우, 방문 안한 경우
                if (nextRow >= 0 && nextCol >= 0 && nextRow < rowLen && nextCol < colLen
                    && board[nextRow][nextCol] != '#' && !ch[nextRow][nextCol][cur.keys]) {

                    char nextChar = board[nextRow][nextCol];
                    int nextKeys = cur.keys;

                    // 열쇠인 경우,
                    if (nextChar >= 'a' && nextChar <= 'f') {
                        // 키 추가
                        nextKeys = (nextKeys | (1 << (nextChar - 'a')));
                    }

                    // 문인 경우,
                    if (nextChar >= 'A' && nextChar <= 'F') {
                        // 해당 키가 없으면,
                        if((nextKeys & (1 << (nextChar - 'A'))) == 0) {
                            continue;
                        }
                    }

                    // 방문 체크 후 이동
                    ch[nextRow][nextCol][nextKeys] = true;
                    Q.offer(new Position(nextRow, nextCol, cur.moveCount + 1, nextKeys));
                }
            }
        }

        // 탈출할 수 없는 경우
        return -1;
    }
}
