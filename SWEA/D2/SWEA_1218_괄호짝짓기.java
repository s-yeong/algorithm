import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1218. 괄호 짝짓기 
 * 0. 스택 직접 구현하기 
 * 1. 괄호 문자들이 짝이 맞는지 판별
 * 2. '(' 형태의 괄호를 스택에 넣고 ')' 형태의 괄호를 만났을 때 스택에서 꺼낸다.
 * 3. 1:유효함, 0:유효하지 않음
 */
public class SWEA_1218_괄호짝짓기 {

  // 정답
  static int answer;

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    // TestCase
    for(int tc=1; tc<=10; tc++) {
      
      // 정답 초기화
      answer = 1;
      
      // 스택 최대 길이
      int maxSize = Integer.parseInt(br.readLine().trim());
      
      Stack<Character> stack = new Stack<>(maxSize);
      
      for(char ch : br.readLine().trim().toCharArray()) {
        
        // '(' 형태의 괄호 만나면 푸쉬
        if(ch == '(' || ch == '[' || ch == '{' || ch == '<') {         
          stack.push(ch);
        }
        
        // ')' 형태의 괄호 만나면,
        else {
          // '('에 해당하는 짝이 있어야함
          // 만약 비어 있다면, 짝이 없음
          if(stack.isEmpty()) {
            answer = 0;
            break;
          }
          
          // 짝이면, 
          if(isPair(ch, stack.peek())) {
            stack.pop();
          }
          else {
            answer = 0;
            break;
          }
        }
      }
      
      // stack에 데이터가 남아 있다면 오답
      if(!stack.isEmpty()) answer = 0;
      
      sb.append("#").append(tc).append(" ").append(answer).append("\n");
    }
    System.out.println(sb);
  }
  
  static boolean isPair(char ch1, char ch2) {
    
    if(ch2 == '(' && ch1 == ')') return true;
    else if(ch2 == '[' && ch1 == ']') return true;
    else if(ch2 == '{' && ch1 == '}') return true;
    else if(ch2 == '<' && ch1 == '>') return true;
    
    return false;
  }
 
  static class Stack<E> {
    
    // 저장 된 원소 중 마지막 원소
    int top;
    
    // 스택 저장 배열
    E[] data;
    
    // 최대 사이즈
    int maxSize;
    
    public Stack() {
      this.maxSize = 0;
      // new 연산자는 컴파일 시점에 타입 T가 뭔지 정확히 알아야 함
      this.data = (E[]) new Object[maxSize];
      this.top = -1;
    }
    
    public Stack(int maxSize) {
      this.maxSize = maxSize;
      this.data = (E[]) new Object[maxSize];
      this.top = -1;
    }
    
    // 스택에 자료를 저장한다.
    void push(E num) {
      data[++top] = num;
    }

    // 스택에 자료를 꺼낸다.
    E pop() {
      return data[top--];
    }

    // 스택의 top에 있는 원소를 반환한다.
    E peek() {
      return data[top];
    }

    // 스택이 비어있는지 반환한다.
    boolean isEmpty() {
      return size() == 0;
    }

    // 스택의 크기를 반환한다.
    int size() {
      return top + 1;
    }
  }
  
  
}
