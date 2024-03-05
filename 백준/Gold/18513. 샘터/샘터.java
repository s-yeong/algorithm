import java.io.*;
import java.util.*;

/**
 * 18513. 샘터
 * 1. n개의 샘터, k채의 집
 * 2. 하나의 위치 : 샘터, 집, X
 * 3-1. n개의 샘터가 위치한 상태에서 k채의 집을 배치
 * 3-2. 이 때 k채의 집과 가장 가까운 샘터까자의 거리가 불행도
 * => 불행도의 최소값 구하기
 *
 * 풀이
 * - 불행도의 최소값을 구하기 위해 쉼터 기준으로 가장 가까운 위치에 집 배치
 * - bfs를 통해 집 배치하고 모두 배치한 경우 값 구하기
 * - 음수도 들어갈 수 있기 때문에 Set을 통해 구하기
 */
public class Main {
    static int n,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Set<Integer> chSet = new HashSet<>();
        Queue<Integer> Q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<n; idx++) {
            int num = Integer.parseInt(st.nextToken());
            Q.offer(num);
            chSet.add(num);
        }
        long answer = bfs(Q, chSet);
        System.out.println(answer);
    }
    static long bfs(Queue<Integer> Q, Set<Integer> chSet) {

        int time = 1;
        long answer = 0;

        while(!Q.isEmpty()) {

            int len = Q.size();

            while(len-- > 0) {

                int num = Q.poll();

                int leftNum = num - 1;
                int rightNum = num + 1;

                if (!chSet.contains(leftNum)) {
                    chSet.add(leftNum);
                    k--;
                    Q.offer(leftNum);
                    answer += time;
                }
                if(k==0) return answer;
                if (!chSet.contains(rightNum)) {
                    chSet.add(rightNum);
                    k--;
                    Q.offer(rightNum);
                    answer += time;
                }
                if(k==0) return answer;
            }
            time++;
        }
        return answer;
    }
}
