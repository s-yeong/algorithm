import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2805. 농작물 수확하기 
 * 1. 농장의 크기는 항상 홀수 
 * 2. 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태로만 가능 
 * 3. 규칙에 따라 얻을 수 있는 수익 구하기
 * 
 * 문제 해결 
 * 1. 배열 세로의 중간 지점에서 N만큼 길이 만큼 누적한다. 
 * 2. 위와 아래를 1씩 증가 시킬 때, lt(left)를 1 증가시키고, rt(right)를 -1 감소 시키면서 값을 누적한다.
 *
 */
public class SWEA_2805_농작물수확하기 {

  public static void main(String[] args) throws IOException {

    // 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 출력
    StringBuilder sb = new StringBuilder();

    // 테스트 케이스 수
    int T = Integer.parseInt(br.readLine().trim());

    for (int test_case = 1; test_case <= T; test_case++) {

      // 농장의 크기
      int N = Integer.parseInt(br.readLine().trim());

      // 농장
      int[][] farm = new int[N][N];
      
      // 수익의 합
      int sum = 0;

      for (int row = 0; row < N; row++) {
        String str = br.readLine().trim();
        for (int column = 0; column < N; column++) {
          farm[row][column] = str.charAt(column) - '0';
        }
      }

      // 중간 지점
      int middle = N / 2;

      // 중간 지점 누적
      for(int column = 0; column < N; column++) {
        sum += farm[middle][column];
      }
      
      // left
      int lt = 1;
      // right
      int rt = N-2;
      
      // 배열 위, 아래로 증가 시키기 위한 변수
      int plus = 1;
      
      // farm 배열의 범위를 벗어나기전 까지
      while(plus <= N/2) {
        
        for(int idx=lt; idx<=rt; idx++) {
          // 배열 위 누적
          sum += farm[middle+plus][idx];
          // 배열 아래 누적
          sum += farm[middle-plus][idx];
        }
        
        lt++;
        rt--;
        plus++;
      }

       sb.append("#").append(test_case).append(" ").append(sum).append("\n");
    }

    // 정답 출력.
    System.out.print(sb);

  }
}
