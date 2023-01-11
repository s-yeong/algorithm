import java.io.*;
import java.util.*;

class Main {
    static int N, L,R;
    static int[][] board;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 출력 : 인구 이동이 며칠 동안 발생하는지

        // 국경선 여는 기준: L <= "두 나라의 인구차이" <= R
        // 인구 차이 계산 메서드 - 연합 가능한지 확인
        // 연합 메서드 - 연합 인구수/칸의 개수

        // 1.국경선 계산(인구 차이) -> 2.국경선 열렸을 때 -> 3.연합하기(연합의 인구수/칸의 개수)

        System.out.println(solution());
    }

    static boolean flag;
    static int solution() {

        int count = 0;

        while(true) {

            // 연합 여부
            boolean isUnion = false;
            // 연합 해제 후 처음부터 탐색하기 위해 매번 체크배열 선언
            ch = new boolean[N][N];

            for(int i=0; i<N; i++) {
                for (int j = 0; j < N; j++) {
                    // 방문 안헀으면
                    if (!ch[i][j]) {

                        // 1.국경선 계산
                        uniList = new ArrayList<>();    // 국경선 계산시 연합 리스트 초기화
                        flag = false;   // i,j 지점에서 국경선 열렸는지
                        ch[i][j] = true;    // 방문 체크

                        diff(i, j);

                        // 2. i,j 지점에서 국경선 열렸으면 그 지점 연합 리스트에 추가
                        if (flag) {
                            uniList.add(new int[]{i, j});
                            // 3. 연합하기
                            union();
                            isUnion = true;
                        }
                    }
                }
            }
            // 연합 했으면 카운트
            if(isUnion) {
                count++;
            }
            else return count;
        }
    }

    static ArrayList<int[]> uniList;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[][] ch;
    static void diff(int x, int y) {

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<N && !ch[nx][ny]) {

                int diff = Math.abs(board[x][y] - board[nx][ny]);

                if(L <= diff && diff <= R) {
                    flag = true;  // x,y에서 국경선 열렸는지 체크
                    ch[nx][ny] = true;
                    uniList.add(new int[]{nx, ny});
                    diff(nx, ny);
                }
            }
        }
    }

    static void union() {

        int sum = 0;
        // 합 더하기
        for(int[] cur : uniList) {
            sum += board[cur[0]][cur[1]];
        }

        // 평균 넣기
        int avg = sum / uniList.size();
        for(int[] cur : uniList) {
            board[cur[0]][cur[1]] = avg;
        }
    }


}