import java.io.*;
import java.util.*;

class Main {
    static int answer = 0;
    static int count;
    static int[] combi = new int[3];
    static int[][] board;
    static int N,M;
    static ArrayList<int[]> list;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        list = new ArrayList<>();  // 벽 후보

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                // 벽 후보가 될 수 있는 경우 list에 넣기 x,y
                if(board[i][j] == 0) list.add(new int[]{j, i});
            }
        }


        // 조합하기 lenC3
        int len = list.size();
        count = len-3;  // 벽 후보 - 벽 3개 => 바이러스 안 퍼졌을 때 안전 영역 최대 크기

        DFS(0, 0, len);
        System.out.println(answer);
    }

    // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int BFS(int[][] tmp) {

        // 최대 안전 영역 세기
        int max = count;

        // 바이러스 큐에 넣기 (x,y)
        Queue<int[]> Q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(tmp[i][j] == 2) Q.offer(new int[]{j, i});
            }
        }

        while(!Q.isEmpty()) {

            int[] cur = Q.poll();   // 바이러스 꺼내기
            int x = cur[0];
            int y = cur[1];

            // 바이러스 이동
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0 && ny>=0 && nx<M && ny<N && tmp[ny][nx] == 0) {
                    // 바이러스 이동하면 안전 영역 줄이기
                    max--;
                    tmp[ny][nx] = 2;
                    Q.offer(new int[]{nx, ny});
                }
            }
        }

        // 최대 안전 영역 크기 출력
        return max;
    }

    static void DFS(int L, int S, int len) {

        if(L==3) {
            // 조합 완성시 계산

            // 벽 세우기 위해 클론
            int[][] tmp = new int[N][M];
            for(int i=0; i<board.length; i++) {
                tmp[i] = board[i].clone();
            }

            for(int idx : combi) {
                int[] x = list.get(idx);
                tmp[x[1]][x[0]] = 1;    // 벽 세우기
            }

            // 안전 영역 계산
            int cnt = BFS(tmp);
            answer = Math.max(answer, cnt);
        }
        else {
            for (int i = S; i < len; i++) {
                combi[L] = i;   // L번째 인덱스에 list i 인덱스 넣기
                DFS(L + 1, i + 1, len);
            }
        }

    }

}