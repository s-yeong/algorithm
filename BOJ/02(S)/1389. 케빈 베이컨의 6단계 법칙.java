import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr_sum = new int[n + 1][n + 1];
        for(int i=0; i<=n; i++) {
            Arrays.fill(arr_sum[i], Integer.MAX_VALUE);
            arr_sum[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 인접행렬 : a와 b 연결, 거리:1
            arr_sum[a][b] = 1;
            arr_sum[b][a] = 1;
        }

        /**
         * 케빈 베이컨의 6단계 법칙
         * 1. 지구에 있는 모든 사람들은 최대 6단계 이내에서 서로 아는 사람으로 연결될 수 있다.
         * 2. 케빈 베이컨 게임은 임의의 `두 사람이 최소 몇 단게` 만에 이어질 수 있는지 계산하는 게임
         * 3. 케빈베이컨은 총 합이 가장 적은 사람 찾기
         * ex) 1이 2,3,4,5를 만나는 최소 단계의 합, 2가 1,3,4,5를 만나는 최소 단계의 합.. 이렇게
         * 5명 중에서 가장 적은 사람 찾기
         * 4. 적은 사람이 여러명이면 번호가 가장 작은 사람 출력
         *
         * 조건
         * 1. 친구가 한명도 없는 사람은 없음
         * 2. 모든 사람은 친구 관계로 연결되어 있음
         * => 무조건 각 쌍마다 최소 단계 존재
         * 3. 1부터 N까지 번호
         *
         * 문제 해결
         * 1. 모든 쌍에 대한 최단 거리 => 플로이드 워셜(최단 경로 알고리즘) => 인접행렬 이용
         * 1-1. 어떤 두 정점 사이의 최단 경로는 어떤 경유지를 거치거나 거치지 않는 경로 중 하나
         * 1-2. 모든 가능한 경유지에 대해 확인 : O(V^3)
         * 1-3. 순환 없으면 음수도 가능
         */

        // 경유지
        for(int i=1; i<=n; i++) {
            // 쌍
            for(int j=1; j<=n; j++) {
                for(int k=1; k<=n; k++) {
                    if(arr_sum[j][i] + arr_sum[i][k] < 0) continue;
                    arr_sum[j][k] = Math.min(arr_sum[j][k], arr_sum[j][i] + arr_sum[i][k]);
                }
            }
        }

        int answer = 0;
        int answer_min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            int sum = 0;
            for(int j=1; j<=n; j++) {
                sum += arr_sum[i][j];
            }
            if(sum < answer_min) {
                answer_min = sum;
                answer = i;
            }
        }

        System.out.println(answer);
    }
}