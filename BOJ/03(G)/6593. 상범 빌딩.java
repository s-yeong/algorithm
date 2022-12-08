import java.io.*;
import java.util.*;

class Main {
    static StringBuilder sb = new StringBuilder();
    static char[][][] building;
    static int C,R,L;

    // 동 서 남 북 상 하
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    static int BFS(int x, int y, int z) {

        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{x, y, z});

        int cnt = 0;
        while(!Q.isEmpty()) {

            int len = Q.size();
            // tmp[0] = x, tmp[1] = y, tmp[2] = z
            for(int j=0; j<len; j++) {
                int[] tmp = Q.poll();
                for (int i = 0; i < 6; i++) {
                    int nx = tmp[0] + dx[i];
                    int ny = tmp[1] + dy[i];
                    int nz = tmp[2] + dz[i];

                    if (nx >= 0 && ny >= 0 && nz >= 0 && nx < C && ny < R && nz < L) {
                        if (building[nz][ny][nx] == '.') {
                            building[nz][ny][nx] = '#';
                            Q.offer(new int[]{nx, ny, nz});
                        } else if (building[nz][ny][nx] == 'E') {
                            return cnt + 1;
                        }
                    }
                }
            }
            cnt++;
        }
        return 0;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;
            building = new char[L][R][C];

            // 금 - #     비어있음 - .
            // 시작점 S    출구 E

            // L - 빌딩 층 수
            // R C - 빌딩 한 층의 행과 열의 개수
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    // C개의 문자 - 상범 빌딩의 한 칸
                    building[i][j] = br.readLine().toCharArray();
                }
                br.readLine();
            }

            int answer = 0;
            for(int i=0; i<L; i++) {
                for(int j=0; j<R; j++) {
                    for(int k=0; k<R; k++) {
                        if(building[i][j][k] == 'S') {
                            answer = BFS(k, j, i);
                        }
                    }
                }
            }

            if(answer != 0) {
                sb.append("Escaped in ").append(answer).append(" minute(s).").append("\n");
            }
            else sb.append("Trapped!\n");
        }

        System.out.println(sb);
    }

}
