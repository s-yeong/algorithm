import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1225. 암호생성기
 * 1. 8개의 숫자 입력 받기
 * 2. 첫번쨰 숫자 1 감소한 뒤, 맨 뒤로 보내기
 * 3. 다음 첫번째 수는 2 감소한 뒤 맨 뒤로, 그 다음은 3 감소한 뒤 ..
 * => 큐로 풀기
 */
public class SWEA_1225_암호생성기 {

  // 암호
  static int[] password;
  static final int PASSWORD_LENGTH = 8;
  
  
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    
    // TestCase
    for(int tc=1; tc<=10; tc++) {
    
      br.readLine();
      password = new int[PASSWORD_LENGTH];
      
      Queue<Integer> Q = new ArrayDeque<>();
      st = new StringTokenizer(br.readLine());
      for(int idx=0; idx<PASSWORD_LENGTH; idx++) {
        Q.offer(Integer.parseInt(st.nextToken()));
      }
      
      // 카운팅 변수
      int count = 1;
      
      while(!Q.isEmpty()) {
        
        int num = Q.poll();
        num = num - count++;
        
        // 0이 된 경우 멈춘다.
        if(num <= 0) {
          Q.offer(0);
          break;
        }
        Q.offer(num);
        
        // 5 넘어가면 1부터 다시 시작
        if(count == 6) count = 1;
      }
      
      // 정답
      sb.append("#").append(tc).append(" ");
      while(!Q.isEmpty()) sb.append(Q.poll()).append(" ");
      sb.append("\n");
    }
    System.out.println(sb);
  }
  
 
}
