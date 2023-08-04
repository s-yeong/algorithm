import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 2164. 카드2
 * 1. 1번 카드가 제일 위에, n번 카드가 제일 아래인 상태로 순서대로 카드가 놓여 있다.
 * 2. 제일 위에 있는 카드부터 꺼내기 때문에 큐 자료 구조 사용
 * 
 * 문제 해결
 * 1. 제일 위에 있는 카드 바닥에 버리기 =>
 * 2. 그 다음  제일 위에 있는 카드 제일 아래에 있는 카드로 옮기기
 * 3. 카드가 하나 남았을 때 답을 리턴
 */
public class BOJ_2164_카드2 {
  
  static int n;
  static Queue<Integer> Q = new ArrayDeque<>();
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    for(int num=1; num<=n; num++) {
      Q.offer(num);
    }
    
    while(Q.size() != 1) {
       // 제일 위에 있는 카드 바닥에 버리기
      Q.poll();
      
      // 그 다음  제일 위에 있는 카드 제일 아래에 있는 카드로 옮기기
      Q.offer(Q.poll());
    }
    System.out.println(Q.poll());
  }
}
  