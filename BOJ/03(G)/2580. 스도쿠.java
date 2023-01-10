import java.io.*;
import java.util.*;

class Main {
    static int[][] board = new int[9][9];
    static ArrayList<int[]> blank = new ArrayList<>();
    static int zeroCount = 0;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                // 빈칸 이면 배열에 넣기
                if(board[i][j] == 0) blank.add(new int[]{j, i});
            }
        }
        DFS(0);

        // 순열로해서 맞는지 확인 -> 1넣고 되는지 확인, 2넣고 되는지 확인..
        // 숫자 하나 놓고 세로, 가로, 3x3 맞는지 확인
    }


    static boolean isPos(int num, int x, int y) {

        // 가로, 세로 확인
        for(int i=0; i<9; i++) {
            if(board[y][i] == num) return false;
            if(board[i][x] == num) return false;
        }

        // 3x3 확인
        // (x/3)*3
        int xs = (x / 3) * 3;
        int ys = (y / 3) * 3;
        for(int i=ys; i<ys+3; i++) {
            for(int j=xs; j<xs+3; j++) {
                if(board[i][j] == num) return false;
             }
        }

        return true;
    }

    static void DFS(int L) {

        StringBuilder sb = new StringBuilder();
        // 다 채웠으면
        if(L== blank.size()) {
            // 출력
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }
        else {
            int[] tmp = blank.get(L);
            for (int k = 1; k <= 9; k++) {
                if (isPos(k, tmp[0], tmp[1])) {
                    // 둘 수 있으면 넣기
                    board[tmp[1]][tmp[0]] = k;
                    DFS(L + 1);
                    board[tmp[1]][tmp[0]] = 0;  // 빽
                }
            }
        }
    }
}
