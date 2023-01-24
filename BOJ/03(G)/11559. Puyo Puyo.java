
import java.io.*;
import java.util.*;

class Main {
    // 12개 줄 6개의 문자
    static char[][] board = new char[12][6];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<12; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // .:빈공간, R:빨강, G:초록, B:파랑, P:보라, Y:노랑
        // 주어진 상황에서 몇연쇄가 되는지 출력
        System.out.println(solution());
    }
    static boolean[][] ch;
    static int solution() {
        int answer = 0;  // 연쇄 횟수
        while(true) {
            boolean flag = false;
            ch = new boolean[12][6];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    // 방문 안했으면
                    if(board[i][j] != '.' && !ch[i][j]) {
                        ch[i][j] = true;    // 방문
                        ArrayList<int[]> list = new ArrayList<>(); // 좌표 리스트
                        list.add(new int[]{j, i});
                        DFS(j, i, board[i][j], list);
                        if(list.size() >= 4) {  // 4개 이상
                            flag = true;
                            for(int[] tmp : list) board[tmp[1]][tmp[0]] = '.';
                        }
                    }
                }
            }
            if(flag) {
                answer++;
                //중력의 영향을 받아 아래로 떨어지기
                down();
            }
            else return answer;
        }
    }
    // 상 하 좌 우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static void DFS(int x, int y, char color, ArrayList<int[]> list) {

        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx>=0 && ny>=0 && nx<6 && ny<12 && board[ny][nx] == color && !ch[ny][nx]) {
                ch[ny][nx] = true;
                list.add(new int[]{nx,ny});
                DFS(nx, ny, color, list);
            }
        }

    }

    static void down() {

        // 모든 행 다 검사 해야함. 중간에 비어있을 수도 있음
        for(int i=0; i<6; i++) {

            for (int j = 11; j >= 0; j--) {
                // 비어있는 점 찾기
                if (board[j][i] == '.') {

                    // 컬러 위치 찾기
                    for(int k= j-1; k>=0; k--) {
                        if(board[k][i] != '.') {
                            // 컬러 위치면, 옮기기
                            board[j][i] = board[k][i];
                            // 빈칸으로
                            board[k][i] = '.';
                            break;
                        }
                    }
                }
            }


        }
    }
}