import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * N과 M (1)
 * 1. nPm 수열 출력하기 
 * 2. 1부터 n까지 자연수 중에서 중복 없이 m개를 고른 수열
 *
 * 1. 재귀 함수를 이용해 출력하자
 * 2. 중복이 없기 때문에 체크 배열을 만들어 중복을 고려하자.
 */
public class BOJ_15649_N과M1 {
  
  // 순열을 저장할 배열
  static int[] permutation;
  
  // 입력 받을 변수
  static int n,m;
  
  // 체크 배열 (중복 방지)
  static boolean[] check;
  
  // 출력
  static StringBuilder sb;
  
  public static void main(String[] args) throws IOException {
    
    // 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine().trim());
    
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    
    // 초기화
    sb = new StringBuilder();
    permutation = new int[m];
    check = new boolean[n+1];
    

    // 재귀 함수 호출
    recursive(0);
    
    // 출력
    System.out.print(sb);
  }
  
  static void recursive(int curren_depth) {
    
    // m개를 저장 했으면, 
    if(curren_depth == m) {
      
      // 출력
      for(int num : permutation) {
        sb.append(num).append(" ");
      }
      sb.append("\n");
    }
    else {
      
      for(int num = 1; num <= n; num++) {
        
        // 방문하지 않았으면,
        if(!check[num]) {
          
          // 순열 배열에 저장
          permutation[curren_depth] = num;
          
          // 방문 체크
          check[num] = true;
          
          // 재귀 호출
          recursive(curren_depth+1);
          
          // 방문 체크 해제
          check[num] = false;
        }
      }
    }
    
  }
}