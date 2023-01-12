import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static char[][] board;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 크기가 N X M 인 미로
        board = new char[N][M];
        for(int i=0; i<N; i++) {
            board[i] = br.readLine().toCharArray();
        }


        // 미로에서 탈출 가능한 칸의 수를 계산
        // 그 칸에서 이동을 시작해서 칸에 적힌대로 이동했을 때, 미로의 경계 밖으로 이동하게 되는 칸

        solution();
        System.out.println(answer);
    }

    static boolean[][] ch;
    static int answer = 0;
    static boolean[][] success;
    
    // 시간초과 -> 해결방법 : 카운팅을 한번씩 하지 말고, 탈출할 때 한번에 카운팅
    // 성공한 지점 배열 따로

    static void solution() {
        ch = new boolean[N][M];
        success = new boolean[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                // i,j 방문한 적 없으면
                if(!ch[i][j]) {
                    ch[i][j] = true;
                    DFS(j, i, 1);
                }
            }
        }
    }

    static void DFS(int x, int y, int count) {

        int[] next = move(x, y);
        int nx = next[0];
        int ny = next[1];

        // 다음 지점이 미로 경계 밖 이동시 -> 성공
        if(nx < 0 || ny < 0 || ny >= N || nx >= M) {
            success[y][x] = true;   // 현재 지점 성공 체크
            answer += count;
        }

        // 다음 지점이 이미 방문했을 때
        else if(ch[ny][nx]) {
            // 다음지점이 성공 지점이면 카운트
            if(success[ny][nx]) {
                answer += count;
                success[y][x] = true;   // 현재 지점 성공 체크
            }   // 성공 지점이 아니면 false (default)
        }

        // 첫 방문
        else {
            ch[ny][nx] = true;
            DFS(nx, ny, count + 1);
            if(success[ny][nx]) {   // 다음 지점이 성공 지점으로 체크 된 경우
                success[y][x] = true;   // 현재 지점 성공 체크
            }
        }
    }

    static int[] move(int x, int y) {

        if (board[y][x] == 'U') {
            return new int[]{x, y - 1};
        }

        if (board[y][x] == 'R') {
            return new int[]{x + 1, y};
        }

        if (board[y][x] == 'D') {
            return new int[]{x, y + 1};
        }

        if (board[y][x] == 'L') {
            return new int[]{x - 1, y};
        }

        return new int[]{};
    }





}