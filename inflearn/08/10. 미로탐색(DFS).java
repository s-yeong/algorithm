import java.util.Scanner;

public class Main {
    static int[][] board = new int[8][8];
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int answer = 0;

    public static void DFS(int x, int y) {


        if(x == 7 && y == 7) answer++;
        else {
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 경계선 밖인지 안인지, 갈 수 있는지 nx, ny를 보고 판단
                if(nx >=1 && nx <= 7 && ny >=1 && ny <=7 && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    DFS(nx, ny);
                    board[nx][ny] = 0; // back
                }
            }
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for(int i=1; i<=7; i++) {
            for(int j=1; j<=7; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // 출발점 -> 도착점까지 갈 수 있는 방법의 수
        board[1][1] = 1; // 출발점 체크
        DFS(1, 1);
        System.out.println(answer);



    }
}