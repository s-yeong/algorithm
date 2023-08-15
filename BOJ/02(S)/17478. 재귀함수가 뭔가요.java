import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  /**
   * 17478. 재귀함수가 뭔가요?
   * n만큼 재귀를 돌면서 출력하기
   */
  
  // 정답 출력
  static StringBuilder answer = new StringBuilder();
  
  // 재귀 대상 문자열
  static String str1 = "\"재귀함수가 뭔가요?\"\n";
  static String str2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
  static String str3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
  static String str4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
  static String str5 = "라고 답변하였지.\n";
  static String str_end = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
  
  // depth에 따른 재귀 구분 문자열
  static String recur_str = "____";
  
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
    recur(n, 0);
    System.out.print(answer);
  }
  
  public static void recur(int n, int depth) {
    
    // 최종 깊이에 왔을 때,
    if(depth == n) {
      
      // 재귀 구분 문자열 해당 depth 만큼 반복
      StringBuilder repaet_str = new StringBuilder();
      for(int count = 0; count<depth; count++) {
        repaet_str.append(recur_str);
      }
      answer.append(repaet_str.toString()).append(str1);
      answer.append(repaet_str.toString()).append(str_end);
      answer.append(repaet_str.toString()).append(str5);
      return;
    }
    
    else {
      
      // 재귀 구분 문자열 해당 depth 만큼 반복
      StringBuilder repaet_str = new StringBuilder();
      for(int count = 0; count<depth; count++) {
        repaet_str.append(recur_str);
      }
      
      answer.append(repaet_str.toString()).append(str1);
      answer.append(repaet_str.toString()).append(str2);
      answer.append(repaet_str.toString()).append(str3);
      answer.append(repaet_str.toString()).append(str4);
      recur(n, depth+1);
      answer.append(repaet_str.toString()).append(str5);
    }
    
  }
}
