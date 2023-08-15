import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2023. 신기한 소수
 * 1. n 범위 1 ~ 8
 * 2. 한자리씩 늘려가면서 소수인지 체크 한다.
 * 3. 재귀 함수를 통해 해당 depth까지의 길이가 소수이면 다음 depth로 넘어간다.
 */
public class BOJ_2023_신기한소수 {

  // 입력.
  static BufferedReader br;
  // 출력
  static StringBuilder sb;
  
  static int n;
  
  public static void main(String[] args) throws IOException {
      
    br = new BufferedReader(new InputStreamReader(System.in));
    sb = new StringBuilder();
    
    n = Integer.parseInt(br.readLine());
    
    // 한자리씩 늘려가면서 소수인지 체크한다.
    recursion(0, 0);
    System.out.print(sb);
  }
  static void recursion(int depth, int num) {
    
    // 해당 depth까지 왔으면 소수
    if(depth == n) {
      sb.append(num).append("\n");
    }
    else {
      
      for(int x=1; x<=9; x++) {
        // 한자리 수 늘리기
        int nextNum = num * 10 + x;
        
        // 소수이면,
        if(isPrime(nextNum)) {
          // 다음 depth
          recursion(depth+1, nextNum);
        }
      }
    }
  }
  
  // 소수 확인
  static boolean isPrime(int num) {
    
    if(num == 1) return false;
    
    for(int i=2; i<=Math.sqrt(num); i++) {
      if(num % i == 0) return false;
    }
    return true;
  }
}
