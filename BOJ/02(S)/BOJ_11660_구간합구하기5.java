import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11660. 구간 합 구하기 5
 * 1. N*N개의 표에서 (x1, y1)부터 (x2, y2)까지 합 구하기
 * 2. N 범위 2^10, M 범위 10만
 * 3. x1 <= x2, y1 <= y2
 * 
 * 1. M번 동안 이차원 배열의 구간 합을 구한다
 * 2. 최악의 상황 O(N^2*M) -> 시간초과
 * 3. 구간 합을 구하는 문제기 때문에 누적합 떠올리기
 * 
 */
public class BOJ_11660_구간합구하기5 {

  // 입출력.
  static BufferedReader br;
  static StringBuilder sb;
  static StringTokenizer st;
  static int n,m;
  static int[][] table;
  
  public static void main(String[] args) throws IOException {
    
    // 초기화
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    sb = new StringBuilder();
    
    // 배열 정보 입력 받기
    table = new int[n+1][n+1];
    for(int x=1; x<=n; x++) {
      st = new StringTokenizer(br.readLine().trim());
      for(int y=1; y<=n; y++) {
        table[x][y] = Integer.parseInt(st.nextToken());
      }
    }
    
    // 누적합 계산을 저장하기 위한 배열
    int[][] cumulativeSum = new int[n+1][n+1];
    
    // (1,1) 부터 (x,y) 까지의 누적합을 계산
    // 현재 인덱스 기준 (x-1,y) 사각형, (x,y-1) 사각형 더해준다음 중복으로 더한 (x-1,y-1) 사각형 뺴주면 된다. 
    for(int x=1; x<=n; x++) {
      for(int y=1; y<=n; y++) {
        cumulativeSum[x][y] = cumulativeSum[x-1][y] + cumulativeSum[x][y-1] - cumulativeSum[x-1][y-1] + table[x][y];
      }
    }
    
    
    for(int count=0; count<m; count++) {
      
      st = new StringTokenizer(br.readLine());
      int startX = Integer.parseInt(st.nextToken());
      int startY = Integer.parseInt(st.nextToken());
      int endX = Integer.parseInt(st.nextToken());
      int endY = Integer.parseInt(st.nextToken());
      
      // 구간합은 누적합에서 벗어나는 지점의 두 사각형을 뺴주고 두 사각형의 중복되는 사각형을 더해준다.
      int sum = cumulativeSum[endX][endY] - cumulativeSum[startX-1][endY] - cumulativeSum[endX][startY-1] + cumulativeSum[startX-1][startY-1];
      sb.append(sum).append("\n");
    }
    System.out.print(sb);
  }

}
