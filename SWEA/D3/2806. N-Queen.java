import java.io.*;


public class Main {

    static int[] board;
    static int n, cnt;

    static boolean ch(int L) {

        for(int i=0; i<L; i++) {

            // 이전 행까지 비교해서 같은 값(같은 열)이 존재하면 false
            if(board[L] == board[i]) return false;

            // 대각선에 위치하는지 확인 - 행번호 차이 = 열 번호 차이 -> false
            else if (Math.abs(L- i) == Math.abs(board[L] - board[i])) return false;
        }

        return true;
    }


    static void DFS(int L) {

        // 마지막 행까지 오면,
        if(L == n) {
            cnt++;
        }
        else {

            // 행기준
            for(int i=0; i<n; i++) {

                board[L] = i;   // L번째 행, i번째 열에 퀸 놓기

                // 놓을 수 있으면
                if(ch(L)) {
                    DFS(L + 1); // 다음 행에 퀸 놓기
                }
            }
        }


    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        // 8*8 체스보드에 8개의 퀸을 서로 공격하지 못하게 놓는 문제는 잘 알려져 있는 문제이다.

        for(int i=0; i<T; i++) {

            n = Integer.parseInt(br.readLine());

            // board[행] = 열 => 열의 위치를 담은 배열
            board = new int[n];

            cnt = 0;
            DFS(0);
            for(int x : board) System.out.print(x + " ");
            sb.append("#").append(i + 1).append(" ").append(cnt).append("\n");
        }






            System.out.println(sb);
        }
    }