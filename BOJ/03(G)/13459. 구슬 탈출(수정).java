import java.io.*;
import java.util.*;

class Main {
    static class Marble {

        // 빨간 구슬 - x,y
        int[] red;
        // 파란 구슬 - x,y
        int[] blue;
        // 빨간 구슬 이동 거리
        int disR;
        // 파란 구슬 이동 거리
        int disB;

        public Marble(int[] red, int[] blue, int disR, int disB) {
            this.red = red;
            this.blue = blue;
            this.disR = disR;
            this.disB = disB;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // --- 정답 : 파란 구슬을 구멍에 넣지 않으면서 빨간 구슬을 10번 이하로 움직여서 빼낼 수 있으면 1 없으면 0
        // => BFS
        // 체크 배열 => 4차원 배열
        // 빨간 구슬과 파란 구슬이 같이 움직인다 => 객체로 묶기
        // 기울이기 => 메서드
        // 겹치는 순간 고려 -> 이동 거리 구해서 더 이동한 구슬 뺵
        // 모서리 벽이기 때문에 배열 인덱스 범위 고려할 필요 없음

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        int[] red={};
        int[] blue={};

        for(int i=0; i<N; i++) {
            board[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                // 빨간 구슬이면 좌표 저장
                if(board[i][j] == 'R'){
                    red = new int[]{j, i};
                }

                //파란 구슬이면 좌표 저장
                if(board[i][j] == 'B'){
                    blue = new int[]{j, i};
                }
            }
        }

        Marble marble = new Marble(red, blue, 0, 0);

        // 출력
        int answer = BFS(marble, N, M);
        System.out.println(answer);
    }

    static char[][] board;
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int BFS(Marble marble, int N, int M) {

        // 체크 배열 - 레드 x,y , 블루 x,y
        boolean[][][][] ch = new boolean[M][N][M][N];
        int cnt = 0;

        Queue<Marble> Q = new LinkedList<>();
        Q.offer(marble);
        ch[marble.red[0]][marble.red[1]][marble.blue[0]][marble.blue[1]] = true; // 체크

        while(!Q.isEmpty()) {
            int len = Q.size();

            while(len --> 0) {
                Marble cur = Q.poll();
                for(int i=0; i<4; i++) {

                    // 기울이기
                    Marble next = move(cur, i);

                    // 구슬이 구멍에 들어갔는지 확인 - 파란 구슬은 들어가면 안되고 빨간 구슬은 들어가야 함
                    if(board[next.blue[1]][next.blue[0]] == 'O') continue;
                    else if(board[next.red[1]][next.red[0]] == 'O') return 1;

                    // 겹치는지 확인
                    if(next.red[0] == next.blue[0] && next.red[1] == next.blue[1]) {
                        // 더 이동한 구슬 빽
                        if(next.disB > next.disR) {
                            // 파란 구슬 빽
                            next.blue[0] -= dx[i];
                            next.blue[1] -= dy[i];
                        }
                        else {
                            // 빨간 구슬 빽
                            next.red[0] -= dx[i];
                            next.red[1] -= dy[i];
                        }
                    }

                    // 체크해서 없는 방문한 적 없으면 큐에 넣기
                    if(!ch[next.red[0]][next.red[1]][next.blue[0]][next.blue[1]]) {
                        Q.offer(next);
                        ch[next.red[0]][next.red[1]][next.blue[0]][next.blue[1]] = true;
                    }
                }
            }
            cnt++;
            // 10번 까지 가능 -> next를 체크하니까 9번 까지 가능
            if(cnt > 9) return 0;
        }
        return 0;
    }

    static Marble move(Marble marble, int d) {

        // 현재 위치는 .인 자리
        // 움직인 점이 #이면 안됨

        int nRx = marble.red[0];
        int nRy = marble.red[1];
        int disR = 0;
        int nBx = marble.blue[0];
        int nBy = marble.blue[1];
        int disB = 0;

        // 빨간 구슬 기울이기
        while(true) {

            // 다음이 벽이면 멈춤
            if(board[nRy + dy[d]][nRx + dx[d]] == '#') break;

            // 이동할 수 있으면, 이동 후 거리 증가
            nRx += dx[d];
            nRy += dy[d];
            disR++;

            // 구멍 만나면 멈춤
            if(board[nRy][nRx] == 'O') break;
        }

        // 파란 구슬 기울이기
        while(true) {

            // 다음이 벽이면 멈춤
            if(board[nBy + dy[d]][nBx + dx[d]] == '#') break;

            // 이동할 수 있으면, 이동 후 거리 증가
            nBx += dx[d];
            nBy += dy[d];
            disB++;

            // 구멍 만나면 멈춤
            if(board[nBy][nBx] == 'O') break;
        }

        return new Marble(new int[]{nRx, nRy}, new int[]{nBx, nBy}, disR, disB);
    }

}