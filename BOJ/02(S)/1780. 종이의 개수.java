import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 처음 값 저장후 탐색하다가 다른 값 나오면 9등분
        int[] answer = solution(board);

        for(int x : answer) System.out.println(x);
    }

    // 종이가 같은 수로 되어있는지 비교
    static boolean isSame(int[][] paper) {

        int tmp = paper[0][0];
        for(int i=0; i< paper.length; i++) {
            for(int j=0; j <paper.length; j++) {
                if(paper[i][j] != tmp) return false;
            }
        }
        return true;
    }

    static int[] solution(int[][] board) {

        Queue<int[][]> Q = new LinkedList<>();
        Q.offer(board);

        // 답 - -1, 0, 1 개수
        int[] answer = new int[3];

        while(!Q.isEmpty()) {

            int[][] cur = Q.poll();

            // 종이가 모두 같은 수인 경우
            if(isSame(cur)) {
                // -1로만 채워진 경우
                if(cur[0][0] == -1) answer[0]++;

                // 0으로만 채워진 경우
                if(cur[0][0] == 0) answer[1]++;

                // 1로만 채워진 경우
                if(cur[0][0] == 1) answer[2]++;
            }
            // 같은 종이가 아닌 경우 같은 크기의 9개로 자르기
            else {

                // 9개로 자르기 위해선 배열의 길이가 9이상 이여야함
                if(cur.length >= 9) {

                    int len = (cur.length / 3);
                    int[] d = {0, len - 1, len, (2 * len) - 1, 2 * len, cur.length - 1};

                    for(int k=0; k<6; k=k+2) {
                        for(int p=0; p<6; p=p+2) {
                            int[][] tmp = new int[len][len];
                            for (int i = d[k]; i <= d[k + 1]; i++) {
                                for (int j = d[p]; j <= d[p + 1]; j++) {
                                    tmp[i - d[k]][j - d[p]] = cur[i][j];
                                }
                            }
                            Q.offer(tmp);
                        }
                    }
                }

                // 배열의 크기가 3인 경우 9개로 쪼개면 1개니까 바로 계산
                else {
                    for(int i=0; i<cur.length; i++) {
                        for(int j=0; j< cur.length; j++) {
                            if(cur[i][j] == -1) answer[0]++;
                            if(cur[i][j] == 0) answer[1]++;
                            if(cur[i][j] == 1) answer[2]++;
                        }
                    }
                }
            }

        }
        return answer;
    }



}