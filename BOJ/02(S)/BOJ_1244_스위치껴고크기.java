import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1244. 스위치 켜고 끄기 
 * 
 * 1. '1'은 스위치가 켜져있음, '0'은 꺼져있음
 * 2. 남학생 -> 자기가 받은 번호의 배수의 스위치 상태 바꿈
 * 3. 여학생 -> 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치 상태 바꿈
 *
 * 문제 해결
 * 1. 스위치 상태 배열 1번 부터 시작
 * 2. 남자인 경우, 여자인 경우 메서드 구분
 */
public class BOJ_1244_스위치껴고크기 {

  // 스위치 개수
  static int[] switch_status;
  // 스위치 상태 배열
  static int switch_count;
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // 스위치 개수
    switch_count = Integer.parseInt(br.readLine());
    // 스위치 상태 배열
    switch_status = new int[switch_count + 1];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int idx=1; idx<=switch_count; idx++) {
      switch_status[idx] = Integer.parseInt(st.nextToken());
    }
    
    // 학생 수
    int student_count = Integer.parseInt(br.readLine());
    
    for(int idx=0; idx<student_count; idx++) {
      st = new StringTokenizer(br.readLine());
      
      // 학생 성별 : 남학생은 1, 여학생은 2
      int sex = Integer.parseInt(st.nextToken());
      
      // 학생이 받은 스위치 수
      int student_switch = Integer.parseInt(st.nextToken());
      
      // 남자라면,
      if(sex == 1) {
        maleSwitch(student_switch);
      }
      // 여자라면,
      else {
        femaleSwitch(student_switch);
      }
    }
   
    // 스위치 출력
    for(int idx=1; idx<=switch_count; idx++) {
      System.out.print(switch_status[idx] + " ");
      if(idx % 20 == 0) System.out.println();
    }

  }
  
  static void maleSwitch(int student_switch) {
    // 2. 남학생 -> 자기가 받은 번호의 배수의 스위치 상태 바꿈
    
    for(int idx=student_switch; idx<=switch_count; idx+=student_switch) {
      toggle(idx);
    }
  }
  
  static void femaleSwitch(int student_switch) {
    // 3. 여학생 -> 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치 상태 바꿈
    
    // 좌우 증감을 위한 변수
    int plus = 1;
    
    // 자기가 받은 번호 스위치 상태 바꾸기
    toggle(student_switch);
    
    while(true) {
      
      // 좌우 스위치 동일하다면,
      if(student_switch - plus >= 1 && student_switch + plus <= switch_count && switch_status[student_switch - plus] == switch_status[student_switch + plus]) {

        // 좌우 스위치 상태 바꾸기
        toggle(student_switch - plus);
        toggle(student_switch + plus);
        
        // 증감 변수 ++ 후 다음 확인하기
        plus++;
      }
      else {
        // 다르다면 break;
        break;
      }
    }
  }
  
  // 스위치 상태 바꾸는 메서드
  // 0이면 1, 1이면 0
  static void toggle(int idx) {
    switch_status[idx] = (switch_status[idx] == 0) ? 1 : 0;
  }

}
