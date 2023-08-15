import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2001. 파리 퇴치
 * 1. N*N 배열에 M*M 파리채를 통해 최대값 구하기
 * 
 * 문제 풀이
 * 1. M*M 만큼 합을 구해서 갱신해준다.
 * 2. 범위를 벗어나면 0을 반환해준다.
 */
public class SWEA_2001_파리퇴치 {

  // 입출력
  static BufferedReader br;
  static StringTokenizer st;
  static StringBuilder sb;
  
  // 파리의 개수가 들어있는 배열
  static int[][] board;
  static int boardSize;
  // 파리채 크기
  static int flapperSize;
  

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine().trim());

    // TestCase
    for (int tc = 1; tc <= T; tc++) {
      
      // Input
      st = new StringTokenizer(br.readLine());
      boardSize = Integer.parseInt(st.nextToken());
      flapperSize = Integer.parseInt(st.nextToken());
      board = new int[boardSize][boardSize];
      
      for(int rowIdx=0; rowIdx<boardSize; rowIdx++) {
        st = new StringTokenizer(br.readLine());
        for(int colIdx=0; colIdx<boardSize; colIdx++) {
          board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
        }
      }
        
      // 답구하기
      int answer = 0;
      for(int rowIdx=0; rowIdx<boardSize; rowIdx++) {
        for(int colIdx=0; colIdx<boardSize; colIdx++) {
          //최대값 반환
          answer = Math.max(answer, getFlyCount(rowIdx, colIdx));
        }
      }
      
      // Output
      sb.append("#").append(tc).append(" ").append(answer).append("\n");
    }
    System.out.println(sb);
  }
  
  static int getFlyCount(int startRow, int startCol) {
    
    // 파리채를 칠 수 없는 경우 0 반환
    if(startRow + flapperSize - 1 > boardSize - 1 || startCol + flapperSize - 1 > boardSize - 1) {
      return 0;
    }
    
    // 파리채 쳤을 때 개수 합 구하기
    int sum = 0;
    for(int rowIdx=startRow; rowIdx<startRow+flapperSize; rowIdx++) {
      for(int colIdx=startCol; colIdx<startCol+flapperSize; colIdx++) {
        sum += board[rowIdx][colIdx];
      }
    }
    return sum;
  }
}
