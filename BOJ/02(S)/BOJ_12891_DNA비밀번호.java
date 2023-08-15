import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 12891. DNA 비밀번호 
 * 1. DNA 문자열 : 모든 문자열에 등장하는 문자가 {‘A’, ‘C’, ‘G’, ‘T’} 인 문자열
 * 2. 부분 문자열을 뽑았을 떄 등장하는 문자의 개수가 특정 개수 이상이어야 비밀번호로 사용할 수 있다.
 * 3. 가능한 비밀번호의 종류의 수를 구하기
 * 4. 부분문자열이 등장하는 위치가 다르다면 부분문자열이 같다고 하더라도 다른 문자열로 취급 => `순서 고려`
 * 
 * 문제 풀이
 * 1. DNA 문자열로 가능한 모든 부분 문자열 만들기
 * 2. 부분 문자열을 만든 후 요구하는 해당 개수가 있는지 확인
 * 
 * 1. 투 포인터로 부분 문자열을 만든다. (substring) -> O(N^2) 이상이기 때문에 시간 초과
 * 2. 처음 부분 문자열을 만들 었을 때 해당 문자를 얼마나 가지고 있는지 맵을 만들어 관리한다.
 * 3. 투 포인터로 lt에 해당하는 문자는 빼주고, rt에 해당하는 문자는 더한다음 조건을 만족하는지 검증한다. 
 * 
 */
public class BOJ_12891_DNA비밀번호 {

  // 입력.
  static BufferedReader br;
  static StringTokenizer st;

  // 문자열 길이
  static int strLen;
  // 비밀번호로 사용할 부분 문자열 길이
  static int subStrLen;
  
  // DNA 문자열
  static String dna;
  
  // 부분문자열에 포함될 ACGT 최소 개수
  static Map<Character, Integer> minCountArr;
  // 부분문자열에 현재 저장될 개수
  static Map<Character, Integer> curCountArr;
  // 가능한 패스워드 수 
  static int passwordCount;
  
  public static void main(String[] args) throws IOException {

    // 입력
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());
    
    strLen = Integer.parseInt(st.nextToken());
    subStrLen = Integer.parseInt(st.nextToken());
    dna = br.readLine();
    st = new StringTokenizer(br.readLine().trim());
    
    // 초기화
    minCountArr = new HashMap<>();
    curCountArr = new HashMap<>();
    passwordCount = 0;
    
    for(char ch : new char[]{'A', 'C', 'G', 'T'}) {
      minCountArr.put(ch, Integer.parseInt(st.nextToken()));
    }
    
    // 처음 길이-1 개수 설정
    for(int idx=0; idx<subStrLen-1; idx++) {
      char ch = dna.charAt(idx);
      // 개수 체크
      curCountArr.put(ch, curCountArr.getOrDefault(ch, 0) + 1);
    }
    
    // 현재 저장된 상태에서 문자 증가시키기
    int lt=0;
    for(int rt=subStrLen-1; rt<strLen; rt++) {

      // 오른쪽 문자 1 증가시키기
      char ch = dna.charAt(rt);
      curCountArr.put(ch, curCountArr.getOrDefault(ch, 0) + 1);

      // 패스워드 가능한지 확인
      if(canPassword()) passwordCount++;
      
      // 왼쪽 문자 1 감소시키기
      ch = dna.charAt(lt++);
      curCountArr.put(ch, curCountArr.get(ch) - 1);
    }

   
    // 정답 출력
    System.out.println(passwordCount);
  }
  
  static boolean canPassword() {
    
    // A, C, G, T 확인하기
    for(char ch : new char[] {'A', 'C', 'G', 'T'}) {
      // 최소 개수 보다 존재하지 않으면 false
      if(minCountArr.get(ch) > curCountArr.getOrDefault(ch, 0)) {
        return false;
      }
    }
    
    return true;
  }
  
  

}
