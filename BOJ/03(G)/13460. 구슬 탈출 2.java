import java.io.*;
import java.util.*;

class Main {
    static class Marble {
        int[] red;
        int[] blue;

        public Marble(int[] red, int[] blue) {
            this.red = red;
            this.blue = blue;
        }
    }
    static int N, M;
    static Queue<Marble> Q = new LinkedList<>();
    static char[][] board;
    static boolean[][][][] ch;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 빨간 구슬을 구멍을 통해 빼내는 최소의 경우의 수
        // 파란 구슬이 동시에 들어가거나 파란 구슬만 들어가도 실패 -> 이 경우는 큐에 넣으면 안됨
        // 가장자리 모두 벽 => 범위 고려 X

        // 기울이기 동작 => 메서드 이용
        // 동시에 같은 칸 있을 수 없음 -> 이동 거리 체크해서 많이 이동한 구슬 빽
        // 최소의 경우의 수 -> BFS -> 구슬의 위치를 큐에 저장
        // 기울일 떄 빨간 구슬과 파란 구슬 함께 이동 => 두 위치를 저장
        // R이나 B의 위치로도 이동 가능 => #이 아닌 경우 이동 가능
        // 체크 배열 필요 -> 두 구슬 좌표 -> 4중 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        ch = new boolean[N][M][N][M];
        int[] red = {}; // x,y
        int[] blue = {}; // x,y

        for(int i=0; i<N; i++) {
            board[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {

                // 빨간 구슬, 파란 구슬 좌표 필요 (구멍은 이동시에 고려하면 됨)
                if(board[i][j] == 'R') {
                    red = new int[]{j, i};
                }
                if (board[i][j] == 'B') {
                    blue = new int[]{j, i};
                }
            }
        }

        // 큐에 넣기
        Q.offer(new Marble(red, blue));
        int answer = BFS();
        System.out.println(answer);
    }

    static int BFS() {

        int count = 0; // 빼낼 수 있는 경우의 수 계산
        while(!Q.isEmpty()) {
            int len = Q.size();

            while(len --> 0) {
                // 이동
                Marble cur = Q.poll();

                for (int i = 0; i < 4; i++) {
                    Marble next = move(cur, i);// 이동하고 구슬 반환

                    // 구슬이 구멍에 빠졌는지 확인

                    // 파란 구슬이 빠지면 넘어가기
                    if(board[next.blue[1]][next.blue[0]] == 'O') {
                        continue;
                    }
                    // 빨간 구슬만 빠지면 값 출력
                    else if(board[next.red[1]][next.red[0]] == 'O') {
                        return count + 1;
                    }

                    // 방문하지 않으면 큐에 넣기
                    else if(!ch[next.red[1]][next.red[0]][next.blue[1]][next.blue[0]]) {
                        ch[next.red[1]][next.red[0]][next.blue[1]][next.blue[0]] = true;
                        Q.offer(next);
                    }
                }
            }

            // 경우의 수 10번 이하로만 가능
            count++;
            if(count >= 10) return -1;
        }
        return -1;
    }

    // 왼 오 위 아래
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Marble move(Marble marble, int d) {

        // 기울이기 - 벽 앞까지 멈추기
        int rx = marble.red[0];
        int ry = marble.red[1];
        int bx = marble.blue[0];
        int by = marble.blue[1];
        // 거리
        int disR = 0;
        int disB = 0;

        // 다음 이동한 곳이 벽이 아니면 이동
        // 빨간 구슬 이동
        while(board[ry+dy[d]][rx+dx[d]] != '#') {
            rx = rx + dx[d];
            ry = ry + dy[d];
            disR++;
            // 위치가 구멍이면 멈춰야함!!
            if(board[ry][rx] == 'O') break;
        }

        // 파란 구슬 이동
        while(board[by+dy[d]][bx+dx[d]] != '#') {
            bx = bx + dx[d];
            by = by + dy[d];
            disB++;
            // 위치가 구멍이면 멈춰야함!!
            if(board[by][bx] == 'O') break;
        }

        // 구멍이 아니면서, 구슬의 위치가 겹쳤다면
        if(rx == bx && ry == by && board[by][bx] != 'O') {

            // 빨간 구슬이 많이 이동 -> 빽
            if(disR > disB) {
                rx -= dx[d];
                ry -= dy[d];
            }
            // 파란 구슬이 많이 이동 -> 빽
            else {
                bx -= dx[d];
                by -= dy[d];
            }
        }
        // 이동 한 구슬 리턴
        return new Marble(new int[]{rx, ry}, new int[]{bx, by});
    }
}