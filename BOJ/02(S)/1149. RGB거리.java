import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        /**
         * RGB 거리 집이 N개 (1번~N번)
         * => "모든 집을 칠하는 비용의 최솟값 구하기"
         *
         * 조건
         * 1. 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
         * 2. N번 집의 색은 N-1번의 집의 색과 같지 않아야 한다.
         * 3. i(2~N-1)번째 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
         *
         * 1. 인접한 집끼리 색이 겹치면 안된다.
         * 2. 그리디X => 모든 경로의 경우의 수를 찾아야 함
         * 3. i번째 R에 왔을 때 최소값, i번쨰 G에 왔을 때 최소값, i번쨰 B에 왔을 때 최소값
         * 4. i번째 R에 올 수 있는 이전 색은 G와 B다!
         */


        int[][] dis = new int[N][3];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 1번 집부터 순서대로
            dis[i][0] = Integer.parseInt(st.nextToken());
            dis[i][1] = Integer.parseInt(st.nextToken());
            dis[i][2] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<N; i++) {

            dis[i][0] = Math.min(dis[i][0] + dis[i-1][1], dis[i][0] + dis[i-1][2]);
            dis[i][1] = Math.min(dis[i][1] + dis[i-1][0], dis[i][1] + dis[i-1][2]);
            dis[i][2] = Math.min(dis[i][2] + dis[i-1][0], dis[i][2] + dis[i-1][1]);
        }

        int answer = Math.min(Math.min(dis[N-1][0], dis[N-1][1]), dis[N-1][2]);
        System.out.println(answer);
    }
}