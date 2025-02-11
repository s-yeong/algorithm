import java.io.*;
import java.util.*;

public class Main {

    // 최종 톱니바퀴 상태 구하기
    // 2(오른쪽), 6(왼쪽)
    static int[][] arr;
    static final int RIGHT = 1, LEFT = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[5][8];
        for (int i = 1; i <= 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());

        while(k--> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            solve(target, dir, arr);
        }
        int answer = arr[1][0] + arr[2][0]*2 + arr[3][0]*4 + arr[4][0]*8;
        System.out.println(answer);
    }
    static void solve(int target, int dir, int[][] arr) {
        int[] rotateDirs = new int[5];
        rotateDirs[target] = dir;

        // 왼쪽 톱니바퀴 체크
        for (int i = target; i > 1; i--) {
            if (arr[i][6] != arr[i - 1][2]) {
                rotateDirs[i - 1] = -rotateDirs[i];
            } else {
                break;
            }
        }

        // 오른쪽 톱니바퀴 체크
        for (int i = target; i < 4; i++) {
            if (arr[i][2] != arr[i + 1][6]) {
                rotateDirs[i + 1] = -rotateDirs[i];
            } else {
                break;
            }
        }

        // 회전
        for (int i = 1; i <= 4; i++) {
            if (rotateDirs[i] != 0) {
                rotate(rotateDirs[i], arr[i]);
            }
        }
    }

    static void rotate(int dir, int[] arr) {
        if(dir == LEFT) {
            int temp = arr[0];
            for(int i = 1; i < arr.length; i++) {
                arr[i-1] = arr[i];
            }
            arr[arr.length-1] = temp;
        }
        else if(dir == RIGHT) {
            int temp = arr[arr.length-1];
            for(int i = arr.length-1; i > 0; i--) {
                arr[i] = arr[i-1];
            }
            arr[0] = temp;
        }
    }
}