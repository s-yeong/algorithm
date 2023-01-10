import java.io.*;
import java.util.*;

class Main {
    static int N,M, zeroCount;
    static int[][] board;
    static ArrayList<int[]> virus = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];  // 연구소 크기 N X N
        zeroCount = 0;  // 빈칸 수

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                // 바이러스 위치면 넣기
                if(board[i][j] == 2) virus.add(new int[]{j, i});
                // 빈칸이면 개수 더하기
                else if(board[i][j] == 0) zeroCount++;
            }
        }

        // 0:빈칸, 1:벽, 2: 바이러스 위치

        // "활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변한다."

        // 바이러스 -> 활성 상태, 비활성 상태
        // 조합 - virus 중 M개를 활성 상태 변경
        combi = new int[M];
        combi(0,0);

        // 빈칸이 없으면
        if(zeroCount == 0) System.out.println(0);
        // 값이 최대값이면 (=모든 빈 칸에 바이러스 퍼뜨릴 수 없음)
        else if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static int[] combi;
    static int answer=Integer.MAX_VALUE;
    static void combi(int L, int S) {

        // 조합 완료
        if(L==M) {
            // 계산

            // 배열 복사
            int[][] tmp = new int[N][N];
            for (int i = 0; i < N; i++) tmp[i] = board[i].clone();

            // 활성 바이러스 표시 => -2로, 활성 바이러스 큐에 넣기
            Queue<int[]> Q = new LinkedList<>();
            for(int idx : combi) {
                int[] v = virus.get(idx);

                // 표시
                tmp[v[1]][v[0]] = -2;
                Q.offer(v);
            }

            // 최소 시간 계산
            answer = Math.min(answer,BFS(tmp, Q));
        }
        else {
            for(int i=S; i<virus.size(); i++) {
                combi[L] = i;
                combi(L + 1, i + 1);
            }
        }
    }

    static int BFS(int[][] tmp, Queue<int[]> Q) {

        int zero = zeroCount;

        // 상 하 좌 우
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        // 바이러스 활성화 : 2, 바이러스 비활성화 : -2
        // 빈칸 : 0, 벽 : 1
        int time = 0;
        while(!Q.isEmpty()) {
            boolean flag = false;
            int len = Q.size();
            while(len --> 0) {
                int[] cur = Q.poll();
                for(int i=0; i<4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    // 퍼질 수 있으면(=빈칸이면) 큐에 넣기
                    if(nx>=0 && ny>=0 && nx<N && ny<N && (tmp[ny][nx] == 2 || tmp[ny][nx] == 0)) {
                        if(tmp[ny][nx] == 0) zero--;

                        /// 바이러스 퍼지기 - 활성화
                        tmp[ny][nx] = -2;
                        Q.offer(new int[]{nx,ny});
                        flag = true;
                    }
                }
            }
            if(flag) time++;

            // 최소 시간 넘어가면 리턴
            if(answer <= time) return Integer.MAX_VALUE;
            // 빈칸이 없으면 리턴
            if(zero == 0) return time;
        }

        // 모든 빈 칸에 바이러스 퍼질 수 없는 경우(=빈칸이 있는 경우)
        if(zero != 0) return Integer.MAX_VALUE;

        return time;
    }


}