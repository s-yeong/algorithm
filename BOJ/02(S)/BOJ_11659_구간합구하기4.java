import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11659. 구간 합 구하기 4
 * 1. N개의 수가 주어졌을 때, i번쨰 부터 j번째 수까지 합을 구하는 프로그램 작성
 * 2. N, M 최대 10만
 * 
 * 1. M번 동안 배열의 구간 합을 구한다
 * 2. 최악의 상황 O(NM) -> 시간초과
 * 3. 구간 합을 구하는 문제기 때문에 누적합 떠올리기
 */
public class BOJ_11659_구간합구하기4 {

  // 입출력.
  static BufferedReader br;
  static StringBuilder sb;
  static StringTokenizer st;
  static int n,m;
  static int[] arr;
  
  
  public static void main(String[] args) throws IOException {
    
    // 초기화
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    sb = new StringBuilder();
    
    // 배열 정보 입력 받기
    st = new StringTokenizer(br.readLine().trim());
    arr = new int[n+1];
    for(int idx=1; idx<=n; idx++) arr[idx] = Integer.parseInt(st.nextToken());
    
    // 누적합 계산을 저장하기 위한 배열
    int[] cumulativeSum = new int[n+1];
    
    // 누적합 계산
    // 이전 인덱스까지 합 + 현재 인덱스 추가해서 누적
    for(int idx=1; idx<=n; idx++) {
      cumulativeSum[idx] = cumulativeSum[idx-1] + arr[idx];
    }
    
    for(int idx=0; idx<m; idx++) {
      
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      
      // 구간합은 누적합의 끝에서 시작 지점을 뺀 다음. 시작 지점을 뻇으니까 추가 해준다.
      int sum = cumulativeSum[end] - cumulativeSum[start] + arr[start];
      sb.append(sum).append("\n");
    }
    System.out.print(sb);
  }

}
