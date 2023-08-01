import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1208_Flatten {

  public static void main(String[] args) throws IOException {

    /**
     * [S/W 문제해결 기본] 1일차 - Flatten D3
     * 1. 평탄화 작업을 제한된 횟수만큼 옮긴 다음 최고점과 최저점 차이 반환
     * 2. 주어진 덤프 횟수 이내에 평탄화 완료되면 높이 차이 반환
     * 
     * 문제 해결
     * 1. 최고점은 -1 시키고, 최저점은 +1 시킨다.
     * 2. 매번 최고점과 최저점을 찾기 위해 정렬을 한다.
     * 3. 주어진 덤프 횟수 이내에 평탄화가 완료됐어도 어차피 최고점과 최저점 차이를 반환하니까 확인할 필요 없다.
     */
    
    // 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 출력
    StringBuilder sb = new StringBuilder();
    
    
    
    for (int test_case = 1; test_case <= 10; test_case++) {
      
      // 덤프 횟수
      int dump_count = Integer.parseInt(br.readLine().trim());
      // 박스
      int[] box = new int[100];

      // 입력받기
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int idx=0; idx<100; idx++) {
        box[idx] = Integer.parseInt(st.nextToken());
      }
      
      // 덤프 횟수만큼 평탄화
      while(dump_count --> 0) {
        // 매번 정렬한 후 최고점 -1, 최저점+1
        Arrays.sort(box);
        box[0]++;
        box[99]--;
      }
      
      // 최고점과 최저점 차이 반환
      // 마지막에 차이가 변경되었을 수도 있으니 정렬 한번 더  해주기
      Arrays.sort(box);
      int answer = box[99] - box[0];
      
      sb.append("#").append(test_case).append(" ").append(answer).append("\n");
    }
    
    // 정답 출력.
    System.out.print(sb);
  }

}
