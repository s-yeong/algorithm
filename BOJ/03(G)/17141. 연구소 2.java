import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[][] board;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                // 바이러스 후보면, 배열에 넣기
                if(board[i][j] == 2) virus.add(new int[]{j, i});
            }
        }

        // 0:빈칸, 1:벽, 2:바이러스 놓을 수 있는 칸


        // M개 조합
        // 바이러스 후보 조합하기
        combi = new int[M];
        combi(0,0);

        // 최대값 = 못퍼트리면
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);

    }

    static int answer = Integer.MAX_VALUE;
    static ArrayList<int[]> virus = new ArrayList<>();
    static int[] combi;
    // 벽 조합
    static void combi(int L, int S) {

        if(L==M) {
            // 조합 완료 -> 계산
            int[][] tmp = new int[N][N];
            Queue<int[]> Q = new LinkedList<>();

            for (int i = 0; i < N; i++) tmp[i] = board[i].clone();
            // 바이러스 퍼지기 계산
            for(int idx : combi) {
                int[] v = virus.get(idx);
                Q.offer(v); // 바이러스 큐에 넣기
                tmp[v[1]][v[0]] = -2;   // 바이러스 놓은 부분 -2로 두기
            }
            answer = Math.min(answer, BFS(tmp, Q));
        }
        else {
            for(int i=S; i<virus.size(); i++) {
                combi[L] = i;   // i번쨰 인덱스 바이러스 넣기
                combi(L + 1, i + 1);
            }

        }
    }
    static int BFS(int[][] tmp, Queue<int[]> Q) {


        int time = -1;
        // 상 하 좌 우
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
            while(!Q.isEmpty()) {
            int len = Q.size();
            while(len --> 0) {
                int[] cur = Q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    // 빈 칸이면 바이러스 퍼지기
                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && (tmp[ny][nx] == 0 || tmp[ny][nx] == 2)) {
                        tmp[ny][nx] = -2;
                        Q.offer(new int[]{nx, ny});
                    }
                }
            }
            time++;

            // 값이 더 크면 필요 없으므로 리턴
            if(answer<=time) return time;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                // 빈 칸에 바이러스 퍼뜨릴 수 없는 경우 - 최대값 출력
                if (tmp[i][j] == 0) {
                    return Integer.MAX_VALUE;
                }
            }
        }

        return time;
    }
}