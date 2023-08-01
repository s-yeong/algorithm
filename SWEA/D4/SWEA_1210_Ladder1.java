import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1210_Ladder1 {

  // 사다리 배열
  static int[][] ladder;
  
  // 도착 지점에 도달했는지 확인
  static boolean flag;
  
  // 바닥에 도착했는지 확인
  static boolean floor; 
  public static void main(String[] args) throws IOException {

    /**
     * [S/W 문제해결 기본] 2일차 - Ladder1 
     * 1. 지정된 도착점에 대응되는 출발점 X 반환 
     * 2. 0으로 채워진 평면상에 사다리는 연속된 1로 표현, 도착 지점은 2로 표현
     * 3. 아래 방향으로 진행하면서 좌우 방향으로 이동 가능한 통로 있으면 방향 전환
     * 
     * 문제 해결 
     * 1. 출발점 0~100까지 돌면서 사다리가 있는지 확인 
     * 2. 이후 도착 지점에 돌아갈 수 있는지 확인 
     * 3. 현재 방향 지정 필요
     * 4. 99, 즉  바닥에 도착 했을 때 해당 X에서 탐색을 끝마쳐야함!!
     */

    // 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 출력
    StringBuilder sb = new StringBuilder();



    for (int test_case = 1; test_case <= 10; test_case++) {

      // test_case 번호 받기
      br.readLine();

      // 사다리 2차원 배열
      ladder = new int[100][100];

      // X:출발점
      for (int idx = 0; idx < 100; idx++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int X = 0; X < 100; X++) {
          ladder[idx][X] = Integer.parseInt(st.nextToken());
        }
      }
      
      // 플래그 초기화, 도착 지점에 도달했으면 true
      flag = false;
      int answer = -1;
      
      for (int X = 0; X < 100; X++) {
        
        // 사다리가 있는 출발점이면,
        if (ladder[0][X] == 1) {
          
          // 내려가기, 바닥 지점 초기화
          floor = false;
          climb(0, X, 1);
          
          // 도착 지점에 도달 했으면, break;
          if(flag) {
            answer = X;
            break;
          }
        }
      }
      
      sb.append("#").append(test_case).append(" ").append(answer).append("\n");
    }

    // 정답 출력.
    System.out.print(sb);

  }

  // 좌우 방향
  static int[] dx = {-1, 1};
  
  // 사다리 타기
  static void climb(int cur_dir, int cur_X, int cur_Y) {
    
    if(floor) return;
    
    // 재귀 종료 시점. 바닥에 도달했을 때
    if(cur_Y == 99) {
      floor = true;
      // 도착 지점이면 체크하기
      if(ladder[cur_Y][cur_X] == 2) {
        flag = true;
      }
      
      return;
    }
    
    // 현재 아래 방향이면
    if(cur_dir == 0) {
      
      // 좌우 방향 확인
      for(int idx=0; idx<2; idx++) {
        
        // 배열 범위 벗어나면 contine;
        if(cur_X + dx[idx] < 0 || cur_X + dx[idx] > 99) continue;
        
        // 좌우 방향에 1이 있으면, 이동 가능한 통로가 있다.
        if(ladder[cur_Y][cur_X + dx[idx]] == 1) {
          // 방향 전환
          climb(dx[idx], cur_X + dx[idx], cur_Y);
        }
      }
      
      // 좌우에 통로 없으면, 이동
      climb(cur_dir, cur_X, cur_Y+1);
    }
    
    else {
      
      // 다음 지점을 갈 수 있는지 확인
      if(cur_X + cur_dir >= 0 && cur_X + cur_dir < 100 && ladder[cur_Y][cur_X + cur_dir] == 1) {
        climb(cur_dir, cur_X + cur_dir, cur_Y);
      }
      // 갈 수 없으면, 아래로 방향 전환
      else {
        climb(0, cur_X, cur_Y+1);
      }
      
    }
    

  }


}
