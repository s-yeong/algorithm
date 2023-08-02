import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 15650. N과 M (2)
 * 1. 1부터 n까지 자연수 중에서 중복 없이 m개를 고른 수열
 * 2. 고른 수열은 오름차순이어야 한다.
 * => 조합 문제
 *
 * 문제 풀이
 * 1. 재귀 함수를 이용
 * 2. 시작 지점을 파라미터로 가져가서 이전 인덱스 이후로 저장되도록 함
 *
 */
public class BOJ_15650_N과M2 {

  // 입력.
  static BufferedReader br;
  static StringTokenizer st;
  static int n,m;
  
  // 수열 저장 배열 (조합)
  static int[] combi;
  
  // 출력.
  static StringBuilder sb;
  
  public static void main(String[] args) throws IOException { 
    
    // 초기화
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    sb = new StringBuilder();
    combi = new int[m];
    
    // 재귀함수 호출
    recursive(0, 1);
    
    // 출력
    System.out.print(sb);
  }
  
  static void recursive(int depth, int start) {

    // m개를 저장 했으면, 
    if(depth == m) {
      for(int num : combi) {
        sb.append(num).append(" ");
      }
      sb.append("\n");
    }
    
    else {
      
      for(int idx=start; idx<=n; idx++) {
        // 조합 배열에 저장
        combi[depth] = idx;
        
        // 재귀 호출 -> start = 현재 인덱스 + 1
        recursive(depth+1, idx+1);
      }
    }
  }
  
  

}
