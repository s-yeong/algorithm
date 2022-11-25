import java.util.*;
import java.io.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


class Main {

    static int[][] board = new int[9][9];
    static int[] ch;
    static ArrayList<Point> arr = new ArrayList<>();

    static void print() {
        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static boolean isPos(int x, int y, int num){

        // x,y에 num을 넣을 수 있는지

        // 행, 열 체크
        for(int i=0;i<9; i++) {
            if(board[y][i] == num) return false;
            if(board[i][x] == num) return false;
        }

        int xs = x - (x % 3);
        int ys = y - (y % 3);
        for(int i=ys; i<ys+3; i++) {
            for(int j=xs; j<xs+3; j++) {
                if(board[i][j] == num) return false;
            }
        }

        return true;
    }



    static void DFS(int L, int S) {
        if(L == arr.size()) {
            // 출력
            print();
            System.exit(0);
        }

        for(int i=1; i<=9; i++) {
            Point ob = arr.get(S);
            if (isPos(ob.x, ob.y, i)) {
                board[ob.y][ob.x] = i;
                DFS(L + 1, S+1);
                board[ob.y][ob.x] = 0;
                }
        }
    }


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 가로 세로 9x9
        for(int i=0; i<9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) arr.add(new Point(j, i));
            }
        }

        ch = new int[arr.size()];

        DFS(0,0);
    }
}
