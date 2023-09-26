import java.util.Scanner;

public class Main10 {
    public static int solution(int n, int[][] arr) {

        int answer = 0;

        // 3중 for문
        // 상, 우, 하, 좌
        int[] dx = {-1, 0, 1, 0};   // direction
        int[] dy = {0, 1, 0, -1};

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                boolean flag = true;

                for(int k=0; k<4; k++) {    // 4방향 탐색
                    int nx = i + dx[k]; // 행 좌표
                    int ny = j + dy[k]; // 열 좌표
                    if(nx>=0 && nx<n && ny>=0 && ny<n && arr[nx][ny] >= arr[i][j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) answer++;
            }
        }

/*
        int[][] newArr = new int[n + 2][n + 2];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                newArr[i+1][j+1] = arr[i][j];
            }
        }
        for(int i=1; i<newArr.length-1; i++) {
            for(int j=1; j< newArr.length-1; j++) {
                if(newArr[i][j] > newArr[i][j-1] &&
                newArr[i][j] > newArr[i][j+1] && newArr[i][j] > newArr[i-1][j] &&
                newArr[i][j] > newArr[i+1][j]) answer++;
            }
        }
*/

        return answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.print(solution(n, arr));
    }

}