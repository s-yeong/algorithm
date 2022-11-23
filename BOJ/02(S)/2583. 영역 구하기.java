import java.util.*;
import java.io.*;

class Main {

    static int area=0;
    static int M, N;
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] board;
    static void DFS(int x, int y) {

        for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0 && ny>=0 && nx<N && ny<M && board[ny][nx]== 0) {
                    board[ny][nx] = 1;
                    area++;
                    DFS(nx, ny);
                }
        }

    }


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> answer = new ArrayList<>();

        M = Integer.parseInt(st.nextToken());   //행
        N = Integer.parseInt(st.nextToken());   //열

        board = new int[M][N];

        int K = Integer.parseInt(st.nextToken());   // k개의 직사각형
        //몇 개의 분리된 영역으로 나누어지는
        // 분리된 각 영역의 넓이가 얼마인지를 구하여

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            // 왼쪽 아래 꼭짓점의 x, y좌표값
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            // 오른쪽 위 꼭짓점의 x, y좌표값
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 직사각형 그리기 -> 왼쪽 위 기준
            for(int y=y1; y<y2; y++) {
                for(int x=x1; x<x2; x++) {
                    board[y][x]++;
                }
            }
        }

        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] == 0) {
                    board[i][j] = 1;
                    area = 1;
                    DFS(j, i);  // x y
                    answer.add(area);
                }
            }
        }


        // 출력
        Collections.sort(answer);
        System.out.println(answer.size());
        for(int x : answer) System.out.print(x + " ");



    }
}
