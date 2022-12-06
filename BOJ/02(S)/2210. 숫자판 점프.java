import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static char[][] board = new char[5][5];
    static int[] ch = new int[1000000];
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int answer = 0;

    static void DFS(int x, int y, int cnt, String num) {

        if(cnt == 6) {
            int n = Integer.parseInt(num);
            if(ch[n] == 0) {
                answer++;
                ch[n] = 1;
            }
        }
        else {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0 && ny>=0 && nx<5 && ny<5) {
                    DFS(nx, ny, cnt + 1, num + board[ny][nx]);
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                board[i][j] = st.nextToken().charAt(0);
            }
        }

        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                DFS(j, i, 1, String.valueOf(board[i][j]));
            }
        }

        System.out.println(answer);


    }

}
