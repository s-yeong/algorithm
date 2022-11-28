import java.util.*;
import java.io.*;

class Main {
    static int answer = Integer.MAX_VALUE;
    static int[] ch;
    static int[][] arr;
    static int N;

    static void DFS(int L, int S) {
        // 조합 완성
        if(L == N/2) {
            int sum1 = 0;
            int sum2 = 0;
            for(int i=1; i<=N; i++) {
                for(int j= i+1; j<=N; j++) {

                    if(ch[i] == 1 && ch[j] == 1) {
                        sum1 += arr[i][j] + arr[j][i];
                    }

                    else if(ch[i] == 0 && ch[j] == 0) {
                        sum2 += arr[i][j] + arr[j][i];
                    }
                }
            }

            answer = Math.min(answer, Math.abs(sum1 - sum2));

        }
        else {
            for (int i = S; i <= N; i++) {
                    ch[i] = 1;
                    DFS(L + 1, i + 1);  // S+1이 아니라 i+1
                    ch[i] = 0;
            }
        }
    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];

        // N개의 수
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N개중에서 N/2개 뽑기
        ch = new int[N+1];

        DFS(0, 1);

        System.out.println(answer);
    }
}
