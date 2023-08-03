import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2961. 도영이가 만든 맛있는 음식 
 * 1. N개의 재료, 각 재료의 신맛 S, 쓴맛 B가 있다.
 * 2. 여러 재료를 이용해서 요리할 때, 그 음식의 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다.
 * 4. 재료 적어도 하나 사용 => nC0 X
 * 5. 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램을 작성
 * 
 * 문제 풀이
 * 1. 하나 이상의 재료를 사용 (조건 추가)
 * 2. 요리의 신맛, 쓴맛을 저장하는 배열 선언
 * 3. 재귀 함수를 통해 종료 시점에 차이 비교
 * 4. 하나의 재료를 쓰거나 안쓰거나(nC1, nC2 ...) => 부분집합
 * 5. 1,000,000,000보다 작은 양의 정수 => 합이나, 곱을 했을 때 int 범위 넘어감 -> 합, long 선언 
 */
public class BOJ_2961_도영이가만든맛있는음식 {

  // 입력.
  static BufferedReader br;
  static StringTokenizer st;
  
  // 재료 개수
  static int ingredientCount;
  // 재료 저장 배열
  static ArrayList<int[]> ingredientList;
  // 최소값 저장
  static long minAnswer;
  
  public static void main(String[] args) throws IOException {
      
    br = new BufferedReader(new InputStreamReader(System.in));
    
    // 초기화
    ingredientList = new ArrayList<>();
    minAnswer = Long.MAX_VALUE;
    
    // 재료 개수
    ingredientCount = Integer.parseInt(br.readLine());
    
    // 재료 저장
    for(int idx=0; idx<ingredientCount; idx++) {
      st = new StringTokenizer(br.readLine());
      
      // 0: 신맛, 1: 쓴맛
      int sour = Integer.parseInt(st.nextToken());
      int bitter = Integer.parseInt(st.nextToken());
      
      ingredientList.add(new int[]{sour,bitter});
    }
    
    // 재귀 호출
    subset(0, 1, 0);
    
    // 출력
    System.out.println(minAnswer);
  }
  static void subset(int depth, long sourSum, long bitterSum) {
    
    // 모든 요소 개수 쓸지 계산 했으면,
    // 하나 이상의 재료를 사용했는지 체크 해야함 => bitterSum이 0인 경우 재료 사용X
    if(depth == ingredientCount) {
      if(bitterSum == 0) return;
      
      // 차이 최소값 갱신
      long diff = (long) Math.abs(bitterSum - sourSum);
      minAnswer = Math.min(minAnswer, diff);
    }
    else {
      // 해당 index의 요소 가져오기
      int[] ingredient = ingredientList.get(depth);
      
      // 요소를 쓸지 - 신맛은 곱, 쓴맛은 합
      subset(depth+1, sourSum * ingredient[0], bitterSum + ingredient[1]);
      // 안쓸지
      subset(depth+1, sourSum, bitterSum);
    }
  }
}
