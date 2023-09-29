import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
/**
 * 1263. [S/W 문제해결 응용] 8일차 - 사람 네트워크2
 * 1. CC(i) = ∑ j dist(i,j) 단, dist(i,j)는 노드i로부터 노드 j까지의 최단 거리
 * 1-1. 한 정점을 기준으로 모든 지점의 최단 거리의 합을 구한다
 * 1-2. 그 합 중 최소가 되는 값이 정답
 * => 모든 정점을 기준으로 최단 거리 구해야함 => 플로이드-워샬!
 */
public class SWEA_사람네트워크2 {
 
    static int T, nodeCount;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
         
        // 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; testCase++) {
             
            st = new StringTokenizer(br.readLine());
            nodeCount = Integer.parseInt(st.nextToken());
             
            // 시작 정점을 기준으로 최단거리의합이 CC가 되고, 그 중 최소 되는 정점이 답.
             
            // 갈수 없는 거리를  MAX/2로 둔다. 그러면, 갈수 없는 거리 + 갈 수 없는 거리 = MAX가 되기 때문에 overflow가 발생하지 않는다.
            dp = new int[nodeCount+1][nodeCount+1];
            for(int nodeIdx=1; nodeIdx<=nodeCount; nodeIdx++) {
                Arrays.fill(dp[nodeIdx], Integer.MAX_VALUE/2);
                // 자신의 노드에서 노드 거리는 0으로
                dp[nodeIdx][nodeIdx] = 0;
            }
             
             
            for(int nodeIdx=1; nodeIdx<=nodeCount; nodeIdx++) {
                for(int nextNodeIdx=1; nextNodeIdx<=nodeCount; nextNodeIdx++) {
                    int canNext = Integer.parseInt(st.nextToken());
                     
                    // 갈 수 있는 노드면,
                    if(canNext == 1) {
                        // 무방향 그래프
                        dp[nodeIdx][nextNodeIdx] = 1;
                        dp[nextNodeIdx][nodeIdx] = 1;
                    }
                }
            }
             
            // 모든 정점의 최단 거리 구하기 -> 플로이드-워샬
            // 경유지
            for(int targetIdx=1; targetIdx<=nodeCount; targetIdx++) {
                // 출발점
                for(int start=1; start<=nodeCount; start++) {
                    // 도착점
                    for(int end=1; end<=nodeCount; end++) {
                        // 최소값 갱신
                        dp[start][end] = Math.min(dp[start][end], dp[start][targetIdx] + dp[targetIdx][end]);
                    }
                }
            }
             
            //1-1. 한 정점을 기준으로 모든 지점의 최단 거리의 합을 구한다
             
            int minDis = Integer.MAX_VALUE;
            for(int start=1; start<=nodeCount; start++) {
                int sum = 0;
                for(int end=1; end<=nodeCount; end++) {
                    sum += dp[start][end];
                }
                //1-2. 그 합 중 최소가 되는 값이 정답
                minDis = Math.min(minDis, sum);
            }
             
            sb.append("#" + testCase + " " + minDis + "\n");
        }
        System.out.print(sb);
    }
}