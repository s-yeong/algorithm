import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1954. 달팽이 숫자 
 * 1. 배열의 범위를 벗어날 때 마다 방향을 바꾼다.
 * 2. 카운팅 하면서 종료 시점에 멈춘다.
 */
public class SWEA_1954_달팽이숫자 {


  // 입력.
  static BufferedReader br;
  // 출력.
  static StringBuilder sb;

  // 방향 (우,하,좌,상)
  static int[] deltaX = {1, 0, -1, 0};
  static int[] deltaY = {0, 1, 0, -1};
  static int direction;

  // 출력 수
  static int count;
  static int curX;
  static int curY;

  public static void main(String[] args) throws IOException {

    // 초기화
    br = new BufferedReader(new InputStreamReader(System.in));
    sb = new StringBuilder();
    
    // 테스트 케이스 수
    int T = Integer.parseInt(br.readLine().trim());

    
    for (int test_case = 1; test_case <= T; test_case++) {
      
      // 출력 수 초기화
      count = 1;
      // 방향 초기화
      direction = 0;
      
      int n = Integer.parseInt(br.readLine());
      
      // 달팽이 배열
      int[][] snail = new int[n][n];
      
      // 종료 되는 시점 카운트
      int endCount = n*n + 1;
      
      
      // 초기값
      curX = 0;
      curY = 0;
      snail[curY][curX] = count++;
      
      while(true) {
        
        int nextX = curX + deltaX[direction];
        int nextY = curY + deltaY[direction];
        
        // 범위 벗어나면 방향 바꾸기
        if(nextX < 0 || nextY < 0 || nextX >= n || nextY >=n || snail[nextY][nextX] != 0) {
          direction = (direction+1) % 4;
        }
        else {
          snail[nextY][nextX] = count++;
          curY = nextY;
          curX = nextX;
        }
        
        // 종료 조건이 되면 break
        if(count == endCount) break;
      }
      
      // 출력
      sb.append("#").append(test_case).append("\n");
      for(int curY=0; curY<n; curY++) {
        for(int curX=0; curX<n; curX++) {
          sb.append(snail[curY][curX]).append(" ");
        }
        sb.append("\n");
      }
    }
    System.out.print(sb);
  }
}
