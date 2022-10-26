import java.io.*;
import java.util.*;


public class Main {

    static int C,R, K;
    static int[][] board;
    static boolean flag = false;
    static ArrayList<Integer> answer = new ArrayList<>();
    // 시계 방향 = 상, 우, 하, 좌
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static void DFS(int x, int y, int cnt, int dir) {
        if (flag) return;

        board[y][x] = 1;

        if (cnt == K) {
            answer.add(x);
            answer.add(y);
            flag = true;
        } else {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 1 && ny >= 1 && nx <= C && ny <= R && board[ny][nx] == 0) {
                DFS(nx, ny, cnt + 1, dir);
            }

            // 방향 옮기기
            else {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
//                if (board[ny][nx] == 0)       // K > C*R 조건 걸어줬기 때문에 필요X
                DFS(nx, ny, cnt + 1, dir);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());    // 대기 번호
        // C X R 격자형    - C 가로 R 세로

        board = new int[R + 1][C + 1];
        // ex) C=7 R=6

        if(K > C*R) System.out.println(0);
        else {
            DFS(1, 1, 1, 0);
            for(int x : answer) System.out.print(x + " ");
        }





    }
}
